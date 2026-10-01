package hotstone.framework.strategies;

import hotstone.framework.Card;
import hotstone.framework.Player;

import java.util.Stack;

public interface DeckBuildingStrategy {
    Stack<Card> createDeck(Player owner);
}
