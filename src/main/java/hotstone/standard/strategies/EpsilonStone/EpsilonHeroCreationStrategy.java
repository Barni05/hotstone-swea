package hotstone.standard.strategies.EpsilonStone;

import hotstone.framework.MutableHero;
import hotstone.framework.Player;
import hotstone.framework.strategies.HeroCreationStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHero;
import hotstone.framework.Hero;

public class EpsilonHeroCreationStrategy implements HeroCreationStrategy {
    @Override
    public MutableHero[] createHeroes(int manaFindus, int manaPeddersen){
        MutableHero[] ret = new MutableHero[2];
        ret[0] = new StandardHero(HeroCreationStrategy.INITIAL_HEALTH, manaFindus, GameConstants.FRENCH_CHEF_HERO_TYPE, true, Player.FINDUS, "Redwine", "Deal 2 damage to opponent minion");
        ret[1] = new StandardHero(HeroCreationStrategy.INITIAL_HEALTH, manaPeddersen, GameConstants.ITALIAN_CHEF_HERO_TYPE, true, Player.PEDDERSEN, "Pasta", "Add 2 attack to minion");
        return ret;
    }
}
