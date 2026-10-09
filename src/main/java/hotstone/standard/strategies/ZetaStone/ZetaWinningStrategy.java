package hotstone.standard.strategies.ZetaStone;

import hotstone.framework.Game;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.strategies.WinningStrategy;
import hotstone.standard.StandardHotStoneGame;

public class ZetaWinningStrategy implements WinningStrategy {
    private WinningStrategy afterThreeRoundStrategy, afterSixRoundStrategy, currentStrategy;

    public ZetaWinningStrategy(WinningStrategy afterThreeRoundStrategy, WinningStrategy afterSixRoundStrategy){
        this.afterThreeRoundStrategy = afterThreeRoundStrategy;
        this.afterSixRoundStrategy = afterSixRoundStrategy;
        this.currentStrategy = afterThreeRoundStrategy;
    }

    public Player calculateWinner(MutableGame game){
        int roundNumber = game.getTurnNumber() / 2 + 1;
        if(roundNumber > 6){
            currentStrategy = afterSixRoundStrategy;
        }
        return currentStrategy.calculateWinner(game);
    }
}
