package hotstone.standard.strategies.Gammastone;

import hotstone.framework.Hero;
import hotstone.framework.Player;
import hotstone.framework.HeroCreationStrategy;
import hotstone.standard.StandardHero;

public class GammaHeroCreationStrategy implements HeroCreationStrategy {
    private final int INITIAL_HEALTH = 21;
    @Override
    public Hero[] createHeroes(int manaFindus, int manaPeddersen){
        Hero[] ret = new Hero[2];
        ret[0] = new StandardHero(INITIAL_HEALTH, manaFindus, "ThaiChef", true, Player.FINDUS, "Chili", "Deal 2 damage to opponent hero");
        ret[1] = new StandardHero(INITIAL_HEALTH, manaPeddersen, "DanishChef", true, Player.PEDDERSEN, "Sovs", "Summons Sovs card");
        return ret;
    }
}
