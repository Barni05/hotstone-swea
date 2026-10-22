package hotstone.standard.CardEffects;

import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.framework.strategies.Effect;

public class BrownRiceEffect implements Effect {
    private final Player owner;

    public BrownRiceEffect(Player owner){
        this.owner = owner;
    }
    @Override
    public Status execute(MutableGame game){
        return game.deltaHealthOnHero(Player.computeOpponent(owner), -1);
    }
}
