package hotstone.framework;

public interface MutableHero extends Hero{
    Status deltaHealth(int damage);
    Status deltaMana(int mana);
    void setMana(int mana);
    void setCanUsePower(boolean canUsePower);
}
