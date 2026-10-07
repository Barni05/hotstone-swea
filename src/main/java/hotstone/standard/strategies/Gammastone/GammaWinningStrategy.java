package hotstone.standard.strategies.Gammastone;

import hotstone.framework.MutableGame;
import hotstone.framework.strategies.WinningStrategy;
import hotstone.framework.Player;
import hotstone.framework.Game;
import hotstone.standard.StandardHotStoneGame;

public class GammaWinningStrategy implements WinningStrategy {
    /*
    * The winner is the player that first manages to clear the opponent’s field
of minions, that is, the opponent has no minions on his/her battlefield. This
winning condition only applies after the game has passed round 3. If both
player’s field is empty after round 3, Peddersen wins
    * */
    @Override
    public Player calculateWinner(MutableGame game) {
        StandardHotStoneGame standardGame = (StandardHotStoneGame) game;
        int roundNumber = standardGame.getTurnNumber() / 2;
        if(roundNumber <= 3)
            return null;
        boolean findusFieldEmpty = true;
        boolean peddersenFieldEmpty = true;
        for(int i = 0; i < standardGame.FIELD_CAPACITY; i++){
            if(standardGame.getCardInField(Player.FINDUS, i) != null)
                findusFieldEmpty = false;
            if(standardGame.getCardInField(Player.PEDDERSEN, i) != null)
                peddersenFieldEmpty = false;
        }

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
