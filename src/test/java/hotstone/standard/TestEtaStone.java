package hotstone.standard;

import hotstone.framework.Player;
import hotstone.framework.strategies.Effect;
import hotstone.standard.CardEffects.*;
import hotstone.standard.factories.EtaStoneFactory;
import hotstone.standard.factories.ZetaStoneFactory;
import hotstone.standard.utils.FixedIndexSelectorStub;
import hotstone.utility.MutableGameSpy;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestEtaStone {

    private StandardHotStoneGame game;
    private FixedIndexSelectorStub stub = new FixedIndexSelectorStub(0);
    @BeforeEach
    public void setupEtaStone(){
        game = new StandardHotStoneGame(new EtaStoneFactory(stub));
    }

    /*
    *  --------------- UNIT TESTS -------------------
    * */

    @Test
    public void brownRiceCardShouldDeal1DamageToOpponent(){
        MutableGameSpy spyGame = new MutableGameSpy();
        Effect brownRice = new BrownRiceEffect(Player.FINDUS);
        brownRice.execute(spyGame);
        assertThat(spyGame.getLastCall(), is("heroTakeDamage(PEDDERSEN,1)"));
    }
    @Test
    public void tomatoSaladShouldMakeEffect(){
        MutableGameSpy spyGame = new MutableGameSpy();
        Effect tomatoSalad = new TomatoSaladEffect(Player.FINDUS, stub);
        spyGame.insertCardIntoField(Player.FINDUS, new StandardCard(GameConstants.TOMATO_SALAD_CARD, "Test", 1,1,1, false, Player.FINDUS, tomatoSalad ), 0);
        tomatoSalad.execute(spyGame);
        assertThat(spyGame.getLastCall(), is("deltaAttackOnCard(FINDUS,0,1)"));
    }
    @Test
    public void pokeBowlEffectShouldHealHeroBy2(){
        MutableGameSpy spy = new MutableGameSpy();
        Effect effect = new PokeBowlEffect(Player.FINDUS);
        effect.execute(spy);
        assertThat(spy.getLastCall(), is("heroTakeDamage(FINDUS,-2)"));
    }

    @Test
    public void noodleSoupEffectShouldTellGameToDrawCard() {
        MutableGameSpy spy = new MutableGameSpy();
        Effect effect = new NoodleSoupEffect(Player.FINDUS);

        effect.execute(spy);

        assertThat(spy.getLastCall(), is("drawCard(FINDUS)"));
    }

    @Test
    public void springRollEffectShouldDestroyOpponentMinion(){
        MutableGameSpy spy = new MutableGameSpy();
        stub.setIndex(0);
        Effect effect = new SpringRollsEffect(Player.PEDDERSEN, stub);
        effect.execute(spy);
        assertThat(spy.getLastCall(), is("removeCardFromField(FINDUS,0)"));
    }

    @Test
    public void bakedSalmonEffectShouldAdd2AttackToOpponentMinion(){
        MutableGameSpy spy = new MutableGameSpy();
        stub.setIndex(0);
        Effect effect = new BakedSalmonEffect(Player.FINDUS, stub);
        effect.execute(spy);
        assertThat(spy.getLastCall(), is("deltaAttackOnCard(PEDDERSEN,0,2)"));
    }
}
