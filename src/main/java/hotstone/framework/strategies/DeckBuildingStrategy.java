package hotstone.framework.strategies;

import hotstone.framework.Card;
import hotstone.framework.MutableCard;
import hotstone.framework.Player;

import java.util.Stack;

public interface DeckBuildingStrategy {
    Stack<MutableCard> createDeck(Player owner);
}
