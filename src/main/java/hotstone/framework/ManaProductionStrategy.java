package hotstone.framework;
import hotstone.framework.Game;

public interface ManaProductionStrategy {
    /**
    * @param who the owning player
     * @return The amount of mana the given player should have at the start of their turn
    * */
    public int calculateStartRoundMana(Player who);

    default void setGame(Game game) {
    }
}
