package hotstone.standard.factories;

import hotstone.framework.HotStoneFactory;
import hotstone.framework.strategies.*;
import hotstone.standard.strategies.Alphastone.*;
import hotstone.standard.strategies.DeltaStone.DishDeckBuildingStrategy;
import hotstone.standard.strategies.DeltaStone.DeltaManaProductionStrategy;

public class DeltaStoneFactory implements HotStoneFactory {
    @Override
    public DeckBuildingStrategy createDeckBuildingStrategy(){
        return new DishDeckBuildingStrategy();
    }

    @Override
    public HeroCreationStrategy createHeroCreationStrategy(){
        return new BabyHeroCreationStrategy();
    }

    @Override
    public ManaProductionStrategy createManaProductionStrategy(){
        return new DeltaManaProductionStrategy();
    }

    @Override
    public WinningStrategy createWinningStrategy(){
        return new AlphaWinningStrategy();
    }
}
