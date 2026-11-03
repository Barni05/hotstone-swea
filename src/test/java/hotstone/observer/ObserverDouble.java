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

import hotstone.framework.Card;
import hotstone.framework.HotStoneFactory;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.standard.StandardHotStoneGame;

import java.util.ArrayList;

/**
 *
 * This test-double is a spy because it passively
 * records calls (observer callbacks and parameters) triggered
 * by the game implementation so that unit tests can verify that
 * observer events are correctly called with the right parameters.
 */

class ObserverDouble implements GameObserver {
    private final ArrayList<String> eventLog = new ArrayList<>();

    public ArrayList<String> getEventLog() {
        return eventLog;
    }

    public String getLastCall() {
        if (eventLog.isEmpty()) {
            return "none";
        }
        return eventLog.getLast();
    }

    public void clear() {
        eventLog.clear();
    }

    @Override
    public void onPlayCard(Player who, Card card, int atIndex) {
        eventLog.add("onPlayCard: " + who + ", " + (card != null ? card.getName() : "null") + ", " + atIndex);
    }

    @Override
    public void onChangeTurnTo(Player playerBecomingActive) {
      eventLog.add("onChangeTurnTo: " + playerBecomingActive);    }

    @Override
    public void onAttackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
      eventLog.add("onAttackCard: " + playerAttacking + ", " +
              (attackingCard != null ? attackingCard.getName() : "null") + ", " +
              (defendingCard != null ? defendingCard.getName() : "null"));
    }

    @Override
    public void onAttackHero(Player playerAttacking, Card attackingCard) {
      eventLog.add("onAttackHero: " + playerAttacking + ", " +
              (attackingCard != null ? attackingCard.getName() : "null"));
    }

    @Override
    public void onUsePower(Player who) {
      eventLog.add("onUsePower: " + who);    }

    @Override
    public void onCardDraw(Player who, Card drawnCard) {
      eventLog.add("onCardDraw: " + who + ", " + (drawnCard != null ? drawnCard.getName() : "null"));    }

    @Override
    public void onCardUpdate(Card card) {
      eventLog.add("onCardUpdate: " + (card != null ? card.getName() : "null"));    }

    @Override
    public void onCardRemove(Player who, Card card) {
      eventLog.add("onCardRemove: " + who + ", " + (card != null ? card.getName() : "null"));    }

    @Override
    public void onHeroUpdate(Player who) {
      eventLog.add("onHeroUpdate: " + who);    }

    @Override
    public void onGameWon(Player playerWinning) {
      eventLog.add("onGameWon: " + playerWinning);    }
}