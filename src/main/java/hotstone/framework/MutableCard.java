package hotstone.framework;

public interface MutableCard extends Card{
    Status deltaHealth(int damage);
    void setActive(boolean newActive);
    void deltaAttack(int amount);
    void useEffect(MutableGame game, Player who);
}
