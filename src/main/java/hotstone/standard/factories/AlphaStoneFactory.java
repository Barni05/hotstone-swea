package hotstone.standard.factories;

import hotstone.framework.HotStoneFactory;
import hotstone.framework.strategies.*;
import hotstone.standard.strategies.Alphastone.*;

public class AlphaStoneFactory implements HotStoneFactory {
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
        return new AlphaManaProductionStrategy();
    }

    @Override
    public WinningStrategy createWinningStrategy(){
        return new AlphaWinningStrategy();
    }
}
