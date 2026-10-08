package hotstone.standard.strategies.EpsilonStone;

import hotstone.framework.MutableHero;
import hotstone.framework.Player;
import hotstone.framework.strategies.Effect;
import hotstone.framework.strategies.HeroCreationStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroEffects.FrenchChefEffect;
import hotstone.standard.HeroEffects.ItalianChefEffect;
import hotstone.standard.StandardHero;
import hotstone.standard.utils.IndexSelector;

public class FrenchItalianChefHeroCreationStrategy implements HeroCreationStrategy {
    private final IndexSelector indexSelector;

    public FrenchItalianChefHeroCreationStrategy(IndexSelector indexSelector){
        this.indexSelector = indexSelector;
    }

    @Override
    public MutableHero[] createHeroes(int manaFindus, int manaPeddersen){
        MutableHero[] ret = new MutableHero[2];
        Effect effect = new FrenchChefEffect(Player.FINDUS, indexSelector);
        ret[0] = new StandardHero(HeroCreationStrategy.INITIAL_HEALTH, manaFindus, GameConstants.FRENCH_CHEF_HERO_TYPE, true, Player.FINDUS, "Redwine", "Deal 2 damage to opponent minion", effect);
        effect = new ItalianChefEffect(Player.PEDDERSEN, indexSelector);
        ret[1] = new StandardHero(HeroCreationStrategy.INITIAL_HEALTH, manaPeddersen, GameConstants.ITALIAN_CHEF_HERO_TYPE, true, Player.PEDDERSEN, "Pasta", "Add 2 attack to minion", effect);
        return ret;
    }
}
