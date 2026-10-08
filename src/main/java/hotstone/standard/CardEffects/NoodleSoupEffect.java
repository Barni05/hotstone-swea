package hotstone.standard.CardEffects;

import hotstone.framework.MutableGame;
import hotstone.framework.MutableHero;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.framework.strategies.Effect;

public class NoodleSoupEffect implements Effect {
    private final Player owner;

    public NoodleSoupEffect(Player owner){
        this.owner = owner;
    }
    @Override
    public Status execute(MutableGame game){
        return game.drawCard(owner);
    }
}
