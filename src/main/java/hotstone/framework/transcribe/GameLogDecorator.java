package hotstone.framework.transcribe;

import hotstone.framework.*;

import java.util.ArrayList;

public class GameLogDecorator implements MutableGame {
    private MutableGame mutableGame;

    public GameLogDecorator(MutableGame mg){
        this.mutableGame = mg;
    }

    @Override
    public Player getPlayerInTurn() {
        return mutableGame.getPlayerInTurn();
    }

    @Override
    public Hero getHero(Player who) {
        return mutableGame.getHero(who);
    }

    @Override
    public Player getWinner() {
        return mutableGame.getWinner();
    }

    @Override
    public int calculateStartRoundMana(Player who) {
        return mutableGame.calculateStartRoundMana(who);
    }

    @Override
    public int getTurnNumber() {
        return mutableGame.getTurnNumber();
    }

    @Override
    public int getDeckSize(Player who) {
        return mutableGame.getDeckSize(who);
    }

    @Override
    public Card getCardInHand(Player who, int indexInHand) {
        return mutableGame.getCardInHand(who, indexInHand);
    }

    @Override
    public Iterable<? extends Card> getHand(Player who) {
        return mutableGame.getHand(who);
    }

    @Override
    public int getHandSize(Player who) {
        return mutableGame.getHandSize(who);
    }

    @Override
    public Card getCardInField(Player who, int indexInField) {
        return mutableGame.getCardInField(who, indexInField);
    }

    @Override
    public Iterable<? extends Card> getField(Player who) {
        return mutableGame.getField(who);
    }

    @Override
    public int getFieldSize(Player who) {
        return mutableGame.getFieldSize(who);
    }

    @Override
    public void endTurn() {
        System.out.println(mutableGame.getPlayerInTurn().toString()+" ended turn");
        mutableGame.endTurn();
    }

    @Override
    public Status playCard(Player who, Card card, int atIndex) {
        System.out.println(who.toString()+" played "+card.getName()+" at "+atIndex);
        return mutableGame.playCard(who, card, atIndex);
    }

    @Override
    public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
        System.out.println(playerAttacking.toString() + "attacked " + defendingCard.getName() + " with " + attackingCard.getName());
        return mutableGame.attackCard(playerAttacking, attackingCard, defendingCard);
    }

    @Override
    public Status attackHero(Player playerAttacking, Card attackingCard) {
        System.out.println(playerAttacking.toString() + "attacked HERO with " + attackingCard.getName());
        return mutableGame.attackHero(playerAttacking, attackingCard);
    }

    @Override
    public Status usePower(Player who) {
        System.out.println(who.toString() + "used hero power");
        return mutableGame.usePower(who);
    }

    @Override
    public Status drawCard(Player who) {
        System.out.println(who.toString() + " drew a card");
        return mutableGame.drawCard(who);
    }

    @Override
    public void executeAttacks() {
        mutableGame.executeAttacks();
    }

    @Override
    public void insertCardIntoField(Player who, MutableCard card, int atIndex) {
        mutableGame.insertCardIntoField(who, card, atIndex);
    }

    @Override
    public Status removeCardFromField(Player who, Card cardToRemove) {
        return mutableGame.removeCardFromField(who, cardToRemove);
    }

    @Override
    public Status removeCardFromField(Player who, int atIndex) {
        return mutableGame.removeCardFromField(who, atIndex);
    }

    @Override
    public Status deltaHealthOnHero(Player who, int damageAmount) {
        return mutableGame.deltaHealthOnHero(who, damageAmount);
    }

    @Override
    public ArrayList<MutableCard> getPlayersMinions(Player who) {
        return mutableGame.getPlayersMinions(who);
    }

    @Override
    public MutableCard getMutableCardInField(Player who, int indexInField) {
        return mutableGame.getMutableCardInField(who, indexInField);
    }

    @Override
    public MutableHero getMutableHero(Player who) {
        return mutableGame.getMutableHero(who);
    }

    @Override
    public Status deltaAttackOnCard(Player whose, int fieldIndex, int attack) {
        return mutableGame.deltaAttackOnCard(whose, fieldIndex, attack);
    }

    @Override
    public Status deltaHealthOnCard(Player whose, int fieldIndex, int health) {
        return mutableGame.deltaHealthOnCard(whose, fieldIndex, health);
    }
}
