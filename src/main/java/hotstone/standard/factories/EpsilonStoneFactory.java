package hotstone.standard.factories;

import hotstone.framework.HotStoneFactory;
import hotstone.framework.strategies.*;
import hotstone.standard.strategies.Alphastone.*;
import hotstone.standard.strategies.EpsilonStone.EpsilonHeroCreationStrategy;
import hotstone.standard.strategies.EpsilonStone.EpsilonHeroPowerStrategy;
import hotstone.standard.utils.IndexSelector;
import hotstone.standard.utils.RandomIndexSelector;

public class EpsilonStoneFactory implements HotStoneFactory {
    private final IndexSelector indexSelector;

    public EpsilonStoneFactory() {
        indexSelector = new RandomIndexSelector();
    }

    public EpsilonStoneFactory(IndexSelector indexSelector){
        this.indexSelector = indexSelector;
    }

    @Override
    public DeckBuildingStrategy createDeckBuildingStrategy() {
        return new AlphaDeckBuildingStrategy();
    }

    @Override
    public HeroCreationStrategy createHeroCreationStrategy() {
        return new EpsilonHeroCreationStrategy();
    }

    @Override
    public HeroPowerStrategy createHeroPowerStrategy() {
        return new EpsilonHeroPowerStrategy(indexSelector);
    }


    @Override
    public ManaProductionStrategy createManaProductionStrategy() {
        return new AlphaManaProductionStrategy();
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return new AlphaWinningStrategy();
    }
}
