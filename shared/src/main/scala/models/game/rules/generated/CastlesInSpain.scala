// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object CastlesInSpain extends GameRules(
  id = "castlesinspain",
  title = "Castles in Spain",
  like = Some("spanish"),
  description = "A variant of ^bakersdozen^ that allows filling in spaces with any card and where we build in alternate colors.",
  foundations = Seq(
    FoundationRules(
      numPiles = 4,
      wrapFromKingToAce = true,
      autoMoveCards = true
    )
  ),
  tableaus = Seq(
    TableauRules(
      numPiles = 13,
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.Count(0),
      suitMatchRuleForMovingStacks = SuitMatchRule.None,
      emptyFilledWith = TableauFillEmptyWith.Aces
    )
  ),
  complete = false
)
