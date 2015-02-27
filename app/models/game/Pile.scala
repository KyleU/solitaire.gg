package models.game

object Pile {
  def klondike = List(
    Pile("stock", "stock"),
    Pile("waste", "waste"),

    Pile("foundation-1", "foundation"),
    Pile("foundation-2", "foundation"),
    Pile("foundation-3", "foundation"),
    Pile("foundation-4", "foundation"),

    Pile("tableau-1", "tableau"),
    Pile("tableau-2", "tableau"),
    Pile("tableau-3", "tableau"),
    Pile("tableau-4", "tableau"),
    Pile("tableau-5", "tableau"),
    Pile("tableau-6", "tableau"),
    Pile("tableau-7", "tableau")
  )

  def sandbox = List(
    Pile("sandbox-1", "tableau")
  )
}

case class Pile(id: String, behavior: String, var cards: List[Card] = Nil) {
  def addCards(cs: Seq[Card]) = cs.foreach(addCard)

  def addCard(c: Card) {
    cards = c :: cards
  }

  def removeCard(c: Card) {
    val newCards = cards.filterNot(_.id == c.id)
    if(cards.size == newCards.size) {
      throw new IllegalArgumentException("Provided card [" + c + "] is not included in pile [" + id + "].")
    }
    cards = newCards
  }

  override def toString: String = id + ": " + cards.map(_.toString).mkString(", ")
}
