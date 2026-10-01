package hotstone.framework.strategies;

import hotstone.framework.Hero;

public interface HeroCreationStrategy {
    int INITIAL_HEALTH = 21;


    /**
     * @return An array of 2 heroes, 1st is FINDUS's, 2nd is PEDDERSEN's
     * */
    Hero[] createHeroes(int manaFindus, int manaPeddersen);
}
