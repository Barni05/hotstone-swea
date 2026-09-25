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

//import java.lang.foreign.PaddingLayout;
import java.util.Stack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

/** This is the 'temporary test stub' in TDD
 * terms: the initial empty but compilable implementation
 * of the game interface.
 *
 * It already includes a bit of FAKE-IT code for the first
 * test case about hand management and player in turn.
 *
 * Start solving the AlphaStone exercise by
 * following the TDD rhythm: pick a one-step-test
 * from your test list, quickly add a test,
 * run it to see it fail, and then modify this
 * implementing class (and supporting classes)
 * to make your test case run. Refactor and repeat.
 *
 * While this is the implementation of Game for
 * the AlphaStone game, you will constantly
 * refactor it over the course of the exercises
 * to become the 'core implementation' which will
 * enable a lot of game variants. This is also
 * why it is not called 'AlphaGame'.
 */

public class StandardHotStoneGame implements Game {
    public int FIELD_CAPACITY = 10;
    private final int NO_MORE_CARD_PENALTY = 2;

    private HashMap<Player, Stack<Card>> decks = new HashMap<>();
    private HashMap<Player, ArrayList<Card>> hands = new HashMap<>();
    private HashMap<Player, ArrayList<Card>> fields = new HashMap<>();
    private int turnNum;
    private Player playerInTurn;
    private HashMap<Player, StandardHero> heroes = new HashMap<>();

    // Strategies
    ManaProductionStrategy manaProductionStrategy;
    WinningStrategy winningStrategy;
    HeroCreationStrategy heroCreationStrategy;
    HeroPowerStrategy heroPowerStrategy;
    DeckBuildingStrategy deckBuildingStrategy;

    public StandardHotStoneGame(ManaProductionStrategy manaProductionStrategy, WinningStrategy winningStrategy, HeroCreationStrategy heroCreationStrategy,  HeroPowerStrategy heroPowerStrategy, DeckBuildingStrategy deckBuildingStrategy) {

        // Assigning the strategies
        this.manaProductionStrategy = manaProductionStrategy;
        this.winningStrategy = winningStrategy;
        this.heroCreationStrategy = heroCreationStrategy;
        this.heroPowerStrategy = heroPowerStrategy;
        this.deckBuildingStrategy = deckBuildingStrategy;

        decks.put(Player.FINDUS, new Stack<Card>());
        decks.put(Player.PEDDERSEN, new Stack<Card>());

        hands.put(Player.FINDUS, new ArrayList<Card>());
        hands.put(Player.PEDDERSEN, new ArrayList<Card>());

        //Initializing both player's deck with the 7 cards
        decks.replace(Player.FINDUS, deckBuildingStrategy.createDeck(Player.FINDUS));
        decks.replace(Player.PEDDERSEN, deckBuildingStrategy.createDeck(Player.PEDDERSEN));

        //Initializing game control variables
        turnNum = 0;
        playerInTurn = Player.FINDUS;

        //Draw 3 cards for each player
        for(int i = 0; i < 3; i ++){
            drawCard(Player.FINDUS);
            drawCard(Player.PEDDERSEN);
        }

        //Initialize fields
        for (Player player : Player.values()) {
            fields.put(player, new ArrayList<>(Collections.nCopies(FIELD_CAPACITY, null)));
        }

        //Initialize mana

        //Initialize hero
        manaProductionStrategy.setGame(this);
        createHeroes();

        //Initialize health
    }

    private void createHeroes(){
        Hero[] temp = heroCreationStrategy.createHeroes(manaProductionStrategy.calculateStartRoundMana(Player.FINDUS), manaProductionStrategy.calculateStartRoundMana(Player.PEDDERSEN));
        heroes.put(Player.FINDUS, (StandardHero)temp[0]);
        heroes.put(Player.PEDDERSEN, (StandardHero)temp[1]);
    }


