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
    public void setupEpsilonStone(){
        stub = new FixedIndexSelectorStub(0);
        game = new StandardHotStoneGame(new EpsilonStoneFactory(stub));
    }

    @Test
    public void peddersenMinionDiesWhenFindusPlaysPower(){
        game.endTurn();
        // Card is UNO (1,1,1)
        game.playCard(Player.PEDDERSEN, game.getCardInHand(Player.PEDDERSEN,0), 0);
        game.endTurn();
        stub.setIndex(0);
        game.usePower(Player.FINDUS);
        assertThat(game.getCardInField(Player.PEDDERSEN, 0), is(nullValue()));
    }
}
