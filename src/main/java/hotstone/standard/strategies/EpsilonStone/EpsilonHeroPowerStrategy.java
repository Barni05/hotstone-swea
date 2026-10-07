package hotstone.standard.strategies.EpsilonStone;

import hotstone.framework.*;
import hotstone.framework.strategies.HeroPowerStrategy;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHero;
import hotstone.standard.StandardHotStoneGame;
import hotstone.standard.utils.IndexSelector;
import hotstone.standard.utils.RandomIndexSelector;
import hotstone.standard.StandardCard;

import java.util.ArrayList;

public class EpsilonHeroPowerStrategy implements HeroPowerStrategy {
    public final IndexSelector indexSelector;

    public EpsilonHeroPowerStrategy(IndexSelector indexSelector) {
        this.indexSelector = indexSelector;
    }

    @Override
    public Status usePower(MutableGame game, Player who) {
        MutableHero hero = game.getMutableHero(who);
        Player opponent = Player.computeOpponent(who);
        if (!hero.canUsePower())
            return Status.POWER_USE_NOT_ALLOWED_TWICE_PR_ROUND;

        int targetIndex;
        MutableCard targetCard;
        switch (hero.getType()) {
            case GameConstants.FRENCH_CHEF_HERO_TYPE:
                ArrayList<MutableCard> opponentMinions = game.getPlayersMinions(opponent);
                if (opponentMinions.isEmpty()) return Status.NO_MORE_CARDS;
                targetIndex = indexSelector.selectIndex(opponentMinions.size());
                targetCard = opponentMinions.get(targetIndex);
                if (targetCard.deltaHealth(-2) == Status.NO_MORE_HEALTH)
                    game.removeCardFromField(opponent, targetCard);
                break;
            case GameConstants.ITALIAN_CHEF_HERO_TYPE:
                ArrayList<MutableCard> ownMinions = game.getPlayersMinions(who);
                if (ownMinions.isEmpty()) return Status.NO_MORE_CARDS;
                targetIndex = indexSelector.selectIndex(ownMinions.size());
                targetCard = ownMinions.get(targetIndex);
                targetCard.deltaAttack(2);
                break;
        }
        //hero.setCanUsePower(false);
        return Status.OK;
    }
}
