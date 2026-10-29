package hotstone.framework.strategies;

import hotstone.framework.Hero;
import hotstone.framework.MutableHero;

public interface HeroCreationStrategy {
    int INITIAL_HEALTH = 21;


    /**
     * @return An array of 2 heroes, 1st is FINDUS's, 2nd is PEDDERSEN's
     * */
    MutableHero[] createHeroes(int manaFindus, int manaPeddersen);
}
