package services.game

import models.game._

case class KlondikeThreeCard(override val id: String, override val seed: Int) extends GameVariant(id, seed) {
  override val name = "Klondike"

  val piles = List(
    new Stock("stock"),
    new Waste("waste"),

    new Foundation("foundation-1"),
    new Foundation("foundation-2"),
    new Foundation("foundation-3"),
    new Foundation("foundation-4"),

    new Tableau("tableau-1"),
    new Tableau("tableau-2"),
    new Tableau("tableau-3"),
    new Tableau("tableau-4"),
    new Tableau("tableau-5"),
    new Tableau("tableau-6"),
    new Tableau("tableau-7")
  )

  val deck = Deck.shuffled(rng)

  val layouts = List(
    Layout(
      width = 7.8,
      height = 5.0,
      piles = List(
        PileLocation("stock", 0.1, 0.1),
        PileLocation("waste", 1.2, 0.1),

        PileLocation("foundation-1", 3.4, 0.1),
        PileLocation("foundation-2", 4.5, 0.1),
        PileLocation("foundation-3", 5.6, 0.1),
        PileLocation("foundation-4", 6.7, 0.1),

        PileLocation("tableau-1", 0.1, 1.2),
        PileLocation("tableau-2", 1.2, 1.2),
        PileLocation("tableau-3", 2.3, 1.2),
        PileLocation("tableau-4", 3.4, 1.2),
        PileLocation("tableau-5", 4.5, 1.2),
        PileLocation("tableau-6", 5.6, 1.2),
        PileLocation("tableau-7", 6.7, 1.2)
      )
    )
  )

  val gameState = GameState(id, seed, deck, piles, layouts)

  def initialMoves() = {
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-1")

    gameState.addCards(deck.getCards(1), "tableau-2")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-2")

    gameState.addCards(deck.getCards(2), "tableau-3")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-3")

    gameState.addCards(deck.getCards(3), "tableau-4")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-4")

    gameState.addCards(deck.getCards(4), "tableau-5")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-5")

    gameState.addCards(deck.getCards(5), "tableau-6")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-6")

    gameState.addCards(deck.getCards(6), "tableau-7")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-7")

    gameState.addCards(deck.getCards(), "stock")
  }
}
