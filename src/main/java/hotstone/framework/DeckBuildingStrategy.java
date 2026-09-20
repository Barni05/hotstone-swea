package hotstone.framework;

import java.util.Stack;

public interface DeckBuildingStrategy {
    Stack<Card> createDeck(Player owner);
}
