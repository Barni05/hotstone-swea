package hotstone.framework;

import hotstone.framework.strategies.Effect;

public interface MutableCard extends Card{
    Status deltaHealth(int damage);
    Status deltaAttack(int amount);
    void setActive(boolean newActive);
    Effect getEffect();
}
