package hotstone.standard.CardEffects;

import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.framework.strategies.Effect;
import hotstone.standard.HotstoneWizardHubAdapter;
import wizardhub.EffectWizard;

public class BrownRiceEffect implements Effect {
    private final Player owner;

    public BrownRiceEffect(Player owner){
        this.owner = owner;
    }
    @Override
    public Status execute(MutableGame game){
        HotstoneWizardHubAdapter adapter = new HotstoneWizardHubAdapter(game);
        new EffectWizard(adapter).forOpponent().forHero().doChangeHealthOrRemove(-1);
        return Status.OK;
    }
}
