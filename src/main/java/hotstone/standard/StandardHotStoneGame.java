/*
 * Copyright (C) 2022 - 2026. Henrik Bærbak Christensen, Aarhus University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hotstone.standard;

import hotstone.framework.*;
import hotstone.framework.strategies.*;

//import java.lang.foreign.PaddingLayout;
import java.util.Stack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

/**
 * This is the 'temporary test stub' in TDD
 * terms: the initial empty but compilable implementation
 * of the game interface.
 * <p>
 * It already includes a bit of FAKE-IT code for the first
 * test case about hand management and player in turn.
 * <p>
 * Start solving the AlphaStone exercise by
 * following the TDD rhythm: pick a one-step-test
 * from your test list, quickly add a test,
 * run it to see it fail, and then modify this
 * implementing class (and supporting classes)
 * to make your test case run. Refactor and repeat.
 * <p>
 * While this is the implementation of Game for
 * the AlphaStone game, you will constantly
 * refactor it over the course of the exercises
 * to become the 'core implementation' which will
 * enable a lot of game variants. This is also
 * why it is not called 'AlphaGame'.
 */

public class StandardHotStoneGame implements Game, MutableGame {
    private final int NO_MORE_CARD_PENALTY = 2;

    private HashMap<Player, Stack<MutableCard>> decks = new HashMap<>();
    private HashMap<Player, ArrayList<MutableCard>> hands = new HashMap<>();
    private HashMap<Player, ArrayList<MutableCard>> fields = new HashMap<>();
    private int turnNum;
    private Player playerInTurn;
    private HashMap<Player, StandardHero> heroes = new HashMap<>();

    // Strategies
    ManaProductionStrategy manaProductionStrategy;
    WinningStrategy winningStrategy;
    HeroCreationStrategy heroCreationStrategy;
    HeroPowerStrategy heroPowerStrategy;
    DeckBuildingStrategy deckBuildingStrategy;

    public StandardHotStoneGame(HotStoneFactory hotStoneFactory)    {

        // Assigning the strategies
        this.manaProductionStrategy = hotStoneFactory.createManaProductionStrategy();
        this.winningStrategy = hotStoneFactory.createWinningStrategy();
        this.heroCreationStrategy = hotStoneFactory.createHeroCreationStrategy();
        this.heroPowerStrategy = hotStoneFactory.createHeroPowerStrategy();
        this.deckBuildingStrategy = hotStoneFactory.createDeckBuildingStrategy();

        decks.put(Player.FINDUS, new Stack<>());
        decks.put(Player.PEDDERSEN, new Stack<>());

        hands.put(Player.FINDUS, new ArrayList<>());
        hands.put(Player.PEDDERSEN, new ArrayList<>());

        //Initializing both player's deck with the 7 cards
        decks.replace(Player.FINDUS, deckBuildingStrategy.createDeck(Player.FINDUS));
        decks.replace(Player.PEDDERSEN, deckBuildingStrategy.createDeck(Player.PEDDERSEN));

        //Initializing game control variables
        turnNum = 0;
        playerInTurn = Player.FINDUS;

        //Draw 3 cards for each player
        for (int i = 0; i < 3; i++) {
            drawCard(Player.FINDUS);
            drawCard(Player.PEDDERSEN);
        }

        //Initialize fields
        for (Player player : Player.values()) {
            fields.put(player, new ArrayList<>(Collections.nCopies(FIELD_CAPACITY, null)));
        }

        //Initialize mana

        //Initialize hero
        createHeroes();

        //Initialize health
    }

    private void createHeroes() {
        Hero[] temp = heroCreationStrategy.createHeroes(manaProductionStrategy.calculateStartRoundMana(this, Player.FINDUS), manaProductionStrategy.calculateStartRoundMana(this,Player.PEDDERSEN));
        heroes.put(Player.FINDUS, (StandardHero) temp[0]);
        heroes.put(Player.PEDDERSEN, (StandardHero) temp[1]);
    }
    @Override
    public int calculateStartRoundMana(Player who){
        return manaProductionStrategy.calculateStartRoundMana(this, who);
    }

    /**
     * Draws one card from the given player's deck to his hand
     *
     * @param who the drawing player
     * @return status of operation
     */
    @Override
    public Status drawCard(Player who) {
        if (!decks.get(who).isEmpty()) {
            MutableCard drawnCard = decks.get(who).pop();
            hands.get(who).addFirst(drawnCard);
            return Status.OK;
        } else {
            StandardHero hero = (StandardHero) getHero(who);
            int newHealth = hero.getHealth() - NO_MORE_CARD_PENALTY;
            if (newHealth <= 0)
                return Status.NO_MORE_HEALTH;
            hero.setHealth(newHealth);
            return Status.NO_MORE_CARDS;
        }
    }

    @Override
    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    @Override
    public Hero getHero(Player who) {
        return heroes.get(who);
    }

    @Override
    //since we already know that (for this iteration) Findus will always win
    public Player getWinner() {
        return winningStrategy.calculateWinner(this);
    }

    @Override
    public int getTurnNumber() {
        return turnNum;
    }

    @Override
    public int getDeckSize(Player who) {
        return decks.get(who).size();
    }

    /*
     * It's unlucky that HashMap and ArrayList also has a get() method,
     * but we don't always have to use the ternary operator for such calculations
     * */
    @Override
    public MutableCard getCardInHand(Player who, int indexInHand) {
        return hands.get(who).get(indexInHand);
    }

