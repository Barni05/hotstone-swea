package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.framework.Status;
import org.junit.jupiter.api.*;
import hotstone.standard.strategies.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import hotstone.framework.Game;

public class TestGammaStone {
    private StandardHotStoneGame game;

    @BeforeEach
    public void setupGammaStone(){
        game = new StandardHotStoneGame(new AlphaManaProductionStrategy(), new GammaWinningStrategy(), new GammaHeroCreationStrategy(), new GammaHeroPowerStrategy());
    }

    // Hero tests

    @Test
    public void findusShouldHaveThaiChefWhenGameCreated(){
        assertThat(game.getHero(Player.FINDUS).getType(), is("ThaiChef"));
    }

    @Test
    public void peddersenShouldHaveDanishChefWhenGameCreated(){
        assertThat(game.getHero(Player.PEDDERSEN).getType(), is("DanishChef"));
    }
}
