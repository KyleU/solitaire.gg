package services.game

import akka.actor.PoisonPill
import models._
import org.joda.time.LocalDateTime

trait GameServiceMessageHelper { this: GameService =>
  protected[this] def handleStopGameIfEmpty() {
    val hasPlayer = player.connectionId.isDefined || observerConnections.exists(_._1.connectionId.isDefined)
    if (!hasPlayer) {
      self ! StopGame
    }
  }

  protected[this] def handleStopGame() {
    log.info("Stopping game [" + id + "].")
    if(status == "started") {
      status = "abandoned"
    }

    context.parent ! GameStopped(id)
    self ! PoisonPill
  }

  protected[this] def sendToAll(messages: Seq[ResponseMessage]): Unit = {
    if (messages.isEmpty) {
      log.info("No messages to send to all players.")
    } else if (messages.tail.isEmpty) {
      sendToAll(messages.headOption.getOrElse(throw new IllegalStateException()))
    } else {
      sendToAll(MessageSet(messages))
    }
  }

  protected[this] def sendToAll(message: ResponseMessage, registerUndoResponse: Boolean = true): Unit = {
    if (registerUndoResponse) {
      undoHelper.registerResponse(message)
    }
    player.connectionActor.foreach(_ ! message)
    observerConnections.foreach { c =>
      c._1.connectionActor.foreach(_ ! message)
    }
  }
}
