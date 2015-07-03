package utils.play

import java.util.TimeZone

import akka.actor.ActorSystem
import com.codahale.metrics.SharedMetricRegistries
import org.joda.time.DateTimeZone
import play.api.http.HeaderNames
import play.api.Play.current
import play.api.mvc.{ Action, RequestHeader, Results, WithFilters }
import play.api.{ Application, GlobalSettings, Mode }
import play.filters.gzip.GzipFilter
import services.ActorSupervisor
import services.database.{ Database, Schema }
import services.scheduled.ScheduledTask
import utils.metrics.Instrumented
import utils.{ BuildInfo, Logging }

import scala.concurrent.Future

object PlayGlobalSettings extends WithFilters(PlayLoggingFilter, new GzipFilter()) with GlobalSettings with Logging {
  override def onStart(app: Application) {
    log.info(s"${utils.Config.projectName} build [${BuildInfo.buildinfoBuildnumber}] is starting on [${utils.Config.hostname}].")

    SharedMetricRegistries.remove("default")
    SharedMetricRegistries.add("default", Instrumented.metricRegistry)

    DateTimeZone.setDefault(DateTimeZone.UTC)
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"))

    Database.open()
    Schema.update()
    ActorSupervisor.instance

    scheduleTask(app)

    super.onStart(app)
  }

  override def onStop(app: Application) {
    Database.close()
    super.onStop(app)
  }

  override def onError(request: RequestHeader, ex: Throwable) = if (current.mode == Mode.Dev) {
    super.onError(request, ex)
  } else {
    Future.successful(
      Results.InternalServerError(views.html.error.serverError(request.path, Some(ex))(request.session, request.flash))
    )
  }

  override def onHandlerNotFound(request: RequestHeader) = Future.successful(
    Results.NotFound(views.html.error.notFound(request.path)(request.session, request.flash))
  )

  override def onBadRequest(request: RequestHeader, error: String) = Future.successful(
    Results.BadRequest(views.html.error.badRequest(request.path, error)(request.session, request.flash))
  )

  override def onRouteRequest(request: RequestHeader) = {
    if (!Option(request.path).exists(_.startsWith("/assets"))) {
      log.info(s"Request from [${request.remoteAddress}]: ${request.toString()}")
    }
    if (request.domain == utils.Config.hostname) {
      super.onRouteRequest(request)
    } else {
      Some(redirectToBareDomain(request))
    }
  }

  private[this] def redirectToBareDomain(request: RequestHeader) = Action {
    val protocol = if (request.secure) { "https" } else { "http" }
    Results.MovedPermanently(s"$protocol://${utils.Config.hostname}${request.path}").withHeaders(HeaderNames.CACHE_CONTROL -> "public, max-age=31556926")
  }

  private[this] def scheduleTask(app: Application) = {
    import scala.concurrent.ExecutionContext.Implicits.global
    import scala.concurrent.duration._

    if (current.mode == Mode.Dev) {
      log.info("Dev mode, so not starting scheduled task.")
    } else {
      log.info("Scheduling task to run every minute, after five minutes.")
      val task = app.injector.instanceOf[ScheduledTask]
      val system = app.injector.instanceOf[ActorSystem]
      system.scheduler.schedule(5.minutes, 1.minute, task)
    }
  }
}