package hotstone.framework;
import java.util.ArrayList;

public interface MutableGame extends Game{
     int FIELD_CAPACITY = 10;

    Status drawCard(Player who);
    void executeAttacks();
    void insertCardIntoField(Player who, MutableCard card, int atIndex);
    Status removeCardFromField(Player who, MutableCard cardToRemove);
    Status heroTakeDamage(Player who, int damageAmount);
    ArrayList<MutableCard> getPlayersMinions(Player who);

}