    @Override
    public Iterable<? extends MutableCard> getHand(Player who) {
        return hands.get(who);
    }

    @Override
    public int getHandSize(Player who) {
        return hands.get(who).size();
    }

    @Override
    public Card getCardInField(Player who, int indexInField) {
        return fields.get(who).get(indexInField);
    }


    @Override
    public MutableCard getMutableCardInField(Player who, int indexInField ){return fields.get(who).get(indexInField);}
    @Override
    public Iterable<? extends MutableCard> getField(Player who) {
        return fields.get(who);
    }

    @Override
    public int getFieldSize(Player who) {
        return fields.get(who).size();
    }

    @Override
    public void endTurn() {
        turnNum++;
        Player currentPlayer = playerInTurn;
        //Setting the previous player's cards inactive
        for (MutableCard c : fields.get(currentPlayer)) {
            if(c != null)
                c.setActive(false);
        }
        //Switching who the player in turn is
        playerInTurn = currentPlayer == Player.FINDUS ? Player.PEDDERSEN : Player.FINDUS;

        setCurrentCardsActive();

        //Draw cards from deck
        drawCard(Player.FINDUS);
        drawCard(Player.PEDDERSEN);

        //Resetting mana
        heroes.get(Player.FINDUS).setMana(manaProductionStrategy.calculateStartRoundMana(this, Player.FINDUS));
        heroes.get(Player.PEDDERSEN).setMana(manaProductionStrategy.calculateStartRoundMana(this, Player.PEDDERSEN));

        executeAttacks();

    }

    private void setCurrentCardsActive() {
        for (Card c : fields.get(playerInTurn)) {
            StandardCard standardCard = (StandardCard) c;
            if (standardCard != null)
                standardCard.setActive(true);
        }
    }
    @Override
    public void executeAttacks() {
        Player attackingPlayer = playerInTurn;
        ArrayList<MutableCard> defendingPlayerField = fields.get(Player.computeOpponent(playerInTurn));
        int currentCardIndex = 0;
        for (MutableCard attackingCard : fields.get(attackingPlayer)) {
            if (attackingCard != null && attackingCard.isActive()) {
                if (defendingPlayerField.get(currentCardIndex) != null)
                    attackCard(attackingPlayer, attackingCard, defendingPlayerField.get(currentCardIndex));
                else attackHero(attackingPlayer, attackingCard);
            }
            currentCardIndex++;
        }
    }

    @Override
    public Status playCard(Player who, Card card, int atIndex) {
        MutableCard mutableCard = (MutableCard) card;
        if (who != playerInTurn)
            return Status.NOT_PLAYER_IN_TURN;
        if (mutableCard.getOwner() != who)
            return Status.NOT_OWNER;
        //Draw mana
        int newManaValue = heroes.get(who).getMana() - mutableCard.getManaCost();
        if (newManaValue < 0)
            return Status.NOT_ENOUGH_MANA;
        heroes.get(who).setMana(newManaValue);
        //Takes out of hand
        if (hands.get(who).remove(mutableCard)) {
            //Insert into field
            insertCardIntoField(who, mutableCard, atIndex);
            return Status.OK;
        }
        return Status.CARD_NOT_FOUND;
    }
    @Override
    public void insertCardIntoField(Player who, MutableCard card, int atIndex) {
        fields.get(who).set(atIndex, card);
    }

    @Override
    public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
        MutableCard mutableAttacking = (MutableCard) attackingCard;
        MutableCard mutableDefending = (MutableCard) defendingCard;
        if (!attackingCard.isActive())
            return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION;

        //Check if either card has died
        if (mutableDefending.deltaHealth(mutableAttacking.getAttack()) == Status.NO_MORE_HEALTH) {
            //Remove card from existence
            removeCardFromField(Player.computeOpponent(playerAttacking), mutableDefending);
        }
        if (mutableAttacking.deltaHealth(mutableDefending.getAttack()) == Status.NO_MORE_HEALTH) {
            //Remove card from existence
            removeCardFromField(playerAttacking, mutableAttacking);
        }
        return Status.OK;
    }
    @Override
    public ArrayList<MutableCard> getPlayersMinions(Player who){
        ArrayList<MutableCard> ret = new ArrayList<>();
        for(MutableCard c : getField(who)){
            if(c != null)
                ret.add(c);
        }
        return ret;
    }

    @Override
    public Status removeCardFromField(Player who, Card cardToRemove) {
        int index = fields.get(who).indexOf(cardToRemove);
        if (index != -1) {
            fields.get(who).set(index, null);
            return Status.OK;
        }
        return Status.CARD_NOT_FOUND;
    }

    @Override
    public Status attackHero(Player playerAttacking, Card attackingCard) {
        return heroTakeDamage(Player.computeOpponent(playerAttacking), attackingCard.getAttack());
    }

    @Override
    public Status heroTakeDamage(Player who, int damageAmount) {
        StandardHero damagedHero = (StandardHero) getHero(who);
        damagedHero.setHealth(damagedHero.getHealth() - damageAmount);
        if (damagedHero.getHealth() <= 0) {
            return Status.NO_MORE_HEALTH;
        }
        return Status.OK;
    }
    @Override
    public Status usePower(Player who) {
        Status ret = heroPowerStrategy.usePower(this, who);
        return ret;
    }
}