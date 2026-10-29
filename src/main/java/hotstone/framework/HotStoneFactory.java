package hotstone.framework;

import hotstone.framework.strategies.*;

public interface HotStoneFactory {
    DeckBuildingStrategy createDeckBuildingStrategy();
    HeroCreationStrategy createHeroCreationStrategy();
    ManaProductionStrategy createManaProductionStrategy();
    WinningStrategy createWinningStrategy();
}
