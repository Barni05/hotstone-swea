package hotstone.standard.strategies.Alphastone;

import hotstone.framework.*;
import hotstone.framework.strategies.HeroPowerStrategy;

public class AlphaHeroPowerStrategy implements HeroPowerStrategy {
    @Override
    public Status usePower(Game game, Player who){
        Hero baby = game.getHero(who);
        int mana = baby.getMana();
        if (mana >= 2) return Status.OK;
        return Status.NOT_ENOUGH_MANA;
    }
}
