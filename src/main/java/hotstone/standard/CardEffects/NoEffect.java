package hotstone.standard.CardEffects;

import hotstone.framework.MutableCard;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.framework.strategies.Effect;

public class NoEffect implements Effect {
    @Override
    public Status execute(MutableGame game){
        return Status.OK;
    }
}
