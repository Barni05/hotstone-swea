package hotstone.standard;

import hotstone.framework.Game;
import hotstone.framework.Card;
import hotstone.framework.strategies.ManaProductionStrategy;
import hotstone.framework.Player;
import hotstone.standard.factories.BetaStoneFactory;
import hotstone.standard.strategies.BetaStone.BetaManaProductionStrategy;
import hotstone.standard.strategies.BetaStone.BetaWinningStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hotstone.standard.strategies.Alphastone.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBetaStone {
    private Game game;
    private ManaProductionStrategy strategy;
    private final int EXPECTED_INITIAL_DECK_SIZE = 4, EXPECTED_INITIAL_HAND_SIZE = 3, MAXIMUM_DRAW = 4,
            MAXIMUM_HEALTH = 21, EXPECTED_MANA = 3;

    @BeforeEach
    public void setUp() {
        strategy = new BetaManaProductionStrategy();
        game = new StandardHotStoneGame(new BetaStoneFactory());
    }

    @Test
    void shouldGiveManaEqualToTurnNumberUpToSeven() {
        Player player = game.getPlayerInTurn();
        StandardHotStoneGame standardGame = (StandardHotStoneGame) game;

        for (int turn = 1; turn <= 8; turn++) {
            int mana = standardGame.calculateStartRoundMana(player);
            int expectedMana = Math.min(turn, 7);
            assertEquals(expectedMana, mana);

            game.endTurn();
            player = game.getPlayerInTurn();
        }

    }

    @Test
    void shouldDeclareAttackingPlayerWinnerWhenOpponentHeroHealthReachesZero() {
        Card lethalCard = new StandardCard(
                "Lethal",
                "Deals lethal damage",
                0,
                MAXIMUM_HEALTH,
                1,
                true,
                Player.FINDUS);

        game.attackHero(Player.FINDUS, lethalCard);

        assertEquals(0, game.getHero(Player.PEDDERSEN).getHealth());
        assertEquals(Player.FINDUS, game.getWinner());
    }
}