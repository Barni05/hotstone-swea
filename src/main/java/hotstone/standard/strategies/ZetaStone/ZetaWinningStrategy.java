package hotstone.standard.strategies.ZetaStone;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.WinningStrategy;
import hotstone.standard.StandardCard;
import hotstone.standard.StandardHotStoneGame;

public class ZetaWinningStrategy implements WinningStrategy {
    private WinningStrategy afterThreeRoundStrategy, afterSixRoundStrategy, currentStrategy;

    public ZetaWinningStrategy(WinningStrategy afterThreeRoundStrategy, WinningStrategy afterSixRoundStrategy){
        this.afterThreeRoundStrategy = afterThreeRoundStrategy;
        this.afterSixRoundStrategy = afterSixRoundStrategy;
        this.currentStrategy = afterThreeRoundStrategy;
    }

    public Player calculateWinner(Game game){
        StandardHotStoneGame standardGame = (StandardHotStoneGame) game;
        int roundNumber = standardGame.getRoundNumber();
        if(roundNumber > 6){
            currentStrategy = afterSixRoundStrategy;
        }
        return currentStrategy.calculateWinner(game);
    }
}
