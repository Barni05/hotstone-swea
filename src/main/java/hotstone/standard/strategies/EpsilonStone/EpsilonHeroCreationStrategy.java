package hotstone.standard.strategies.EpsilonStone;

import hotstone.framework.Player;
import hotstone.framework.strategies.HeroCreationStrategy;
import hotstone.standard.StandardHero;
import hotstone.framework.Hero;

public class EpsilonHeroCreationStrategy implements HeroCreationStrategy {
    @Override
    public Hero[] createHeroes(int manaFindus, int manaPeddersen){
        Hero[] ret = new Hero[2];
        ret[0] = new StandardHero(HeroCreationStrategy.INITIAL_HEALTH, manaFindus, "FrenchChef", true, Player.FINDUS, "Redwine", "Deal 2 damage to opponent minion");
        ret[0] = new StandardHero(HeroCreationStrategy.INITIAL_HEALTH, manaPeddersen, "ItalianChef", true, Player.PEDDERSEN, "Pasta", "Add 2 attack to minion");
        return ret;
    }
}
