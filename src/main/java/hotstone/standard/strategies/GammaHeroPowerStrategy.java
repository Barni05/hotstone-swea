package hotstone.standard.strategies;

import hotstone.framework.HeroPowerStrategy;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Status;
public class GammaHeroPowerStrategy implements HeroPowerStrategy {
    @Override
    public Status usePower(Game game, Player who){
        return Status.OK;
    }
}
