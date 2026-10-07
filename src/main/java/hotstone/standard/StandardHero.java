package hotstone.standard;

import hotstone.framework.Hero;
import hotstone.framework.MutableHero;
import hotstone.framework.Player;
import hotstone.framework.Status;

public class StandardHero implements Hero, MutableHero {
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
    @Override
    public Status deltaHealth(int damage) {
        int newHealth = getHealth()-damage;
        if(newHealth <= 0)
            return Status.NO_MORE_HEALTH;
        this.health = newHealth;
        return Status.OK;
    }

    @Override
    public int getMana() {return mana; }

    @Override
    public void setMana(int mana){this.mana = mana;}

    @Override
    public Status deltaMana(int mana) {
        int newMana = getMana()-mana;
        if(newMana <= 0)
            return Status.NO_MORE_HEALTH;
        this.mana = newMana   ;
        return Status.OK;
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
