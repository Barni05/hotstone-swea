package hotstone.framework;

public interface HeroCreationStrategy {
    /**
     * @return An array of 2 heroes, 1st is FINDUS's, 2nd is PEDDERSEN's
     * */
    public Hero[] createHeroes(int manaFindus, int manaPeddersen);
}
