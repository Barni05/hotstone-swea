package hotstone.framework;

import hotstone.framework.strategies.*;

public interface HotStoneFactory {
    DeckBuildingStrategy createDeckBuildingStrategy();
    HeroCreationStrategy createHeroCreationStrategy();
    HeroPowerStrategy createHeroPowerStrategy();
    ManaProductionStrategy createManaProductionStrategy();
    WinningStrategy createWinningStrategy();
}
