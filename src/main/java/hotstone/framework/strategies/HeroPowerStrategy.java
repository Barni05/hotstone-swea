package hotstone.framework.strategies;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Status;

public interface HeroPowerStrategy {
    /**
     * @param game the mutable game instance
     * @param who the player who is using the power
     * */
    Status usePower(Game game, Player who);

}
