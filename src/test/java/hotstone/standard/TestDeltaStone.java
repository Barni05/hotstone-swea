package hotstone.standard;
import hotstone.framework.*;
import hotstone.standard.factories.DeltaStoneFactory;
import hotstone.standard.strategies.Alphastone.AlphaDeckBuildingStrategy;
import hotstone.standard.strategies.Alphastone.AlphaManaProductionStrategy;
import hotstone.standard.strategies.DeltaStone.DeltaDeckBuildingStrategy;
import hotstone.standard.strategies.DeltaStone.DeltaManaProductionStrategy;
import hotstone.standard.strategies.Gammastone.GammaHeroCreationStrategy;
import hotstone.standard.strategies.Gammastone.GammaHeroPowerStrategy;
import hotstone.standard.strategies.Gammastone.GammaWinningStrategy;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThanOrEqualTo;


public class TestDeltaStone {
    private StandardHotStoneGame game;

    @BeforeEach
    public void setupDeltaStone(){
        game = new StandardHotStoneGame(new DeltaStoneFactory());
    }

    // Mana production tests

    @Test
    public void findusInitialManaShouldBeFiveOnStartRound(){
        assertThat(game.getHero(Player.FINDUS).getMana(), is(5));
    }

    @Test
    public void peddersenInitialManaShouldBeFiveOnStartRound(){
        assertThat(game.getHero(Player.PEDDERSEN).getMana(), is(5));
    }

    // Deck

    @Test
    public void deckShouldHave15CardsWhenGameInitialized(){
        assertThat(game.getDeckSize(Player.FINDUS), is(15));
    }

    @Test
    public void eachCardShouldBeTwiceInANewDeck(){
        Stack<MutableCard> deck = game.deckBuildingStrategy.createDeck(Player.FINDUS);

        List<String> cardNames = new ArrayList<>();
        for (MutableCard card : deck) {
            cardNames.add(card.getName());
        }

        for (String name : cardNames) {
            assertThat(Collections.frequency(cardNames, name), is(2));
        }
    }

    @Test
    public void firstCardManaShouldBe1WhenNewDeck(){
        Stack<MutableCard> deck = game.deckBuildingStrategy.createDeck(Player.FINDUS);
        assertThat(deck.pop().getManaCost(), is(1));
    }

    @Test
    public void secondCardManaShouldBe2orLessWhenNewDeck(){
        Stack<MutableCard> deck = game.deckBuildingStrategy.createDeck(Player.FINDUS);
        deck.pop();
        assertThat(deck.pop().getManaCost(), lessThanOrEqualTo(2));
    }

    @Test
    public void thirdCardManaShouldBe4orLessWhenNewDeck(){
        Stack<MutableCard> deck = game.deckBuildingStrategy.createDeck(Player.FINDUS);
        deck.pop();
        deck.pop();
        assertThat(deck.pop().getManaCost(), lessThanOrEqualTo(4));
    }
}