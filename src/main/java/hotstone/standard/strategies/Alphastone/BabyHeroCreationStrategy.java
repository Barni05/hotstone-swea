package hotstone.standard.strategies.Alphastone;
import hotstone.framework.MutableHero;
import hotstone.framework.strategies.Effect;
import hotstone.framework.strategies.HeroCreationStrategy;
import hotstone.framework.Player;
import hotstone.standard.HeroEffects.BabyHeroEffect;
import hotstone.standard.StandardHero;

public class BabyHeroCreationStrategy implements HeroCreationStrategy {
    private final int INITIAL_HEALTH = 21;

    @Override
    public MutableHero[] createHeroes(int manaFindus, int manaPeddersen) {
        MutableHero[] heroes = new MutableHero[2];
        Effect effect = new BabyHeroEffect(Player.FINDUS);
        heroes[0] = new StandardHero(INITIAL_HEALTH, manaFindus, "Baby", false,
                Player.FINDUS, "Cute","Just cute", effect);

        effect = new BabyHeroEffect(Player.PEDDERSEN);
        heroes[1] =  new StandardHero(INITIAL_HEALTH, manaPeddersen, "Baby", false,
                Player.PEDDERSEN, "Cute","Just cute", effect);
        return heroes;
    }
}
