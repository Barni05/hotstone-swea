package hotstone.standard.HeroEffects;

import hotstone.framework.MutableGame;
import hotstone.framework.MutableHero;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.framework.strategies.Effect;
import hotstone.standard.StandardCard;

public class BabyHeroEffect implements Effect {
    private final Player owner;

    public BabyHeroEffect(Player owner){
        this.owner = owner;
    }
    @Override
    public Status execute(MutableGame game){
        MutableHero baby = game.getMutableHero(owner);
        return baby.deltaMana(-2);
    }
}
