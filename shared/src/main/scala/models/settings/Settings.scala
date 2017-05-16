package models.settings

object Settings {
  val default = Settings()
}

case class Settings(
  backgroundColor: String = "#607d8b",
  backgroundPattern: Option[String] = None,
  cardSet: CardSet = CardSet.Default,
  cardBack: CardBack = CardBack.A,
  cardBlank: CardBlank = CardBlank.A,
  cardFaces: CardFaces = CardFaces.A,
  cardLayout: CardLayout = CardLayout.A,
  cardRanks: CardRanks = CardRanks.A,
  cardSuits: CardSuits = CardSuits.A,
  menuPosition: MenuPosition = MenuPosition.Top,
  autoFlip: Boolean = true
)
