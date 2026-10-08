package hotstone.utility;

import hotstone.framework.*;

public class MutableGameSpy implements MutableGame {
    private String lastCall = "";
    public MutableGameSpy(){}

    public String getLastCall(){
        return lastCall;
    }

    @Override
    public Status drawCard(Player who){
        lastCall = "drawCard("+who.toString()+")";
        return Status.OK;
    }

    @Override
    public void insertCardIntoField(Player who, MutableCard card, int atIndex){
        lastCall = "insertCardIntoField("+who.toString()+","+card.getName()+","+atIndex+")";
    }

    @Override
    public Status removeCardFromField(Player who, Card cardToRemove){
        lastCall = "removeCardFromField("+who.toString()+","+cardToRemove.getName()+")";
        return Status.OK;
    }

    @Override
    public Status heroTakeDamage(Player who, int damageAmount){
        lastCall = "heroTakeDamage("+who.toString()+","+damageAmount+")";
        return Status.OK;
    }

    @Override
    public Player getPlayerInTurn(){return null;}

    @Override
    public Hero getHero(Player who) {
        return null;
    }

    @Override
    public Player getWinner() {
        return null;
    }

    @Override
    public int calculateStartRoundMana(Player who) {
        return 0;
    }

    @Override
    public int getTurnNumber() {
        return 0;
    }

    @Override
    public int getDeckSize(Player who) {
        return 0;
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
        return 0;
    }

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
    public void endTurn() {

    }

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
