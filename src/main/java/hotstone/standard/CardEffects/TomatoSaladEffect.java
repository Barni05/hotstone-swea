package hotstone.standard.CardEffects;

import hotstone.framework.MutableCard;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.framework.strategies.Effect;
import hotstone.standard.utils.IndexSelector;

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
        ArrayList<MutableCard> ownMinions = game.getPlayersMinions(owner);
        if (ownMinions.isEmpty()) return Status.NO_MORE_CARDS;
        int targetIndex;
        MutableCard targetCard;
        targetIndex = indexSelector.selectIndex(ownMinions.size());
        targetCard = ownMinions.get(targetIndex);
        targetCard.deltaAttack(1);
        return Status.OK;
    }
}
