define(function () {
  "use strict";
  var Rank = {};
  Rank.two = {
    value: 2, char: "2", name: "Two",
    locs: [[0.5, 0.25], [0.5, 0.75]]
  };
  Rank.three = {
    value: 3, char: "3", name: "Three",
    locs: [[0.5, 0.25], [0.5, 0.5], [0.5, 0.75]]
  };
  Rank.four = {
    value: 4, char: "4", name: "Four",
    locs: [[0.25, 0.25], [0.75, 0.25], [0.25, 0.75], [0.75, 0.75]]
  };
  Rank.five = {
    value: 5, char: "5", name: "Five",
    locs: [[0.25, 0.25], [0.75, 0.25], [0.5, 0.5], [0.25, 0.75], [0.75, 0.75]]
  };
  Rank.six = {
    value: 6, char: "6", name: "Six",
    locs: [[0.25, 0.25], [0.75, 0.25], [0.25, 0.5], [0.75, 0.5], [0.25, 0.75], [0.75, 0.75]]
  };
  Rank.seven = {
    value: 7, char: "7", name: "Seven",
    locs: [[0.25, 0.25], [0.75, 0.25], [0.25, 0.5], [0.5, 0.5], [0.75, 0.5], [0.25, 0.75], [0.75, 0.75]]
  };
  Rank.eight = {
    value: 8, char: "8", name: "Eight",
    locs: [[0.25, 0.2], [0.75, 0.2], [0.25, 0.4], [0.75, 0.4], [0.25, 0.6], [0.75, 0.6], [0.25, 0.8], [0.75, 0.8]]
  };
  Rank.nine = {
    value: 9, char: "9", name: "Nine",
    locs: [[0.25, 0.2], [0.75, 0.2], [0.5, 0.3], [0.25, 0.4], [0.75, 0.4], [0.25, 0.6], [0.75, 0.6], [0.25, 0.8], [0.75, 0.8]]
  };
  Rank.ten = {
    value: 10, char: "X", name: "Ten",
    locs: [[0.25, 0.2], [0.75, 0.2], [0.5, 0.3], [0.25, 0.4], [0.75, 0.4], [0.25, 0.6], [0.75, 0.6], [0.5, 0.7], [0.25, 0.8], [0.75, 0.8]]
  };
  Rank.jack = {
    value: 11, char: "J", name: "Jack", locs: [0.5, 0.5]
  };
  Rank.queen = {
    value: 12, char: "Q", name: "Queen", locs: [0.5, 0.5]
  };
  Rank.king = {
    value: 13, char: "K", name: "King", locs: [0.5, 0.5]
  };
  Rank.ace = {
    value: 14, char: "A", name: "Ace", locs: [0.5, 0.5]
  };
  Rank.unknown = {
    value: 0, char: "?", name: "Unknown", locs: [0.5, 0.5]
  };

  Rank.all = [Rank.two, Rank.three, Rank.four, Rank.five, Rank.six, Rank.seven, Rank.eight, Rank.nine, Rank.ten, Rank.jack, Rank.queen, Rank.king, Rank.ace];

  Rank.fromChar = function(c) {
    switch(c) {
      case '2': return Rank.two;
      case '3': return Rank.three;
      case '4': return Rank.four;
      case '5': return Rank.five;
      case '6': return Rank.six;
      case '7': return Rank.seven;
      case '8': return Rank.eight;
      case '9': return Rank.nine;
      case 'X': return Rank.ten;
      case 'J': return Rank.jack;
      case 'Q': return Rank.queen;
      case 'K': return Rank.king;
      case 'A': return Rank.ace;
      case '?': return Rank.unknown;
      default: throw "Invalid rank [" + c + "].";
    }
  };

  return Rank;
});
