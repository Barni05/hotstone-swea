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

    public EpsilonHeroPowerStrategy(IndexSelector indexSelector){
        this.indexSelector = indexSelector;
    }

    @Override
    public Status usePower(MutableGame game, Player who) {
        if (game.getPlayerInTurn() != who)
            return Status.NOT_PLAYER_IN_TURN;
        StandardHero hero = (StandardHero) game.getHero(who);
        Player opponent = Player.computeOpponent(who);
        if (!hero.canUsePower())
            return Status.POWER_USE_NOT_ALLOWED_TWICE_PR_ROUND;
        switch (hero.getType()) {
            case "FrenchChef":
                ArrayList<Card> opponentMinions = game.getPlayersMinions(who);
                if(opponentMinions.isEmpty()) return Status.NO_MORE_CARDS;
                int targetIndex = indexSelector.selectIndex(opponentMinions.size());
                StandardCard target = (StandardCard) opponentMinions.get(targetIndex);
                if(target.takeDamage(2) == Status.NO_MORE_HEALTH)
                    game.removeCardFromField(opponent, target);
                break;
            case "ItalianChef":
                break;
        }

        return Status.OK;
    }
}
