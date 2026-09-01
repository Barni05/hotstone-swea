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
    private Stack<Card> FindusDeck = new Stack<>();
    private Stack<Card> PeddersenDeck = new Stack<>();
    public StandardHotStoneGame() {
        //Initializing both player's deck with the 7 cards
        StandardCard unoFindus = new StandardCard("Uno", "It's just One", 1, 1, 1, false, Player.FINDUS);
        StandardCard dosFindus = new StandardCard("Dos", "It's just Two", 2, 2, 2, false, Player.FINDUS);
        StandardCard tresFindus = new StandardCard("Tres", "It's just Three", 3, 3, 3, false, Player.FINDUS);
        StandardCard cuatroFindus = new StandardCard("Cuatro", "It's just Four", 2, 3, 1, false, Player.FINDUS);
        StandardCard cincoFindus = new StandardCard("Cinco", "It's just Five", 3, 5, 1, false, Player.FINDUS);
        StandardCard seisFindus = new StandardCard("Seis", "It's just Six", 2, 1, 3, false, Player.FINDUS);
        StandardCard sieteFindus = new StandardCard("Siete", "It's just Seven", 3, 2, 4, false, Player.FINDUS);

        FindusDeck.push(unoFindus);
        FindusDeck.push(dosFindus);
        FindusDeck.push(tresFindus);
        FindusDeck.push(cuatroFindus);
        FindusDeck.push(cincoFindus);
        FindusDeck.push(seisFindus);
        FindusDeck.push(sieteFindus);

        StandardCard unoPeddersen = new StandardCard("Uno", "It's just One", 1, 1, 1, false, Player.PEDDERSEN);
        StandardCard dosPeddersen = new StandardCard("Dos", "It's just Two", 2, 2, 2, false, Player.PEDDERSEN);
        StandardCard tresPeddersen = new StandardCard("Tres", "It's just Three", 3, 3, 3, false, Player.PEDDERSEN);
        StandardCard cuatroPeddersen = new StandardCard("Cuatro", "It's just Four", 2, 3, 1, false, Player.PEDDERSEN);
        StandardCard cincoPeddersen = new StandardCard("Cinco", "It's just Five", 3, 5, 1, false, Player.PEDDERSEN);
        StandardCard seisPeddersen = new StandardCard("Seis", "It's just Six", 2, 1, 3, false, Player.PEDDERSEN);
        StandardCard sietePeddersen = new StandardCard("Siete", "It's just Seven", 3, 2, 4, false, Player.PEDDERSEN);

        PeddersenDeck.push(unoPeddersen);
        PeddersenDeck.push(dosPeddersen);
        PeddersenDeck.push(tresPeddersen);
        PeddersenDeck.push(cuatroPeddersen);
        PeddersenDeck.push(cincoPeddersen);
        PeddersenDeck.push(seisPeddersen);
        PeddersenDeck.push(sietePeddersen);

    }

  @Override
  public Player getPlayerInTurn() {
    return Player.FINDUS;
  } // Fake-it

  @Override
  public Hero getHero(Player who) {
    return null;
  }

  @Override
  public Player getWinner() {
    return null;
  }

  @Override
  public int getTurnNumber() {
    return 0;
  }

  @Override
  public int getDeckSize(Player who) {
    return who == Player.FINDUS ? FindusDeck.size() : PeddersenDeck.size();
  }

  @Override
  public Card getCardInHand(Player who, int indexInHand) {
    return null;
  }

  @Override
  public Iterable<? extends Card> getHand(Player who) {
    return null;
  }

  @Override
  public int getHandSize(Player who) {
    return 3;
  } // FAKE-IT

  @Override
  public Card getCardInField(Player who, int indexInField) {
    return null;
  }

  @Override
  public Iterable<? extends Card> getField(Player who) {
    return null;
  }

  @Override
  public int getFieldSize(Player who) {
    return 0;
  }

  @Override
  public void endTurn() { }

  @Override
  public Status playCard(Player who, Card card, int atIndex) {
    return null;
  }

  @Override
  public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
    return null;
  }

  @Override
  public Status attackHero(Player playerAttacking, Card attackingCard) {
    return null;
  }

  @Override
  public Status usePower(Player who) {
    return null;
  }
}
