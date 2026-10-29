package hotstone.standard;
import hotstone.framework.*;
import hotstone.framework.transcribe.GameLogDecorator;
import hotstone.standard.CardEffects.NoEffect;
import hotstone.standard.factories.AlphaStoneFactory;
import hotstone.standard.strategies.Alphastone.*;
import org.junit.jupiter.api.*;
import wizardhub.EffectWizard;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
public class DecoratorTest {
    MutableGame realGame;
    MutableGame game;

    @BeforeEach
    public void setupTest(){
        realGame = new StandardHotStoneGame(new AlphaStoneFactory());
        game = new GameLogDecorator(realGame);
    }
    @Test
    public void manualDecoratorTest(){
        // Verify behaviour with decoration
        game.drawCard(Player.FINDUS);
        game.endTurn();

        // Turn off decorator
        game = realGame;
        game.drawCard(Player.FINDUS);
        game.endTurn();

        //Turn back again
        game = new GameLogDecorator(realGame);
        game.attackHero(Player.FINDUS, new StandardCard("Gizmo öröksége", "Világbéke", 1, 1, 1, true, Player.FINDUS, new NoEffect()));
    }
}
