package hotstone.standard.HeroEffects;

import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.framework.strategies.Effect;

public class ThaiChefEffect implements Effect {
    private final Player owner;

    public ThaiChefEffect(Player owner){
        this.owner = owner;
    }
    @Override
    public Status execute(MutableGame game){
        game.deltaHealthOnHero(Player.computeOpponent(owner), -2);
        return Status.OK;
    }
}
