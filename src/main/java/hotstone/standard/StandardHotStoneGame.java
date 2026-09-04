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
    private final int FIELD_CAPACITY = 10;

    private HashMap<Player, Stack<Card>> decks = new HashMap<>();
    private HashMap<Player, ArrayList<Card>> hands = new HashMap<>();
    private HashMap<Player, ArrayList<Card>> fields = new HashMap<>();
    private int turnNum;
    private Player playerInTurn;
    private StandardHero baby;

    public StandardHotStoneGame() {

        decks.put(Player.FINDUS, new Stack<Card>());
        decks.put(Player.PEDDERSEN, new Stack<Card>());

        hands.put(Player.FINDUS, new ArrayList<Card>());
        hands.put(Player.PEDDERSEN, new ArrayList<Card>());

        //Initializing both player's deck with the 7 cards
        decks.replace(Player.FINDUS, createStandardDeck(Player.FINDUS));
        decks.replace(Player.PEDDERSEN, createStandardDeck(Player.PEDDERSEN));

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
        createBaby(Player.FINDUS);
        createBaby(Player.PEDDERSEN);

        //Initialize health
    }

    public Stack<Card> createStandardDeck(Player owner){
        Stack<Card> deck = new Stack<>();
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

    public StandardHero createBaby(Player owner){
        baby = new StandardHero(30, 3, "Baby", false,
                owner, "Cute","Just cute");
        return baby;
    }

    public void startTurn(){

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
            return Status.NO_MORE_CARDS;
        }
    }

  @Override
  public Player getPlayerInTurn() {
    return playerInTurn;
  }

  @Override
  public Hero getHero(Player who) {
      return baby;
  }

  @Override
  //since we already know that (for this iteration) Findus will always win
  public Player getWinner() {return Player.FINDUS;}

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
        playerInTurn = playerInTurn == Player.FINDUS ? Player.PEDDERSEN : Player.FINDUS;
  }
  @Override
  public Status playCard(Player who, Card card, int atIndex) {
    //Takes out of hand
    if(hands.get(who).remove(card)){
        //Insert into field
        fields.get(who).add(atIndex, card);
        return Status.OK;
    }
    return Status.CARD_NOT_FOUND;
  }

  @Override
  public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
    return null;
  }

  @Override
  public Status attackHero(Player playerAttacking, Card attackingCard) {
    return null;
  }

  public void afterCrystalDraw (Player who) {
      Status usePower = usePower(who);
      int totalMana = baby.getMana();
      if (usePower == Status.OK) totalMana = totalMana -2;
      baby.setMana(totalMana);
  }

  @Override
  public Status usePower(Player who) {
      Hero baby = getHero(who);
      int mana = baby.getMana();
      if (mana >= 2) return Status.OK;
      return Status.NOT_ENOUGH_MANA;
  }
}