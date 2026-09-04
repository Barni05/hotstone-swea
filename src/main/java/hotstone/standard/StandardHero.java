package hotstone.standard;

import hotstone.framework.Hero;
import hotstone.framework.Player;

public class StandardHero implements Hero {
    private int health, mana;
    private String type, effectDescription, effectName;
    private boolean powerAbility;
    private Player owner;

    public StandardHero (int health, int mana, String type, boolean powerAbility, Player owner,
                         String effectName, String effectDescription) {
        this.health = health;
        this.mana = mana;
        this.type = type;
        this.powerAbility = powerAbility;
        this.owner = owner;
        this.effectName = effectName;
        this.effectDescription = effectDescription;
    }
    @Override
    public int getHealth() {return health; }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getMana() {return mana; }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public String getType() {return type; }

    @Override
    public boolean canUsePower() {return powerAbility; }

    @Override
    public Player getOwner() {return owner; }

    @Override
    public String getEffectDescription() {return effectDescription; }

    public String getEffectName() {return effectName; }
}
