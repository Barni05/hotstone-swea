package hotstone.standard.strategies.DeltaStone;

import hotstone.framework.MutableCard;
import hotstone.framework.strategies.DeckBuildingStrategy;
import hotstone.framework.Player;
import hotstone.standard.StandardCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.Random;

public class DeltaDeckBuildingStrategy implements DeckBuildingStrategy {
    @Override
    public Stack<MutableCard> createDeck(Player owner) {
        Stack<MutableCard> deck = new Stack<>();
        Stack<MutableCard> firstThree = new Stack<>();
        Random random = new Random();
        ArrayList<MutableCard> helperDeck = new ArrayList<>();
        Object[][] cardStats = {
                {"Brown Rice", 1, 1, 2},
                {"French Fries", 1, 2, 1},
                {"Green Salad", 2, 2, 3},
                {"Tomato Salad", 2, 3, 2},
                {"Poke Bowl", 3, 2, 4},
                {"Pumpkin Soup", 4, 2, 7},
                {"Noodle Soup", 4, 5, 3},
                {"Spring Rolls", 5, 3, 7},
                {"Baked Salmon", 5, 8, 2}
        };

        for (Object[] stat : cardStats) {
            for (int i = 0; i < 2; i++) {
                helperDeck.add(new StandardCard(
                        (String) stat[0],
                        "",
                        (int) stat[1],
                        (int) stat[2],
                        (int) stat[3],
                        false,
                        owner
                ));
            }
        }

        // Shuffling

        // Choosing the first card
        int index1 = random.nextInt(4);
        firstThree.push(helperDeck.remove(index1));

        // Choosing the second card (guaranteed not equal to index1)
        int index2 = random.nextInt(7);
        firstThree.push(helperDeck.remove(index2));

        // Choosing the third card (guaranteed not equal to first or second
        int index3 = random.nextInt(11);
        firstThree.push(helperDeck.remove(index3));

        Collections.shuffle(helperDeck);
        for(MutableCard card : helperDeck){
            deck.push(card);
        }
        deck.push(firstThree.pop());
        deck.push(firstThree.pop());
        deck.push(firstThree.pop());

        return deck;
    }
}
