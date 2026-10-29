package hotstone.standard.HeroEffects;

import hotstone.framework.MutableCard;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.framework.strategies.Effect;
import hotstone.standard.utils.IndexSelector;

import java.util.ArrayList;

public class ItalianChefEffect implements Effect {
    private final Player owner;
    public final IndexSelector indexSelector;


    public ItalianChefEffect(Player owner, IndexSelector indexSelector) {
        this.owner = owner;
        this.indexSelector = indexSelector;
    }

    @Override
    public Status execute(MutableGame game) {
        int fieldSize = game.getFieldSize(owner);
        if (fieldSize == 0) return Status.NO_MORE_CARDS;
        int targetIndex;
        targetIndex = indexSelector.selectIndex(fieldSize);
        game.deltaAttackOnCard(owner, targetIndex, 2);
        return Status.OK;
    }
}
