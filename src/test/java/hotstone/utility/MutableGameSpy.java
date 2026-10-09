package hotstone.utility;

import hotstone.framework.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

public class MutableGameSpy implements MutableGame {
    private String lastCall = "";
    private HashMap<Player, Stack<MutableCard>> decks = new HashMap<>();
    private HashMap<Player, ArrayList<MutableCard>> hands = new HashMap<>();
    private HashMap<Player, ArrayList<MutableCard>> fields = new HashMap<>();
    public MutableGameSpy(){
        for (Player player : Player.values()) {
            fields.put(player, new ArrayList<>(Collections.nCopies(FIELD_CAPACITY, null)));
        }
    }

    public String getLastCall(){
        return lastCall;
    }

    @Override
    public Status drawCard(Player who){
        lastCall = "drawCard("+who.toString()+")";
        return Status.OK;
    }

    @Override
    public void executeAttacks() {

    }

    @Override
    public void insertCardIntoField(Player who, MutableCard card, int atIndex){
        lastCall = "insertCardIntoField("+who.toString()+","+card.getName()+","+atIndex+")";
        fields.get(who).set(atIndex, card);
    }

    @Override
    public Status removeCardFromField(Player who, Card cardToRemove){
        lastCall = "removeCardFromField("+who.toString()+","+cardToRemove.getName()+")";

        return Status.OK;
    }

    @Override
    public Status removeCardFromField(Player who, int atIndex) {
        lastCall = "removeCardFromField("+who.toString()+","+atIndex+")";
        return Status.OK;
    }

    @Override
    public Status heroTakeDamage(Player who, int damageAmount){
        lastCall = "heroTakeDamage("+who.toString()+","+damageAmount+")";
        return Status.OK;
    }

    @Override
    public ArrayList<MutableCard> getPlayersMinions(Player who) {
        return fields.get(who);
    }

    @Override
    public MutableCard getMutableCardInField(Player who, int indexInField) {
        return null;
    }

    @Override
    public MutableHero getMutableHero(Player who) {
        return null;
    }

    @Override
    public void deltaAttackOnCard(Player whose, int fieldIndex, int attack) {
        lastCall = "deltaAttackOnCard("+whose.toString()+","+fieldIndex+","+attack+")";

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
        return 1;
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
