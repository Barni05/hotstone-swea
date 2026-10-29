package hotstone.standard.factories;

import hotstone.framework.HotStoneFactory;
import hotstone.framework.strategies.DeckBuildingStrategy;
import hotstone.framework.strategies.HeroCreationStrategy;
import hotstone.framework.strategies.ManaProductionStrategy;
import hotstone.framework.strategies.WinningStrategy;
import hotstone.standard.strategies.BetaStone.BetaManaProductionStrategy;
import hotstone.standard.strategies.BetaStone.BetaWinningStrategy;
import hotstone.standard.strategies.EpsilonStone.FrenchItalianChefHeroCreationStrategy;
import hotstone.standard.strategies.EtaStone.ModifiedDishDeckBuildingStrategy;
import hotstone.standard.strategies.Gammastone.GammaWinningStrategy;
import hotstone.standard.strategies.SemiStone.ThaiDanishFrenchItalianRandomHeroCreation;
import hotstone.standard.strategies.ZetaStone.ZetaWinningStrategy;
import hotstone.standard.utils.IndexSelector;
import hotstone.standard.utils.RandomIndexSelector;

public class SemiStoneFactory implements HotStoneFactory {
    private final IndexSelector indexSelector;

    public SemiStoneFactory(){indexSelector = new RandomIndexSelector();
    }

    public SemiStoneFactory(IndexSelector indexSelector){this.indexSelector = indexSelector;}

    @Override
    public DeckBuildingStrategy createDeckBuildingStrategy() {
        return new ModifiedDishDeckBuildingStrategy(indexSelector);
    }

    @Override
    public HeroCreationStrategy createHeroCreationStrategy() {
        return new ThaiDanishFrenchItalianRandomHeroCreation(indexSelector);
    }

    @Override
    public ManaProductionStrategy createManaProductionStrategy() {

        return new BetaManaProductionStrategy();
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return new BetaWinningStrategy();
    }
}
