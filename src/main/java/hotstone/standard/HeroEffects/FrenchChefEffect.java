package hotstone.standard.HeroEffects;

import hotstone.framework.MutableCard;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.framework.strategies.Effect;
import hotstone.standard.utils.IndexSelector;

import java.util.ArrayList;

public class FrenchChefEffect implements Effect {
    private final Player owner;
    public final IndexSelector indexSelector;


    public FrenchChefEffect(Player owner, IndexSelector indexSelector) {
        this.owner = owner;
        this.indexSelector = indexSelector;
    }

    @Override
    public Status execute(MutableGame game) {
        ArrayList<MutableCard> opponentMinions = game.getPlayersMinions(Player.computeOpponent(owner));
        if (opponentMinions.isEmpty()) return Status.NO_MORE_CARDS;
        int targetIndex;
        MutableCard targetCard;
        targetIndex = indexSelector.selectIndex(opponentMinions.size());
        targetCard = opponentMinions.get(targetIndex);
        if (targetCard.deltaHealth(-2) == Status.NO_MORE_HEALTH)
            game.removeCardFromField(Player.computeOpponent(owner), targetCard);
        return Status.OK;
    }
}
