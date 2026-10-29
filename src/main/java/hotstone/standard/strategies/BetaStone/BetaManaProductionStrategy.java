package hotstone.standard.strategies.BetaStone;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.strategies.ManaProductionStrategy;

public class BetaManaProductionStrategy implements ManaProductionStrategy {

    @Override
    public int calculateStartRoundMana(MutableGame game, Player who) {
        if (game == null) {
            throw new IllegalStateException("BetaManaProductionStrategy must be attached to a game");
        }
        return Math.min(game.getTurnNumber() + 1, 7);
    }
}
