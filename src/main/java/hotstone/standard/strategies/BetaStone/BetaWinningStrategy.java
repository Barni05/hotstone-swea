package hotstone.standard.strategies.BetaStone;

import hotstone.framework.Game;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.strategies.WinningStrategy;

public class BetaWinningStrategy implements WinningStrategy {

    @Override
    public Player calculateWinner(MutableGame game) {
        if (game == null) {
            throw new IllegalStateException("BetaWinningStrategy must be attached to a game");
        }
        if (game.getHero(Player.FINDUS).getHealth() <= 0) {
            return Player.PEDDERSEN;
        }
        if (game.getHero(Player.PEDDERSEN).getHealth() <= 0) {
            return Player.FINDUS;
        }
        return null;
    }
}
