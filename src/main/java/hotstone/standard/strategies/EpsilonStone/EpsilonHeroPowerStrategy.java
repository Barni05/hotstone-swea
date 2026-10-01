package hotstone.standard.strategies.EpsilonStone;

import hotstone.framework.*;
import hotstone.framework.strategies.HeroPowerStrategy;
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
    public Status usePower(Game game, Player who) {
        if (game.getPlayerInTurn() != who)
            return Status.NOT_PLAYER_IN_TURN;
        StandardHotStoneGame standardGame = (StandardHotStoneGame) game;
        StandardHero hero = (StandardHero) game.getHero(who);
        Player opponent = Player.computeOpponent(who);
        if (!hero.canUsePower())
            return Status.POWER_USE_NOT_ALLOWED_TWICE_PR_ROUND;

        int targetIndex;
        StandardCard targetCard;
        switch (hero.getType()) {
            case "FrenchChef":
                ArrayList<Card> opponentMinions = standardGame.getPlayersMinions(opponent);
                if (opponentMinions.isEmpty()) return Status.NO_MORE_CARDS;
                targetIndex = indexSelector.selectIndex(opponentMinions.size());
                targetCard = (StandardCard) opponentMinions.get(targetIndex);
                if (targetCard.takeDamage(2) == Status.NO_MORE_HEALTH)
                    standardGame.removeCardFromField(opponent, targetCard);
                break;
            case "ItalianChef":
                ArrayList<Card> ownMinions = standardGame.getPlayersMinions(who);
                if (ownMinions.isEmpty()) return Status.NO_MORE_CARDS;
                targetIndex = indexSelector.selectIndex(ownMinions.size());
                targetCard = (StandardCard) ownMinions.get(targetIndex);
                targetCard.changeAttackBy(2);
                break;
        }

        return Status.OK;
    }
}
