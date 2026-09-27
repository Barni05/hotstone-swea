package hotstone.standard.strategies.BetaStone;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.strategies.ManaProductionStrategy;

public class BetaManaProductionStrategy implements ManaProductionStrategy {
    private Game game;

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public int calculateStartRoundMana(Player who) {
        if (game == null) {
            throw new IllegalStateException("BetaManaProductionStrategy must be attached to a game");
        }
        return Math.min(game.getTurnNumber() + 1, 7);
    }
}
