/*
 * Copyright (C) 2022 - 2026. Henrik BÃ¦rbak Christensen, Aarhus University.
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

package hotstone.observer;

import hotstone.framework.*;
import hotstone.standard.StandardHotStoneGame;
import hotstone.standard.factories.AlphaStoneFactory;
import hotstone.standard.factories.GammaStoneFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestObserver {

  private MutableGame game;
  private ObserverDouble observerDouble;

  @BeforeEach
  public void setUp() {
    HotStoneFactory factory = new AlphaStoneFactory();
    game = new StandardHotStoneGame(factory);
    observerDouble = new ObserverDouble();
    game.addObserver(observerDouble);
  }
  @Test
  public void shouldFireEventsForUsePower() {
    game.usePower(Player.FINDUS);
    assertThat(observerDouble.getLastCall(), is("onUsePower: FINDUS"));
  }

  @Test
  public void shouldFireEventsForPlayCard() {
    Card card = game.getHand(Player.FINDUS).iterator().next();
    game.playCard(Player.FINDUS, card, 0);
    assertThat(observerDouble.getLastCall(), is("onPlayCard: FINDUS, " + card.getName() + ", 0"));
  }

  @Test
  public void shouldFireEventsForEndTurn() {
    game.endTurn();
    assertThat(observerDouble.getLastCall(), is("onChangeTurnTo: PEDDERSEN"));
  }

  @Test
  public void shouldFireEventsForAttackHero() {
    Card card = game.getHand(Player.FINDUS).iterator().next();
    game.playCard(Player.FINDUS, card, 0);
    game.endTurn();
    game.endTurn();
    game.attackHero(Player.FINDUS, card);
    assertThat(observerDouble.getLastCall(), is("onAttackHero: FINDUS, " + card.getName()));
  }

  @Test
  public void shouldFireEventsForDrawCard() {
    game.drawCard(Player.FINDUS);
    Card drawnCard = game.getCardInHand(Player.FINDUS, 0);
    assertThat(observerDouble.getLastCall(), is("onCardDraw: FINDUS, " + drawnCard.getName()));
  }

  @Test
  public void shouldNotifyObserverEventsWhenDanishChefUsesPowerToSummonSovs(){
    HotStoneFactory gammaFactory = new GammaStoneFactory();
    StandardHotStoneGame standardGame = new StandardHotStoneGame(gammaFactory);
    ObserverDouble gammaObserver = new ObserverDouble();
    standardGame.addObserver(gammaObserver);
    standardGame.endTurn();
    gammaObserver.clear();
    standardGame.usePower(Player.PEDDERSEN);
    assertThat(gammaObserver.getEventLog(), contains(
            "onPlayCard: PEDDERSEN, Sovs, 0",
            "onUsePower: PEDDERSEN"
    ));

  }
}
