package hotstone.standard.strategies;

import hotstone.framework.*;

public class AlphaWinningStrategy implements WinningStrategy {
    @Override
    public Player calculateWinner(Game game){
        if (game.getTurnNumber() <= 4) return null;
        return Player.FINDUS;
    }
}
