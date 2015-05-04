// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Acquaintance extends GameRules(
  id = "acquaintance",
  title = "Acquaintance",
  like = Some("auldlangsyne"),
  related = Seq("quadrennial"),
  description = "A variation of ^auldlangsyne^ suggested by Michael Keller that adds some interest by allowing two redeals.",
  stock = Some(
    StockRules(
      dealTo = StockDealTo.Tableau,
      maximumDeals = Some(1)
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 4,
      suitMatchRule = SuitMatchRule.Any,
      wrapFromKingToAce = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      name = "Reserve",
      numPiles = 4,
      initialCards = InitialCards.Count(1),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.None,
      suitMatchRuleForMovingStacks = SuitMatchRule.None
    )
  ),
  special = Some(
    SpecialRules(
      redealsAllowed = 2,
      shuffleBeforeRedeal = false
    )
  ),
  complete = false
)
