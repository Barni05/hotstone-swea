package hotstone.standard.CardEffects;

import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.framework.strategies.Effect;
import hotstone.standard.HotstoneWizardHubAdapter;
import wizardhub.EffectWizard;

public class PokeBowlEffect implements Effect {
    private final Player owner;

    public PokeBowlEffect(Player owner){
        this.owner = owner;
    }
    @Override
    public Status execute(MutableGame game){
        HotstoneWizardHubAdapter adapter = new HotstoneWizardHubAdapter(game);
        EffectWizard effectWizard = new EffectWizard(adapter);
        effectWizard.forMe().forHero().doChangeHealthOrRemove(2);
        return Status.OK;
    }
}
