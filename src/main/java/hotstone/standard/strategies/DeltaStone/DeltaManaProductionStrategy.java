package hotstone.standard.strategies.DeltaStone;

import hotstone.framework.MutableGame;
import hotstone.framework.strategies.ManaProductionStrategy;
import hotstone.framework.Player;

public class DeltaManaProductionStrategy implements ManaProductionStrategy {
    @Override
    public int calculateStartRoundMana(MutableGame game, Player who) {
        return 5;
    }
}
