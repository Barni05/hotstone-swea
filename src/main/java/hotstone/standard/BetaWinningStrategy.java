package hotstone.standard;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.WinningStrategy;

public class BetaWinningStrategy implements WinningStrategy {
    private Game game;

    @Override
    public Player calculateWinner(Game game) {
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
