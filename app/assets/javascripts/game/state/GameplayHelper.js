define(['utils/Config', 'game/Card', 'game/pile/Pile', 'game/helpers/CardImages'], function(cfg, Card, Pile, CardImages) {
  var GameplayHelper = function(game) {
    this.game = game;
  };

  GameplayHelper.prototype.load = function() {
    this.game.stage.disableVisibilityChange = true;
  };

  GameplayHelper.prototype.sendInitialMessage = function() {
    if(cfg.initialAction === undefined) {
      cfg.initialAction = ["start"];
    }

    switch(cfg.initialAction[0]) {
      case "start":
        if(cfg.seed === undefined) {
          this.game.send("StartGame", {"rules": cfg.rules});
        } else {
          this.game.send("StartGame", {"rules": cfg.rules, "seed": cfg.seed});
        }
        break;
      case "join":
        this.game.send("JoinGame", {"id": cfg.initialAction[1]});
        break;
      case "observe":
        if(cfg.initialAction.length === 2) {
          this.game.send("ObserveGame", {"id": cfg.initialAction[1]});
        } else {
          this.game.send("ObserveGame", {"id": cfg.initialAction[1], "as": cfg.initialAction[2]});
        }
        break;
      default:
        alert("Invalid initial action [" + cfg.initialAction + "].");
    }
  };

  GameplayHelper.prototype.loadCardImages = function() {
    CardImages.init(this.game);
    this.game.cardTextures = CardImages.textures;
  };

  GameplayHelper.prototype.loadPileSets = function(pileSets) {
    this.game.pileSets = pileSets;
    for(var pileSetIndex in pileSets) {
      var pileSet = pileSets[pileSetIndex];
      for(var pileIndex in pileSet.piles) {
        var pile = pileSet.piles[pileIndex];
        var pileObj = new Pile(this.game, pile.id, pileSet, pile.options);
        this.game.playmat.addPile(pileObj);
      }
    }
  };

  GameplayHelper.prototype.loadCards = function(pileSets, originalOrder) {
    var cards = {};

    for(var pileSetIndex in pileSets) {
      var pileSet = pileSets[pileSetIndex];
      for(var pileIndex in pileSet.piles) {
        var pile = pileSet.piles[pileIndex];
        var pileObj = this.game.piles[pile.id];
        for(var cardIndex in pile.cards) {
          var card = pile.cards[cardIndex];
          var cardObj = new Card(this.game, card.id, card.r, card.s, card.u);
          cards[card.id] = [cardObj, pileObj, cardIndex];
        }
      }
    }

    _.each(originalOrder, function(cardId, cardIndex) {
      var c = cards[cardId];
      if(c !== undefined) {
        var cardObj = c[0];
        var pileObj = c[1];
        var cardPileIndex = c[2];
        setTimeout(function() { pileObj.addCard(cardObj, cardPileIndex); }, 30 * cardIndex);
      }
    });

    var game = this.game;
    setTimeout(function() { game.initialMovesComplete(); }, (30 * (originalOrder.length - 1)) + 500);
  };

  GameplayHelper.prototype.moveCard = function(card, src, tgt, turn) {
    var movedCard = this.game.cards[card];
    var source = this.game.piles[src];
    var target = this.game.piles[tgt];

    if(turn === true && !movedCard.faceUp) {
      movedCard.turnFaceUp();
    }
    if(turn === false && movedCard.faceUp) {
      movedCard.turnFaceDown();
    }

    movedCard.bringToTop();
    source.removeCard(movedCard);
    target.addCard(movedCard);
  };
  return GameplayHelper;
});
