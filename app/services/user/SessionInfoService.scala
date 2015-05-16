package services.user

import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import com.mohiva.play.silhouette.impl.daos.AuthenticatorDAO
import models.database.queries.auth.SessionInfoQueries
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import utils.CacheService

import scala.concurrent.Future

object SessionInfoService extends AuthenticatorDAO[CookieAuthenticator] {
  override def save(session: CookieAuthenticator) = Database.execute(SessionInfoQueries.CreateSessionInfo(session)).map { x =>
    CacheService.cacheSession(session)
  }

  override def remove(id: String) = Database.execute(SessionInfoQueries.RemoveSessionInfo(id)).map { i =>
    CacheService.removeSession(id)
  }

  override def find(id: String) = CacheService.getSession(id) match {
    case Some(sess) => Future.successful(Some(sess))
    case None => Database.query(SessionInfoQueries.FindSessionInfo(id)).map {
      case Some(dbSess) =>
        CacheService.cacheSession(dbSess)
        Some(dbSess)
      case None => None
    }
  }
}
