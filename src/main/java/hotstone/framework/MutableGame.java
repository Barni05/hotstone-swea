package hotstone.framework;

import java.util.ArrayList;

public interface MutableGame extends Game {
    int FIELD_CAPACITY = 10;

    Status drawCard(Player who);

    void executeAttacks();

    void insertCardIntoField(Player who, MutableCard card, int atIndex);

    Status removeCardFromField(Player who, Card cardToRemove);
    Status removeCardFromField(Player who, int atIndex);

    Status deltaHealthOnHero(Player who, int damageAmount);

    ArrayList<MutableCard> getPlayersMinions(Player who);

    MutableCard getMutableCardInField(Player who, int indexInField);

    MutableHero getMutableHero(Player who);

    Status deltaAttackOnCard(Player whose, int fieldIndex, int attack);
    Status deltaHealthOnCard(Player whose, int fieldIndex, int health);



}
