package hotstone.standard.CardEffects;

import hotstone.framework.MutableCard;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.framework.strategies.Effect;
import hotstone.standard.utils.IndexSelector;

import java.util.ArrayList;

public class SpringRollsEffect implements Effect {
    private final Player owner;
    private final IndexSelector indexSelector;

    public SpringRollsEffect(Player owner, IndexSelector indexSelector){
        this.owner = owner;
        this.indexSelector = indexSelector;
    }
    @Override
    public Status execute(MutableGame game){
        ArrayList<MutableCard> opponentMinion = game.getPlayersMinions(Player.computeOpponent(owner));
        if (opponentMinion.isEmpty()) return Status.NO_MORE_CARDS;
        int targetIndex;
        MutableCard targetCard;
        targetIndex = indexSelector.selectIndex(opponentMinion.size());
        targetCard = opponentMinion.get(targetIndex);
        return game.removeCardFromField(Player.computeOpponent(owner), targetCard);
    }
}
