package hotstone.standard;

import hotstone.framework.Game;
import hotstone.framework.Card;
import hotstone.framework.ManaProductionStrategy;
import hotstone.framework.Player;
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
        game = new StandardHotStoneGame(strategy, new BetaWinningStrategy(), new AlphaHeroCreationStrategy(), new AlphaHeroPowerStrategy(), new AlphaDeckBuildingStrategy());
    }

    @Test
    void shouldGiveManaEqualToTurnNumberUpToSeven() {
        Player player = game.getPlayerInTurn();

        for (int turn = 1; turn <= 8; turn++) {
            int mana = strategy.calculateStartRoundMana(player);
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