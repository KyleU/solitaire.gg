// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object GoldRush extends GameRules(
  id = "goldrush",
  title = "Gold Rush",
  related = Seq("doublegoldrush"),
  description = "A ^klondike^ variation where the number of cards dealt to the waste decreases with each pass through the stock.",
  stock = Some(
    StockRules(
      maximumDeals = Some(3),
      cardsDealt = StockCardsDealt.FewerEachTime
    )
  ),
  waste = Some(WasteRules()),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)
