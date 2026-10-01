package hotstone.standard.strategies.Alphastone;
import hotstone.framework.Hero;
import hotstone.framework.strategies.HeroCreationStrategy;
import hotstone.framework.Player;
import hotstone.standard.StandardHero;

public class AlphaHeroCreationStrategy implements HeroCreationStrategy {
    private final int INITIAL_HEALTH = 21;

    @Override
    public Hero[] createHeroes(int manaFindus, int manaPeddersen) {
        Hero[] heroes = new Hero[2];
        heroes[0] = new StandardHero(INITIAL_HEALTH, manaFindus, "Baby", false,
                Player.FINDUS, "Cute","Just cute");
        heroes[1] =  new StandardHero(INITIAL_HEALTH, manaPeddersen, "Baby", false,
                Player.PEDDERSEN, "Cute","Just cute");
        return heroes;
    }
}
