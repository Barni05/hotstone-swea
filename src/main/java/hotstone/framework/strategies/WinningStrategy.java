package hotstone.framework.strategies;

import hotstone.framework.Game;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;

public interface WinningStrategy {
    /**
     * @param game - the game object
     * @return returns the winner player, or null if none
     * */
    Player calculateWinner(MutableGame game);

}
