package hotstone.framework;

import hotstone.framework.strategies.Effect;

public interface MutableCard extends Card{
    Status deltaHealth(int damage);
    void setActive(boolean newActive);
    void deltaAttack(int amount);
    Effect getEffect();
}
