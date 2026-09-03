package hotstone.standard;

import hotstone.framework.Hero;
import hotstone.framework.Player;

public class StandardHero implements Hero {
    private int health, mana;
    private String type, effectdescription;
    private boolean powerAbility;
    private Player owner;

    public StandardHero (int health, int mana, String type, boolean powerAbility, Player owner) {
        this.health = health;
        this.mana = mana;
        this.type = type;
        this.powerAbility = powerAbility;
        this.owner = owner;
    }
    @Override
    public int getHealth() {return health; }

    @Override
    public int getMana() {return mana; }

    @Override
    public String getType() {return type; }

    @Override
    public boolean canUsePower() {return powerAbility; }

    @Override
    public Player getOwner() {return owner; }

    @Override
    public String getEffectDescription() {return effectdescription; }
}
