package hotstone.standard.strategies.SemiStone;

import hotstone.framework.MutableHero;
import hotstone.framework.Player;
import hotstone.framework.strategies.Effect;
import hotstone.framework.strategies.HeroCreationStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroEffects.DanishChefEffect;
import hotstone.standard.HeroEffects.FrenchChefEffect;
import hotstone.standard.HeroEffects.ItalianChefEffect;
import hotstone.standard.HeroEffects.ThaiChefEffect;
import hotstone.standard.StandardHero;
import hotstone.standard.utils.IndexSelector;

import java.util.ArrayList;

public class ThaiDanishFrenchItalianRandomHeroCreation implements HeroCreationStrategy {
    private final IndexSelector indexSelector;

    public ThaiDanishFrenchItalianRandomHeroCreation(IndexSelector indexSelectorFindus){
        this.indexSelector = indexSelectorFindus;
    }


    @Override
    public MutableHero[] createHeroes(int manaFindus, int manaPeddersen) {
        MutableHero[] ret = new MutableHero[2];

        //Create hero pools for each player
        ArrayList<MutableHero> findusPool = createHeroPool(Player.FINDUS, manaFindus, indexSelector);
        ArrayList<MutableHero> peddersenPool = createHeroPool(Player.PEDDERSEN, manaPeddersen, indexSelector);

        //Select hero for findus
        int findusChoice = indexSelector.selectIndex(findusPool.size());
        ret[0] = findusPool.get(findusChoice);

        //Select hero for pedersen
        int peddersenChoice = indexSelector.selectIndex(peddersenPool.size());
        ret[1] = peddersenPool.get(peddersenChoice);

        return ret;
    }

    private ArrayList<MutableHero> createHeroPool(Player player, int startingMana, IndexSelector selector) {
        ArrayList<MutableHero> pool = new ArrayList<>();

        Effect effect = new ThaiChefEffect(player);
        // Index 0 - Thai Chef
        MutableHero thaiHero = new StandardHero(INITIAL_HEALTH, startingMana, GameConstants.THAI_CHEF_HERO_TYPE, true, player, "Chili", "Deal 2 damage to opponent hero", effect);
        pool.add(thaiHero);

        // Index 1 - Danish Chef
        effect = new DanishChefEffect(Player.PEDDERSEN);
        MutableHero danishHero = new StandardHero(INITIAL_HEALTH, startingMana, GameConstants.DANISH_CHEF_HERO_TYPE, true, player, "Sovs", "Summons Sovs card", effect);
        pool.add(danishHero);

        // Index 2
        effect = new FrenchChefEffect(Player.FINDUS, selector);
        MutableHero frenchHero = new StandardHero(HeroCreationStrategy.INITIAL_HEALTH, startingMana, GameConstants.FRENCH_CHEF_HERO_TYPE, true, player, "Redwine", "Deal 2 damage to opponent minion", effect);
        pool.add(frenchHero);

        // Index 3 - Italian Chef
        effect = new ItalianChefEffect(Player.PEDDERSEN, selector);
        MutableHero italianHero = new StandardHero(HeroCreationStrategy.INITIAL_HEALTH, startingMana, GameConstants.ITALIAN_CHEF_HERO_TYPE, true, player, "Pasta", "Add 2 attack to minion", effect);
        pool.add(italianHero);

        return pool;
    }
}
