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

/**
 * Skeleton class for AlphaStone test cases
 *
 *    This source code is from the book
 *      "Flexible, Reliable Software:
 *        Using Patterns and Agile Development"
 *      2nd Edition
 *    Author:
 *      Henrik Bærbak Christensen
 *      Department of Computer Science
 *      Aarhus University
 */

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.standard.strategies.AlphaHeroCreationStrategy;
import hotstone.standard.strategies.AlphaHeroPowerStrategy;
import hotstone.standard.strategies.AlphaManaProductionStrategy;
import hotstone.standard.strategies.AlphaWinningStrategy;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import hotstone.framework.Game;

/** Template for your own ongoing TDD process.
 * Fill it out until you have covered all
 * requirements for the alpha stone game.
 */
public class TestAlphaStone {
  private Game game;
  private final int EXPECTED_INITIAL_DECK_SIZE = 4, EXPECTED_INITIAL_HAND_SIZE = 3, MAXIMUM_DRAW = 4,
          MAXIMUM_HEALTH = 21, EXPECTED_MANA = 3;


  /** Fixture for AlphaStone testing. */
  @BeforeEach
  public void setUpAlphastone() {
    game = new StandardHotStoneGame(new AlphaManaProductionStrategy(), new AlphaWinningStrategy(), new AlphaHeroCreationStrategy(), new AlphaHeroPowerStrategy());
  }

  // Example of an early, simple test case:
  // Turn handling
  @Test
  public void shouldHaveFindusAsFirstPlayer() {
    // Given a game
    // When I ask for the player in turn
    Player player = game.getPlayerInTurn();
    // Then it should be Findus
    assertThat(player, is(Player.FINDUS));

    // Later on, the 'printGameState' can be very handy during development
    // as you can inspect the state of the game (the Given in GWT) and
    // ensure your assumptions are correct before modifying it (When)
    // to test a new feature (Then)

    // TestHelper.printGameState(game);
  }


  // Example of a later, more complex, test case:
  // Card handling

  // The HotStone specs are quite insisting on how
  // the cards, drawn from the deck, are organized
  // in the hand. So when drawing the top three cards
  // from the deck (uno, dos, tres) they have to
  // be organized in the hand as
  // index 0 = tres; index 1 = dos; index 2 = uno
  // That is, a newly drawn card is 'at the top'
  // of the hand - always entered at position 0
  // and pushing the rest of the cards 1 position
  // 'down'

  //---------------DECK TESTS------------

  @Test
  public void shouldHaveUnoDosTresCardsInitially() {
    // Given a game, Findus has 3 cards in hand
    int count = game.getHandSize(Player.FINDUS);
    assertThat(count, is(EXPECTED_INITIAL_HAND_SIZE));
    // And these are ordered Tres, Dos, Uno in slot 0,1,2

    Card cardTres = game.getCardInHand(Player.FINDUS, 0);
    assertThat(cardTres.getName(), is(GameConstants.TRES_CARD));
    Card cardDos = game.getCardInHand(Player.FINDUS, 1);
    assertThat(cardDos.getName(), is(GameConstants.DOS_CARD));
    Card cardUno = game.getCardInHand(Player.FINDUS, 2);
    assertThat(cardUno.getName(), is(GameConstants.UNO_CARD));
  }


  @Test
  public void shouldHave4CardsInDeckInitiallyForBothPlayers(){
      int countFindus = game.getDeckSize(Player.FINDUS);
      assertThat(countFindus, is(EXPECTED_INITIAL_DECK_SIZE));
      int countPeddersen = game.getDeckSize(Player.PEDDERSEN);
      assertThat(countPeddersen, is(EXPECTED_INITIAL_DECK_SIZE));
  }

  @Test
  public void shouldHandBeInactive(){
    for(Card c : game.getHand(Player.FINDUS)){
      assertThat(c.isActive(), is(false));
    }
    for(Card c : game.getHand(Player.PEDDERSEN)){
      assertThat(c.isActive(), is(false));
    }
  }

