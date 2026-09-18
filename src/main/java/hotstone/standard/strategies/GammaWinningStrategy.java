package hotstone.standard.strategies;

import hotstone.framework.WinningStrategy;
import hotstone.framework.Player;
import hotstone.framework.Game;
public class GammaWinningStrategy implements WinningStrategy {
    /*
    * The winner is the player that first manages to clear the opponent’s field
of minions, that is, the opponent has no minions on his/her battlefield. This
winning condition only applies after the game has passed round 3. If both
player’s field is empty after round 3, Peddersen wins
    * */
    @Override
    public Player calculateWinner(Game game) {
        int roundNumber = game.getTurnNumber() / 2;
        if(roundNumber <= 3)
            return null;
        boolean findusFieldEmpty = game.getField(Player.FINDUS).isEmpty();
        boolean peddersenFieldEmpty = game.getField(Player.PEDDERSEN).isEmpty();

        if (findusFieldEmpty && peddersenFieldEmpty) {
            return Player.PEDDERSEN;
        }
        if (peddersenFieldEmpty) {
            return Player.FINDUS;
        }
        if (findusFieldEmpty) {
            return Player.PEDDERSEN;
        }
        return null;
    }
}
