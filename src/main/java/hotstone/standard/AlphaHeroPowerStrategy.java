package hotstone.standard;

import hotstone.framework.*;
import hotstone.standard.StandardHotStoneGame;

public class AlphaHeroPowerStrategy implements HeroPowerStrategy {
    @Override
    public Status usePower(Game game, Player who){
        Hero baby = game.getHero(who);
        int mana = baby.getMana();
        if (mana >= 2) return Status.OK;
        return Status.NOT_ENOUGH_MANA;
    }
}