  @Test
  public void shouldHaveStats222ForDosCard(){
    //Game hasn't started, so we know that either player's card in hand of index 1 is Dos
    Card Dos = game.getCardInHand(Player.FINDUS, 1);
    assertThat(Dos.getManaCost(), is(2));
    assertThat(Dos.getHealth(), is(2));
    assertThat(Dos.getAttack(), is(2));
  }

  @Test
  public void shouldDecreaseDeckSizeBy1AndIncreaseHand(){
    int deckSizeBefore = game.getDeckSize(Player.FINDUS);
    int handSizeBefore = game.getHandSize(Player.FINDUS);
    StandardHotStoneGame standardGame = (StandardHotStoneGame) game;
    standardGame.drawCard(Player.FINDUS);
    assertThat(standardGame.getHandSize(Player.FINDUS), is(handSizeBefore+1));
    assertThat(standardGame.getDeckSize(Player.FINDUS), is(deckSizeBefore-1));
  }

  @Test
  public void shouldPlayedCardBeOnRightIndex(){
    Card card = game.getCardInHand(Player.FINDUS, 0);
    assertThat(card, not(nullValue()));
    game.playCard(Player.FINDUS, card, 1);
    assertThat(game.getCardInField(Player.FINDUS, 1), not(nullValue()));
  }
@Test
public void shoudReturnNoMoreCards(){
    StandardHotStoneGame standardGame = (StandardHotStoneGame) game;
    for(int i = 0; i < MAXIMUM_DRAW; i++){
      standardGame.drawCard(Player.FINDUS);
    }
    Status status = standardGame.drawCard(Player.FINDUS);
    assertThat(status, is(Status.NO_MORE_CARDS));
}

@Test
public void shouldBeNewCardOnOldPositionInHand(){
    Card oldCard = game.getCardInHand(Player.FINDUS, 0);
    game.playCard(Player.FINDUS, oldCard, 0);
    Card newCard = game.getCardInHand(Player.FINDUS, 0);
    assertThat(oldCard, not(newCard));
}


@Test
public void shouldMinionBeInactive(){
    game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 0), 1);
    boolean isActive = game.getCardInField(Player.FINDUS, 1).isActive();
    assertThat(isActive, is(false));
}

  @Test
  public void shouldHaveFullHealthAndMana() {
    StandardHotStoneGame standardGame = (StandardHotStoneGame) game;
    StandardHero baby = (StandardHero) standardGame.getHero(Player.FINDUS);
    int healthBaby = baby.getHealth();
    int manaBaby = baby.getMana();
    assertThat(healthBaby, is(MAXIMUM_HEALTH));
    assertThat(manaBaby, is(EXPECTED_MANA));
  }

  @Test
  public void shouldDrawTwoCrystals() {
    StandardHotStoneGame standardGame = (StandardHotStoneGame) game;
    standardGame.afterCrystalDraw(Player.FINDUS);
    assertThat(standardGame.getHero(Player.FINDUS).getMana(), is(1));
  }

  @Test
  public void shouldTakeDamage() {
    StandardHotStoneGame standardGame = (StandardHotStoneGame) game;
    Card card = standardGame.getCardInHand(Player.FINDUS, 0);
    int health = standardGame.getHero(Player.PEDDERSEN).getHealth();
    standardGame.attackHero(Player.FINDUS, card);
    assertThat(standardGame.getHero(Player.PEDDERSEN).getHealth(), is(health - card.getAttack()));
  }

  @Test
  public void shouldPlayerInTurnBeFindusOnStart(){
    assertThat(game.getPlayerInTurn(), is(Player.FINDUS));
  }

  @Test
  public void shouldPlayerInTurnBePeddersenAfterEndturnFindus(){
    game.endTurn();
    assertThat(game.getPlayerInTurn(), is(Player.PEDDERSEN));
  }

 /* @Test
  public void shouldManaResetWhenNewTurn(){
    StandardHero heroFindus = (StandardHero) game.getHero(Player.FINDUS);
    game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 2), 0);
    assertThat(heroFindus.getMana(), not(EXPECTED_MANA));
    game.endTurn();
    StandardHero heroPeddersen = (StandardHero) game.getHero(Player.PEDDERSEN);
    game.playCard(Player.PEDDERSEN, game.getCardInHand(Player.PEDDERSEN, 2), 0);
    assertThat(heroPeddersen.getMana(), not(EXPECTED_MANA));
    game.endTurn();
    assertThat(heroFindus.getMana(), is(EXPECTED_MANA));
    assertThat(heroPeddersen.getMana(), is(EXPECTED_MANA));
  }*/

  @Test
  public void shouldHandSizeIncreaseAfterEndingTurn(){
    int oldHandSizeFindus = game.getHandSize(Player.FINDUS);
    int oldHandSizePeddersen = game.getHandSize(Player.PEDDERSEN);
    game.endTurn();
    int newHandSizeFindus = game.getHandSize(Player.FINDUS);
    int newHandSizePeddersen = game.getHandSize(Player.PEDDERSEN);

    assertThat(newHandSizeFindus - oldHandSizeFindus, is(1));
    assertThat(newHandSizePeddersen - oldHandSizePeddersen, is(1));

  }



  @Test
  public void cardShouldNotBePlayWhenNotEnoughMana(){
    game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 0), 0);
    assertThat(game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 0), 1), is(Status.NOT_ENOUGH_MANA));
    assertThat(game.getCardInField(Player.FINDUS, 1), nullValue());
  }


  //This replaces duplicate code for making combat tests
  private void setupCombatInSlotZero(int findusHandIndex, int peddersenHandIndex) {
    game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, findusHandIndex), 0);
    game.endTurn();
    game.playCard(Player.PEDDERSEN, game.getCardInHand(Player.PEDDERSEN, peddersenHandIndex), 0);
    game.endTurn();
  }

  @Test
  public void defendingCardHealthShouldDecreaseAfterAttack(){
    //Both player plays card Uno (1,1,1)
    setupCombatInSlotZero(2, 2);
    assertThat(game.getCardInField(Player.PEDDERSEN, 0).getHealth(), is(1));
  }

  @Test
  public void findusWinsAfter4Rounds() {
    StandardHotStoneGame standardGame = (StandardHotStoneGame) game;
    standardGame.endTurn();
    standardGame.endTurn();
    standardGame.endTurn();
    standardGame.endTurn();
    standardGame.endTurn();
    assertThat(standardGame.getWinner(), is(Player.FINDUS));
  }

  @Test
  public void attackingCardShouldTakeRetaliationDamageFromDefendingCardOnAttack(){
    //Findus plays Tres (3,3,3), Peddersen plays Uno (1,1,1)
    setupCombatInSlotZero(0, 2);
    assertThat(game.getCardInField(Player.FINDUS, 0).getHealth(), is(1));
  }

  @Test
  public void lethalDamageToCardShouldRemoveFromField(){
    //Findus plays Uno (1,1,1), Peddersen plays Tres (3,3,3)
    setupCombatInSlotZero(2, 0);
    assertThat(game.getCardInField(Player.PEDDERSEN, 0), nullValue());
  }

  @Test
  public void heroShouldTakeDamageWhenItDoesntHaveDefendingCard(){
    StandardHotStoneGame standardGame = (StandardHotStoneGame) game;
    standardGame.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 2), 1);
    standardGame.endTurn();
    standardGame.playCard(Player.PEDDERSEN, game.getCardInHand(Player.PEDDERSEN, 0), 0);
    standardGame.endTurn();
    assertThat(game.getHero(Player.PEDDERSEN).getHealth(), is(not(MAXIMUM_HEALTH)));
  }

}
