// Generated rules for Scalataire.
package models.game.rules.generated

import models.game._
import models.game.rules._

object Minerva extends GameRules(
  id = "minerva",
  title = "Minerva",
  like = Some("athena"),
  related = Seq("doubleminerva", "munger", "mystique", "tripleminerva"),
  description = "^athena^ with a ^canfield^-style reserve added.",
  stock = Some(
    StockRules(
      maximumDeals = Some(2)
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
      initialCards = InitialCards.Count(4),
      cardsFaceDown = TableauFaceDownCards.OddNumbered,
      emptyFilledWith = TableauFillEmptyWith.Kings
    )
  ),
  reserves = Some(
    ReserveRules(
      name = "Reserve",
      numPiles = 1,
      initialCards = 11,
      cardsFaceDown = 0
    )
  ),
  complete = false
)
