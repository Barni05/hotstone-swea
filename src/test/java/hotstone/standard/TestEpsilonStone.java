package hotstone.standard;

import hotstone.framework.Player;
import hotstone.standard.factories.EpsilonStoneFactory;
import hotstone.standard.utils.FixedIndexSelectorStub;
import org.junit.jupiter.api.*;
import hotstone.framework.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestEpsilonStone {
    private StandardHotStoneGame game;
    private FixedIndexSelectorStub stub;

    @BeforeEach
    public void setupEpsilonStone() {
        stub = new FixedIndexSelectorStub(0);
        game = new StandardHotStoneGame(new EpsilonStoneFactory(stub));
    }

    @Test
    public void peddersenMinionDiesWhenFindusPlaysPower() {
        game.endTurn();
        // Card is UNO (1,1,1)
        game.playCard(Player.PEDDERSEN, game.getCardInHand(Player.PEDDERSEN, 0), 0);
        game.endTurn();
        stub.setIndex(0);
        game.usePower(Player.FINDUS);
        assertThat(game.getCardInField(Player.PEDDERSEN, 0), is(nullValue()));
    }

    @Test
    public void heroPowerShouldHaveNoEffectWhenFieldIsEmpty() {
        assertThat(game.getPlayersMinions(Player.PEDDERSEN).size(), is(0));
        assertThat(game.usePower(Player.FINDUS), is(Status.NO_MORE_CARDS));
    }

    @Test
    public void frenchChefShouldDamageSelectedMinionByTwo() {
        game.endTurn();
        for (int i = 0; i < 3; i++) {
            game.playCard(Player.PEDDERSEN, game.getCardInHand(Player.PEDDERSEN, 0), i);
            game.endTurn();
            game.endTurn();
        }
        int testIndex = 0;
        for (int i = 0; i < 3; i++) {
            game.endTurn();
            stub.setIndex(testIndex);
            int healthBeforePower = game.getCardInField(Player.PEDDERSEN, i).getHealth();
            game.usePower(Player.FINDUS);
            Card cardAfterPower = game.getCardInField(Player.PEDDERSEN, i);
            if (cardAfterPower != null) {
                assertThat(cardAfterPower.getHealth(), is(healthBeforePower - 2));
                testIndex++;
            }

            game.endTurn();
        }
    }
}
