package hotstone.standard.strategies.Alphastone;

import hotstone.framework.*;
import hotstone.framework.strategies.WinningStrategy;

public class AlphaWinningStrategy implements WinningStrategy {
    @Override
    public Player calculateWinner(MutableGame game){
        if (game.getTurnNumber() <= 4) return null;
        return Player.FINDUS;
    }
}
