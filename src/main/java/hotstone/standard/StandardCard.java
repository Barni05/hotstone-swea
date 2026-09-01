package hotstone.standard;
import hotstone.framework.Player;
import hotstone.framework.Card;
public class StandardCard implements Card{
    private String name, effectDescription;
    private int manaCost, attack, health;
    private boolean isActive;
    private Player owner;

    public StandardCard(String name, String effectDescription, int manaCost, int attack, int health, boolean isActive, Player owner) {
        this.name = name;
        this.effectDescription = effectDescription;
        this.manaCost = manaCost;
        this.attack = attack;
        this.health = health;
        this.isActive = isActive;
        this.owner = owner;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEffectDescription() {
        return effectDescription;
    }

    @Override
    public int getManaCost() {
        return manaCost;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public Player getOwner() {
        return owner;
    }
}
