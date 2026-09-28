package hotstone.standard.strategies.DeltaStone;

import hotstone.framework.Game;
import hotstone.framework.strategies.ManaProductionStrategy;
import hotstone.framework.Player;

public class DeltaManaProductionStrategy implements ManaProductionStrategy {
    @Override
    public int calculateStartRoundMana(Player who, Game game) {
        return 5;
    }
}
