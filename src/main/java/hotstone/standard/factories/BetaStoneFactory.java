package hotstone.standard.factories;

import hotstone.framework.HotStoneFactory;
import hotstone.framework.strategies.*;
import hotstone.standard.strategies.Alphastone.*;
import hotstone.standard.strategies.BetaStone.BetaManaProductionStrategy;
import hotstone.standard.strategies.BetaStone.BetaWinningStrategy;

public class BetaStoneFactory implements HotStoneFactory {
    @Override
    public DeckBuildingStrategy createDeckBuildingStrategy(){
        return new AlphaDeckBuildingStrategy();
    }

    @Override
    public HeroCreationStrategy createHeroCreationStrategy(){
        return new AlphaHeroCreationStrategy();
    }

    @Override
    public HeroPowerStrategy createHeroPowerStrategy(){
        return new AlphaHeroPowerStrategy();
    }

    @Override
    public ManaProductionStrategy createManaProductionStrategy(){
        return new BetaManaProductionStrategy();
    }

    @Override
    public WinningStrategy createWinningStrategy(){
        return new BetaWinningStrategy();
    }
}
