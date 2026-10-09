package hotstone.standard;

import hotstone.framework.Player;
import hotstone.standard.factories.ZetaStoneFactory;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestZetaStone {
    private StandardHotStoneGame game;

    @BeforeEach
    public void setupZetaStone(){
        game = new StandardHotStoneGame(new ZetaStoneFactory());
    }

    @Test
    public void findusWinsWhenHeClearsPeddersenFieldinRoundFour(){
        game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS,0), 0);
        for(int i = 0; i < 8; i++){
            game.endTurn();
        }
        assertThat(game.getWinner(), is(Player.FINDUS));
    }

    @Test
    public void peddersenWinsWhenHeClearsFindusFieldinRoundFour(){
        game.endTurn();
        game.playCard(Player.PEDDERSEN, game.getCardInHand(Player.PEDDERSEN,0), 0);
        for(int i = 0; i < 7; i++){
            game.endTurn();
        }
        assertThat(game.getWinner(), is(Player.PEDDERSEN));
    }

    @Test
    public void peddersenWinsWhenFindusDiesInRoundSeven(){
        game.endTurn();
        game.heroTakeDamage(Player.FINDUS, 21);
        for(int i = 0; i < 13; i++){
            game.endTurn();
        }
        assertThat(game.getWinner(), is(Player.PEDDERSEN ));
    }

    @Test
    public void peddersenWinsWhenHeClearsFindusFieldinRoundSix(){
        game.endTurn();
        game.playCard(Player.PEDDERSEN, game.getCardInHand(Player.PEDDERSEN,0), 0);
        for(int i = 0; i < 7; i++){
            game.endTurn();
        }
        assertThat(game.getWinner(), is(Player.PEDDERSEN));
    }

    @Test
    public void noWinnerBeforeRoundToEvenWhenFieldIsCleared(){
        game.endTurn();
        game.endTurn();
        assertThat(game.getWinner(), is(nullValue()));
    }
    @Test
    public void noWinnerWhenFieldsAreEmptyPastRoundSix(){
        for(int i = 0; i < 13; i++){
            game.endTurn();
        }
        assertThat(game.getWinner(), is(nullValue()));
    }
}

