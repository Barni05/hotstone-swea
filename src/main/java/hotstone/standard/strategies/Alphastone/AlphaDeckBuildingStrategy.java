package hotstone.standard.strategies.Alphastone;

import hotstone.framework.*;
import hotstone.framework.strategies.DeckBuildingStrategy;
import hotstone.standard.StandardCard;

import java.util.Stack;

public class AlphaDeckBuildingStrategy implements DeckBuildingStrategy {
    @Override
    public Stack<MutableCard> createDeck(Player owner){
        Stack<MutableCard> deck = new Stack<>();
        //We have to put it in reversed order, so that the Uno card is drawn first
        deck.push(new StandardCard("Siete", "It's just Seven", 3, 2, 4, false, owner));
        deck.push(new StandardCard("Seis", "It's just Six", 2, 1, 3, false, owner));
        deck.push(new StandardCard("Cinco", "It's just Five", 3, 5, 1, false, owner));
        deck.push(new StandardCard("Cuatro", "It's just Four", 2, 3, 1, false, owner));
        deck.push(new StandardCard("Tres", "It's just Three", 3, 3, 3, false, owner));
        deck.push(new StandardCard("Dos", "It's just Two", 2, 2, 2, false, owner));
        deck.push(new StandardCard("Uno", "It's just One", 1, 1, 1, false, owner));
        return deck;
    }
}
