package hotstone.standard.strategies.SemiStone;

import hotstone.framework.MutableHero;
import hotstone.framework.Player;
import hotstone.framework.strategies.HeroCreationStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.HeroEffects.DanishChefEffect;
import hotstone.standard.HeroEffects.FrenchChefEffect;
import hotstone.standard.HeroEffects.ItalianChefEffect;
import hotstone.standard.HeroEffects.ThaiChefEffect;
import hotstone.standard.StandardHero;
import hotstone.standard.utils.IndexSelector;
import hotstone.standard.utils.RandomIndexSelector;

import java.util.ArrayList;

public class RandomFourHeroCreationStrategy implements HeroCreationStrategy {
    private final IndexSelector indexSelector;

    public RandomFourHeroCreationStrategy() {
        this(new RandomIndexSelector());
    }

    public RandomFourHeroCreationStrategy(IndexSelector indexSelector) {
        this.indexSelector = indexSelector;
    }
    public MutableHero[] createHeroes(int manaFindus, int manaPeddersen) {
        MutableHero[] heroes = new MutableHero[2];

        ArrayList<MutableHero> findusPool = createHeroPool(Player.FINDUS, manaFindus);
        int findusIndex = indexSelector.selectIndex(findusPool.size());
        heroes[0] = findusPool.get(findusIndex);

        ArrayList<MutableHero> peddersenPool = createHeroPool(Player.PEDDERSEN, manaPeddersen);
        int peddersenIndex = indexSelector.selectIndex(peddersenPool.size());
        heroes[1] = peddersenPool.get(peddersenIndex);

        return heroes;
    }
    private ArrayList<MutableHero> createHeroPool(Player player, int startingMana) {
        ArrayList<MutableHero> pool = new ArrayList<>();
        pool.add(createThaiChef(player, startingMana));
        pool.add(createDanishChef(player, startingMana));
        pool.add(createFrenchChef(player, startingMana));
        pool.add(createItalianChef(player, startingMana));
        return pool;
    }
    private MutableHero createThaiChef(Player player, int mana) {
        MutableHero hero = new StandardHero(INITIAL_HEALTH, mana, GameConstants.THAI_CHEF_HERO_TYPE, true, player, "Chili", "Deal 2 damage to opponent hero", new ThaiChefEffect(player));
        hero.setMana(mana);
        return hero;
    }

    private MutableHero createDanishChef(Player player, int mana) {
        MutableHero hero =  new StandardHero(INITIAL_HEALTH, mana, GameConstants.DANISH_CHEF_HERO_TYPE, true, player, "Sovs", "Summons Sovs card", new DanishChefEffect(player));
        hero.setMana(mana);
        return hero;
    }

    private MutableHero createFrenchChef(Player player, int mana) {
        MutableHero hero = new StandardHero(HeroCreationStrategy.INITIAL_HEALTH, mana, GameConstants.FRENCH_CHEF_HERO_TYPE, true, player, "Redwine", "Deal 2 damage to opponent minion", new FrenchChefEffect(player, indexSelector));
        hero.setMana(mana);
        return hero;
    }

    private MutableHero createItalianChef(Player player, int mana) {
        MutableHero hero =  new StandardHero(HeroCreationStrategy.INITIAL_HEALTH, mana, GameConstants.ITALIAN_CHEF_HERO_TYPE, true, player, "Pasta", "Add 2 attack to minion", new ItalianChefEffect(player, indexSelector));
        hero.setMana(mana);
        return hero;
    }
}
