// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Arizona extends GameRules(
  id = "arizona",
  title = "Arizona",
  related = Seq("phoenix"),
  description = "An easier varition of ^wildflower^ where you can move sequences regardless of suit.",
  waste = Some(
    WasteRules(
      name = "Reserve"
    )
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 6,
      initialCards = InitialCards.Count(6),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.Any,
      suitMatchRuleForMovingStacks = SuitMatchRule.Any,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)
