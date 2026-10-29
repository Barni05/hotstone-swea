package hotstone.standard.HeroEffects;

import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.framework.strategies.Effect;
import hotstone.standard.CardEffects.NoEffect;
import hotstone.standard.StandardCard;

public class DanishChefEffect implements Effect {
    private final Player owner;

    public DanishChefEffect(Player owner){
        this.owner = owner;
    }
    @Override
    public Status execute(MutableGame game){
        Effect effect = new NoEffect();
        StandardCard sovs = new StandardCard("Sovs", "It's a special minion", 0, 1, 1, false, owner, effect);
        game.insertCardIntoField(owner, sovs, 0);
        return Status.OK;

    }
}
