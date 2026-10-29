package hotstone.framework.strategies;
import hotstone.framework.Game;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;

public interface ManaProductionStrategy {
    /**
    * @param who the owning player
     * @return The amount of mana the given player should have at the start of their turn
    * */
    int calculateStartRoundMana(MutableGame game, Player who);

}
