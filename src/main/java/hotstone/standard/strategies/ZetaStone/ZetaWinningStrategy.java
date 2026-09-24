package hotstone.standard.strategies.ZetaStone;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.WinningStrategy;
import hotstone.standard.StandardCard;
import hotstone.standard.StandardHotStoneGame;

public class ZetaWinningStrategy implements WinningStrategy {
    private WinningStrategy afterThreeRoundStrategy, afterSixRoundStrategy;

    public ZetaWinningStrategy(WinningStrategy afterThreeRoundStrategy, WinningStrategy afterSixRoundStrategy){
        this.afterThreeRoundStrategy = afterThreeRoundStrategy;
        this.afterSixRoundStrategy = afterSixRoundStrategy;
    }

    public Player calculateWinner(Game game){
        StandardHotStoneGame standardGame = (StandardHotStoneGame) game;
        int roundNumber = standardGame.getRoundNumber();
        if(roundNumber > 3 && roundNumber <= 6){
            return afterThreeRoundStrategy.calculateWinner(game);
        }else if(roundNumber > 6){
            return afterSixRoundStrategy.calculateWinner(game);
        }
        return null;
    }
}
