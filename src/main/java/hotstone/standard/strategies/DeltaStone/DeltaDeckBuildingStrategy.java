package hotstone.standard.strategies.DeltaStone;

import hotstone.framework.Card;
import hotstone.framework.DeckBuildingStrategy;
import hotstone.framework.Player;
import hotstone.standard.StandardCard;

import java.util.Stack;

public class DeltaDeckBuildingStrategy implements DeckBuildingStrategy {
    @Override
    public Stack<Card> createDeck(Player owner) {
        Stack<Card> deck = new Stack<>();
        for (int i = 0; i<2; i++) {
            deck.push(new StandardCard("Brown rice", "", 1, 1, 2, false, owner));
            deck.push(new StandardCard("French fries", "", 1, 2, 1, false, owner));
            deck.push(new StandardCard("Green salad", "", 2, 2, 3, false, owner));
            deck.push(new StandardCard("Tomato salad", "", 2, 3, 2, false, owner));
            deck.push(new StandardCard("Poke bowl", "", 3, 2, 4, false, owner));
            deck.push(new StandardCard("Pumpkin soup", "", 4, 2, 7, false, owner));
            deck.push(new StandardCard("Noodle soup", "", 4, 5, 3, false, owner));
            deck.push(new StandardCard("Spring rolls", "", 5, 3, 7, false, owner));
            deck.push(new StandardCard("Baked salmon", "", 5, 8, 2, false, owner));
        }
        return deck;
    }

    public Stack<Card> shufflingDeck(Player owner) {
        Stack<Card> deck = createDeck(owner);
        deck.sort();
        Stack<Card> deckOf1s = new Stack<>();
        deckOf1s.add;
    }
}
