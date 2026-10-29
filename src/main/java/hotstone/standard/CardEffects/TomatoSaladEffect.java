package hotstone.standard.CardEffects;

import hotstone.framework.MutableCard;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.framework.strategies.Effect;
import hotstone.standard.HotstoneWizardHubAdapter;
import hotstone.standard.utils.IndexSelector;
import wizardhub.EffectWizard;

import java.util.ArrayList;

public class TomatoSaladEffect implements Effect {
    private final Player owner;
    private final IndexSelector indexSelector;

    public TomatoSaladEffect(Player owner, IndexSelector indexSelector){
        this.owner = owner;
        this.indexSelector = indexSelector;
    }
    @Override
    public Status execute(MutableGame game){
        HotstoneWizardHubAdapter adapter = new HotstoneWizardHubAdapter(game);
        wizardhub.EffectWizard effectWizard = new EffectWizard(adapter);

        // Determining index
        int fieldSize = game.getFieldSize(owner);
        if (fieldSize == 0) return Status.NO_MORE_CARDS;
        int targetIndex;
        targetIndex = indexSelector.selectIndex(fieldSize);

        effectWizard.forMe().forMinionAt(targetIndex).doChangeAttack(1);

        return Status.OK;
    }
}
