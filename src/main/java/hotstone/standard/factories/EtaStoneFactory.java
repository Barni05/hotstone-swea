package hotstone.standard.factories;

import hotstone.framework.HotStoneFactory;
import hotstone.framework.strategies.DeckBuildingStrategy;
import hotstone.framework.strategies.HeroCreationStrategy;
import hotstone.framework.strategies.ManaProductionStrategy;
import hotstone.framework.strategies.WinningStrategy;
import hotstone.standard.strategies.Alphastone.AlphaWinningStrategy;
import hotstone.standard.strategies.Alphastone.BabyHeroCreationStrategy;
import hotstone.standard.strategies.DeltaStone.DeltaManaProductionStrategy;
import hotstone.standard.strategies.EtaStone.ModifiedDishDeckBuildingStrategy;
import hotstone.standard.utils.IndexSelector;
import hotstone.standard.utils.RandomIndexSelector;

public class EtaStoneFactory implements HotStoneFactory {
    private final IndexSelector indexSelector;

    public EtaStoneFactory(){indexSelector = new RandomIndexSelector();
    }

    public EtaStoneFactory(IndexSelector indexSelector){this.indexSelector = indexSelector;}

    @Override
    public DeckBuildingStrategy createDeckBuildingStrategy() {
        return new ModifiedDishDeckBuildingStrategy(indexSelector);
    }

    @Override
    public HeroCreationStrategy createHeroCreationStrategy() {
        return new BabyHeroCreationStrategy();
    }

    @Override
    public ManaProductionStrategy createManaProductionStrategy() {
        return new DeltaManaProductionStrategy();
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return new AlphaWinningStrategy();
    }
}
