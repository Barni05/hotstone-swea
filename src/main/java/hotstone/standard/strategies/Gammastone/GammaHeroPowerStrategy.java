package hotstone.standard.strategies.Gammastone;

import hotstone.framework.strategies.HeroPowerStrategy;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.standard.StandardHero;
import hotstone.standard.StandardHotStoneGame;
import hotstone.standard.StandardCard;

public class GammaHeroPowerStrategy implements HeroPowerStrategy {
    @Override
    public Status usePower(Game game, Player who){
        if(game.getPlayerInTurn() != who){
            return Status.NOT_PLAYER_IN_TURN;
        }
        StandardHotStoneGame standardGame = (StandardHotStoneGame)game;
        StandardHero hero = (StandardHero)standardGame.getHero(who);
        if(hero.canUsePower()){
            switch(hero.getType()){
                case "ThaiChef":
                    standardGame.takeDamage(Player.computeOpponent(who), 2);
                    return Status.OK;
                case "DanishChef":
                    StandardCard sovs = new StandardCard("Sovs", "It's a special minion", 0, 1, 1, false, who);
                    standardGame.insertCardIntoField(who, sovs, 0);
                    return Status.OK;
            }
        }
        return Status.POWER_USE_NOT_ALLOWED_TWICE_PR_ROUND;
    }
}
