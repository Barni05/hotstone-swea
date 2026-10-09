package hotstone.standard.factories;

import hotstone.framework.HotStoneFactory;
import hotstone.framework.strategies.*;
import hotstone.standard.strategies.Alphastone.*;
import hotstone.standard.strategies.Gammastone.ThaiDanishChefHeroCreationStrategy;
import hotstone.standard.strategies.Gammastone.GammaWinningStrategy;

public class GammaStoneFactory implements HotStoneFactory {
    @Override
    public DeckBuildingStrategy createDeckBuildingStrategy(){
        return new AlphaDeckBuildingStrategy();
    }

    @Override
    public HeroCreationStrategy createHeroCreationStrategy(){
        return new ThaiDanishChefHeroCreationStrategy();
    }

    @Override
    public ManaProductionStrategy createManaProductionStrategy(){
        return new AlphaManaProductionStrategy();
    }

    @Override
    public WinningStrategy createWinningStrategy(){
        return new GammaWinningStrategy();
    }
}
