// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Chelicera extends GameRules(
  id = "chelicera",
  title = "Chelicera",
  like = Some("scorpion"),
  description = "A variation on ^scorpion^ invented by Erik den Hollander in which we fill spaces with three cards from the stock instead of dealin" +
  "g from the stock.",
  victoryCondition = VictoryCondition.AllOnTableauSorted,
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Never,
      maximumDeals = Some(1)
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Custom,
      customInitialCards = Seq(
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUU",
        "UUUUUUU",
        "UUUUUUU",
        "UUUUUUU"
      ),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      rankMatchRuleForMovingStacks = RankMatchRule.Any,
      autoFillEmptyFrom = TableauAutoFillEmptyFrom.Stock,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  complete = false
)
