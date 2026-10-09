package hotstone.standard.strategies.Gammastone;

import hotstone.framework.MutableHero;
import hotstone.framework.Player;
import hotstone.framework.strategies.Effect;
import hotstone.framework.strategies.HeroCreationStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroEffects.DanishChefEffect;
import hotstone.standard.HeroEffects.ThaiChefEffect;
import hotstone.standard.StandardHero;

public class ThaiDanishChefHeroCreationStrategy implements HeroCreationStrategy {
    @Override
    public MutableHero[] createHeroes(int manaFindus, int manaPeddersen){
        MutableHero[] ret = new MutableHero[2];
        Effect effect = new ThaiChefEffect(Player.FINDUS);
        ret[0] = new StandardHero(INITIAL_HEALTH, manaFindus, GameConstants.THAI_CHEF_HERO_TYPE, true, Player.FINDUS, "Chili", "Deal 2 damage to opponent hero", effect);

        effect = new DanishChefEffect(Player.PEDDERSEN);
        ret[1] = new StandardHero(INITIAL_HEALTH, manaPeddersen, GameConstants.DANISH_CHEF_HERO_TYPE, true, Player.PEDDERSEN, "Sovs", "Summons Sovs card", effect);
        return ret;
    }
}
