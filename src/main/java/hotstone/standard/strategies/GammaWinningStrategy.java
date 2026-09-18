package hotstone.standard.strategies;

import hotstone.framework.WinningStrategy;
import hotstone.framework.Player;

public class GammaWinningStrategy implements WinningStrategy {
    @Override
    public Player calculateWinner(int turnNumber){
        return Player.FINDUS;
    }
}
