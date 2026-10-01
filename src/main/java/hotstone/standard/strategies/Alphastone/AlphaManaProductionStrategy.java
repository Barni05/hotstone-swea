package hotstone.standard.strategies.Alphastone;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.strategies.ManaProductionStrategy;

public class AlphaManaProductionStrategy implements ManaProductionStrategy {
    private int INITIAL_MANA = 3;
    @Override
    public int calculateStartRoundMana(Player who, Game game){
        return INITIAL_MANA;
    }
}
