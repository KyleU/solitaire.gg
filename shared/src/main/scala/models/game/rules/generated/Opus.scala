// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Opus extends GameRules(
  id = "opus",
  title = "Opus",
  description = "Thomas Warfield's much more difficult version of ^penguin^ has two fewer cells",
  deckOptions = DeckOptions(
    lowRank = Some(Rank.Unknown)
  ),
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      initialCards = 3,
      wrapFromKingToAce = true,
      canMoveFrom = FoundationCanMoveFrom.Never,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      initialCards = InitialCards.Count(7),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForBuilding = SuitMatchRule.SameSuit,
      suitMatchRuleForMovingStacks = SuitMatchRule.SameSuit,
      emptyFilledWith = TableauFillEmptyWith.Kings,
      pilesWithLowCardsAtBottom = 1
    )
  ),
  cells = Some(
    CellRules(
      numPiles = 5
    )
  ),
  complete = false
)

