package hotstone.standard.strategies.DeltaStone;

import hotstone.framework.ManaProductionStrategy;
import hotstone.framework.Player;

public class DeltaManaProductionStrategy implements ManaProductionStrategy {
    @Override
    public int calculateStartRoundMana(Player who) {
        return 5;
    }
}