    /**
     * Draws one card from the given player's deck to his hand
     * @param who the drawing player
     * @return status of operation
     */
    public Status drawCard(Player who){
        if(decks.get(who).size() > 0){
            Card drawnCard = decks.get(who).pop();
            hands.get(who).addFirst(drawnCard);
            return Status.OK;
        }else{
            StandardHero hero = (StandardHero) getHero(who);
            int newHealth = hero.getHealth() - NO_MORE_CARD_PENALTY;
            if(newHealth <= 0)
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


  public int getRoundNumber(){
        return getTurnNumber()/2+1;
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
  public Card getCardInHand(Player who, int indexInHand) {
    return hands.get(who).get(indexInHand);
  }

  @Override
  public Iterable<? extends Card> getHand(Player who) {
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
  public Iterable<? extends Card> getField(Player who) {
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
        for(Card c :  fields.get(currentPlayer)){
            StandardCard standardCard = (StandardCard) c;
            if(standardCard != null)
                standardCard.setActive(false);
        }
        //Switching who the player in turn is
        playerInTurn = currentPlayer == Player.FINDUS ? Player.PEDDERSEN : Player.FINDUS;
  }

  public void startTurn(){
      //Set current players card active
      for(Card c :  fields.get(playerInTurn)){
          StandardCard standardCard = (StandardCard) c;
          if(standardCard != null)
              standardCard.setActive(true);
      }


      //Draw cards from deck
      drawCard(Player.FINDUS);
      drawCard(Player.PEDDERSEN);

      //Resetting mana
      heroes.get(Player.FINDUS).setMana(manaProductionStrategy.calculateStartRoundMana(Player.FINDUS));
      heroes.get(Player.PEDDERSEN).setMana(manaProductionStrategy.calculateStartRoundMana(Player.PEDDERSEN));

      executeAttacks();
  }

  public void executeAttacks(){
      Player attackingPlayer = playerInTurn;
      ArrayList<Card> defendingPlayerField = fields.get(Player.computeOpponent(playerInTurn));
      int currentCardIndex = 0;
      for(Card attackingCard : fields.get(attackingPlayer)){
          if(attackingCard != null && attackingCard.isActive()) {
              if (defendingPlayerField.get(currentCardIndex) != null)
                  attackCard(attackingPlayer, attackingCard, defendingPlayerField.get(currentCardIndex));
              else attackHero(attackingPlayer, attackingCard);
              currentCardIndex++;
          }
      }
  }

  @Override
  public Status playCard(Player who, Card card, int atIndex) {
      if(who != playerInTurn)
          return Status.NOT_PLAYER_IN_TURN;
      if(card.getOwner() != who)
          return Status.NOT_OWNER;
      //Draw mana
      int newManaValue = heroes.get(who).getMana()-card.getManaCost();
      if(newManaValue < 0)
          return Status.NOT_ENOUGH_MANA;
      heroes.get(who).setMana(newManaValue);
    //Takes out of hand
    if(hands.get(who).remove(card)){
        //Insert into field
        insertCardIntoField(who, card, atIndex);
        return Status.OK;
    }
    return Status.CARD_NOT_FOUND;
  }

  public void insertCardIntoField(Player who, Card card, int atIndex) {
      fields.get(who).add(atIndex, card);
  }

  @Override
  public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
      StandardCard standardDefending = (StandardCard) defendingCard;
      StandardCard standardAttacking = (StandardCard) attackingCard;
    if(!standardAttacking.isActive())
        return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION;
    //Attack Defending card
    standardDefending.setHealth(standardDefending.getHealth()-standardAttacking.getAttack());

    //Defending card attacks back
      standardAttacking.setHealth(standardAttacking.getHealth()-standardDefending.getAttack());
    //Check if either card has died
    if(standardDefending.getHealth() <= 0){
        //Remove card from existence
        removeCardFromField(Player.computeOpponent(playerAttacking), defendingCard);
    }
    if(standardAttacking.getHealth() <= 0){
      //Remove card from existence
      removeCardFromField(playerAttacking, attackingCard);
    }
    return Status.OK;
  }

  public Status removeCardFromField(Player who, Card cardToRemove){
      int index = fields.get(who).indexOf(cardToRemove);
      if (index != -1) {
          fields.get(who).set(index, null);
          return Status.OK;
      }
      return Status.CARD_NOT_FOUND;
  }

  @Override
  public Status attackHero(Player playerAttacking, Card attackingCard) {
      return takeDamage(Player.computeOpponent(playerAttacking), attackingCard.getAttack());
  }

  public Status takeDamage(Player who, int damageAmount){
      StandardHero damagedHero = (StandardHero)getHero(who);
      damagedHero.setHealth(damagedHero.getHealth() - damageAmount);
      if(damagedHero.getHealth() <= 0)
      {
          return Status.NO_MORE_HEALTH;
      }
      return Status.OK;
  }

  public void afterCrystalDraw (Player who) {
      Status usePower = usePower(who);
      int totalMana = heroes.get(who).getMana();
      if (usePower == Status.OK) totalMana = totalMana -2;
      heroes.get(who).setMana(totalMana);
  }

  @Override
  public Status usePower(Player who) {
    Status ret = heroPowerStrategy.usePower(this, who);
    return ret;
  }
}