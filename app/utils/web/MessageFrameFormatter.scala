package utils.web

import msg.rsp.SocketResponseMessage
import play.api.mvc.WebSocket.MessageFlowTransformer
import utils.{JsonSerializers, Logging}

class MessageFrameFormatter(debug: Boolean) extends Logging {
  val transformer = MessageFlowTransformer.stringMessageFlowTransformer.map { s =>
    JsonSerializers.readSocketRequestMessage(s)
  }.contramap { m: SocketResponseMessage => JsonSerializers.writeSocketResponseMessage(m, debug) }
}
