package models

import models.game.{Card, GameState}

sealed trait ResponseMessage

case class ServerError(reason: String, content: String) extends ResponseMessage
case class Pong(timestamp: Long) extends ResponseMessage
case class VersionResponse(version: String) extends ResponseMessage

case class GameJoined(id: String, players: Seq[String], state: GameState) extends ResponseMessage

case class CardRevealed(card: Card) extends ResponseMessage
case class CardMoved(card: String, source: String, target: String, targetIndex: Option[Int] = None, turnFaceUp: Boolean = false, turnFaceDown: Boolean = false) extends ResponseMessage
case class CardMoveCancelled(cards: Seq[String], source: String) extends ResponseMessage

case class MessageSet(messages: Seq[ResponseMessage]) extends ResponseMessage
