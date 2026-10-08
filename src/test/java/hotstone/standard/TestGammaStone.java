package hotstone.standard;

import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.standard.factories.GammaStoneFactory;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestGammaStone {
    private StandardHotStoneGame game;

    @BeforeEach
    public void setupGammaStone(){
        game = new StandardHotStoneGame(new GammaStoneFactory());
    }

    // Hero tests

    @Test
    public void findusShouldHaveThaiChefWhenGameCreated(){
        assertThat(game.getHero(Player.FINDUS).getType(), is(GameConstants.THAI_CHEF_HERO_TYPE));
    }

    @Test
    public void peddersenShouldHaveDanishChefWhenGameCreated(){
        assertThat(game.getHero(Player.PEDDERSEN).getType(), is(GameConstants.DANISH_CHEF_HERO_TYPE));
    }

    @Test
    public void findusPlaysHeroPowerPeddersenTakesDamage(){
        int peddersenHealthBefore = game.getHero(Player.PEDDERSEN).getHealth();
        game.usePower(Player.FINDUS);
        int peddersenHealthAfter = game.getHero(Player.PEDDERSEN).getHealth();
        assertThat(peddersenHealthBefore-2, is(peddersenHealthAfter));
    }
    @Test
    public void playerCantUsePowerWhenNotTheirTurn(){
        assertThat(game.usePower(Player.PEDDERSEN), is(not(Status.OK)));
    }
    @Test
    public void sovsShouldBeOnFieldAfterPlayingDanishChef(){
        game.endTurn();
        game.usePower(Player.PEDDERSEN);
        assertThat(game.getCardInField(Player.PEDDERSEN, 0).getName(), is("Sovs"));
    }

    // Win condition tests
    @Test
    public void findusShouldWinWhenPeddersenFieldEmpty(){
        for(int i = 0; i < 8; i++){
            game.endTurn();
        }
        game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 0), 0);
        assertThat(game.getWinner(), is(Player.FINDUS));
    }
    @Test
    public void noWinnerShouldBeDeclaredWhenRoundIsLessThanThree(){
        game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 0), 0);
        game.endTurn();
        game.endTurn();
        game.endTurn();
        assertThat(game.getWinner(), is(nullValue()));
    }

    @Test
    public void noWinnerDuringRoundThree(){
        game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 0), 0);
        for(int i = 0; i < 5; i++){
            game.endTurn();
        }
        assertThat(game.getWinner(), is(nullValue()));
    }

    @Test
    public void peddersenWinsWhenBothFieldsAreEmpty(){
        for(int i = 0; i < 8; i++){
            game.endTurn();
        }
        assertThat(game.getWinner(), is(Player.PEDDERSEN));
    }

    @Test
    public void noWinnerIfBothPlayersHaveMinionsOnField(){
        game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 0), 1);
        game.endTurn();
        game.playCard(Player.PEDDERSEN, game.getCardInHand(Player.PEDDERSEN, 0), 0);
        for(int i = 0; i < 7; i++){
            game.endTurn();
        }
        assertThat(game.getWinner(), is(nullValue()));

    }
}
