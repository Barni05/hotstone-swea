package hotstone.standard.strategies.EtaStone;

import hotstone.framework.MutableCard;
import hotstone.framework.Player;
import hotstone.framework.strategies.DeckBuildingStrategy;
import hotstone.framework.strategies.Effect;
import hotstone.standard.CardEffects.*;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;
import hotstone.standard.utils.IndexSelector;
import hotstone.standard.utils.RandomIndexSelector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class ModifiedDishDeckBuildingStrategy implements DeckBuildingStrategy {
    private final IndexSelector indexSelector;
    public ModifiedDishDeckBuildingStrategy() {
        this(new RandomIndexSelector());
    }

    public ModifiedDishDeckBuildingStrategy(IndexSelector indexSelector) {
        this.indexSelector = indexSelector;
    }

    @Override
    public Stack<MutableCard> createDeck(Player owner){
        Stack<MutableCard> deck = new Stack<>();
        Stack<MutableCard> firstThree = new Stack<>();
        Effect effect = new NoEffect();
        Random random = new Random();
        ArrayList<MutableCard> helperDeck = new ArrayList<>();

        helperDeck.add(createBrownRice(owner));
        helperDeck.add(createBrownRice(owner));
        helperDeck.add(createTomatoSalad(owner));
        helperDeck.add(createTomatoSalad(owner));
        helperDeck.add(createPokeBowl(owner));
        helperDeck.add(createPokeBowl(owner));
        helperDeck.add(createNoodleSoup(owner));
        helperDeck.add(createNoodleSoup(owner));
        helperDeck.add(createSpringRolls(owner));
        helperDeck.add(createSpringRolls(owner));
        helperDeck.add(createBakedSalmon(owner));
        helperDeck.add(createBakedSalmon(owner));


        // Shuffling

        // Choosing the first card
        int index1 = random.nextInt(4);
        firstThree.push(helperDeck.remove(index1));

        // Choosing the second card (guaranteed not equal to index1)
        int index2 = random.nextInt(7);
        firstThree.push(helperDeck.remove(index2));

        // Choosing the third card (guaranteed not equal to first or second
        int index3 = random.nextInt(10);
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

    private MutableCard createBrownRice(Player owner){
        return new StandardCard(GameConstants.BROWN_RICE_CARD, "Deal 1 damage to opponent hero", 1, 1, 1, false, owner, new BrownRiceEffect(owner));
    }

    private MutableCard createTomatoSalad(Player owner){
        return new StandardCard(GameConstants.TOMATO_SALAD_CARD, "Add +1 attack to random minion", 2,2,2, false, owner, new TomatoSaladEffect(owner, indexSelector));
    }

    private MutableCard createPokeBowl(Player owner){
        return new StandardCard(GameConstants.POKE_BOWL_CARD, "Restore +2 health to hero", 3,2,3, false, owner, new PokeBowlEffect(owner));
    }

    private MutableCard createNoodleSoup(Player owner){
        return new StandardCard(GameConstants.NOODLE_SOUP_CARD, "Draw a card", 4,5,3, false, owner,  new NoodleSoupEffect(owner));
    }

    private MutableCard createSpringRolls(Player owner){
        return new StandardCard(GameConstants.SPRING_ROLLS_CARD, "Destroy a random opponent minion", 5,3,5, false, owner, new SpringRollsEffect(owner, indexSelector));
    }

    private MutableCard createBakedSalmon(Player owner){
        return new StandardCard(GameConstants.BAKED_SALMON_CARD, "Add +2 attack to random opponent minion", 5,7,6, false, owner, new BakedSalmonEffect(owner, indexSelector));
    }


}
