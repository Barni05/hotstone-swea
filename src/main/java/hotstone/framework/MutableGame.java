package hotstone.framework;
import java.util.ArrayList;

public interface MutableGame extends Game{
     int FIELD_CAPACITY = 10;

    Status drawCard(Player who);
    default void executeAttacks(){}
    void insertCardIntoField(Player who, MutableCard card, int atIndex);
    Status removeCardFromField(Player who, Card cardToRemove);
    Status heroTakeDamage(Player who, int damageAmount);
    default ArrayList<MutableCard> getPlayersMinions(Player who){return null;}
    default MutableCard getMutableCardInField(Player who, int indexInField){return null;}
    default MutableHero getMutableHero(Player who) {return null;}

}
