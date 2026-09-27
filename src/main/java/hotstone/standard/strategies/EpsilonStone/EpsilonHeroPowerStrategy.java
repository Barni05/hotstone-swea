package hotstone.standard.strategies.EpsilonStone;

import hotstone.framework.*;
import hotstone.framework.strategies.HeroPowerStrategy;
import hotstone.standard.StandardHero;
import hotstone.standard.StandardHotStoneGame;

public class EpsilonHeroPowerStrategy implements HeroPowerStrategy {
    @Override
    public Status usePower(Game game, Player who) {
        if (game.getPlayerInTurn() != who)
            return Status.NOT_PLAYER_IN_TURN;
        StandardHotStoneGame standardGame = (StandardHotStoneGame) game;
        StandardHero hero = (StandardHero) game.getHero(who);
        if (!hero.canUsePower())
            return Status.POWER_USE_NOT_ALLOWED_TWICE_PR_ROUND;
        switch (hero.getType()) {
            case "FrenchChef":
                break;
            case "ItalianChef":
                break;
        }

        return Status.OK;
    }
}
