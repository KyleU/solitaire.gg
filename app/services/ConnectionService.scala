package services

import java.util.UUID

import akka.actor.{ Props, ActorRef }
import models._
import models.user.User
import utils.{ Logging, Config }

object ConnectionService {
  def props(supervisor: ActorRef, user: User, out: ActorRef) = Props(new ConnectionService(supervisor, user, out))
}

class ConnectionService(val supervisor: ActorRef, val user: User, val out: ActorRef) extends ConnectionServiceHelper with Logging {
  protected[this] val id = UUID.randomUUID

  protected[this] var activeGameId: Option[UUID] = None
  protected[this] var activeGame: Option[ActorRef] = None

  protected[this] var pendingDebugChannel: Option[ActorRef] = None

  override def preStart() = {
    supervisor ! ConnectionStarted(user, id, self)
  }

  override def receiveRequest = {
    // Incoming basic messages
    case mr: MalformedRequest => timeReceive(mr) { log.error(s"MalformedRequest:  [${mr.reason}]: [${mr.content}].") }
    case p: Ping => timeReceive(p) { out ! Pong(p.timestamp) }
    case GetVersion => timeReceive(GetVersion) { out ! VersionResponse(Config.version) }
    case di: DebugInfo => timeReceive(di) { handleDebugInfo(di.data) }

    // Incoming game messages
    case sg: StartGame => timeReceive(sg) { handleStartGame(sg.rules, sg.seed, sg.testGame.contains(true)) }
    case jg: JoinGame => timeReceive(jg) { handleJoinGame(jg.id) }
    case og: ObserveGame => timeReceive(og) { handleObserveGame(og.id, og.as) }

    case gm: GameMessage => handleGameMessage(gm)
    case im: InternalMessage => handleInternalMessage(im)

    // Outgoing messages
    case rm: ResponseMessage => handleResponseMessage(rm)

    case x => throw new IllegalArgumentException(s"Unhandled message [${x.getClass.getSimpleName}].")
  }

  override def postStop() = {
    activeGame.foreach(_ ! ConnectionStopped(id))
    supervisor ! ConnectionStopped(id)
  }

  private[this] def handleInternalMessage(im: InternalMessage) = im match {
    case gs: GameStarted => timeReceive(gs) { handleGameStarted(gs.id, gs.gameService, gs.started) }
    case gs: GameStopped => timeReceive(gs) { handleGameStopped(gs.id) }
    case ct: ConnectionTrace => timeReceive(ct) { handleConnectionTrace() }
    case ct: ClientTrace => timeReceive(ct) { handleClientTrace() }
    case x => throw new IllegalArgumentException(s"Unhandled internal message [${x.getClass.getSimpleName}].")
  }
}
