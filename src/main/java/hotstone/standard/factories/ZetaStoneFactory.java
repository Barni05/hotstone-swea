package hotstone.standard.factories;

import hotstone.framework.HotStoneFactory;
import hotstone.framework.strategies.*;
import hotstone.standard.strategies.Alphastone.*;
import hotstone.standard.strategies.BetaStone.BetaWinningStrategy;
import hotstone.standard.strategies.Gammastone.GammaWinningStrategy;
import hotstone.standard.strategies.ZetaStone.ZetaWinningStrategy;

public class ZetaStoneFactory implements HotStoneFactory {
    @Override
    public DeckBuildingStrategy createDeckBuildingStrategy(){
        return new AlphaDeckBuildingStrategy();
    }

    @Override
    public HeroCreationStrategy createHeroCreationStrategy(){
        return new BabyHeroCreationStrategy();
    }

    @Override
    public ManaProductionStrategy createManaProductionStrategy(){
        return new AlphaManaProductionStrategy();
    }

    @Override
    public WinningStrategy createWinningStrategy(){
        return new ZetaWinningStrategy(new GammaWinningStrategy(), new BetaWinningStrategy());
    }
}
