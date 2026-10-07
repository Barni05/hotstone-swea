package hotstone.framework;

public interface MutableGame extends Game{
    Status drawCard(Player who);
    void executeAttacks();
    void insertCardIntoField(Player who, Card card, int atIndex);
    Status removeCardFromField(Player who, Card cardToRemove);
    Status heroTakeDamage(Player who, int damageAmount);

}
