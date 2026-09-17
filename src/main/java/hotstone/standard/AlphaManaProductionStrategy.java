package hotstone.standard;
import hotstone.framework.Player;
import hotstone.framework.ManaProductionStrategy;

public class AlphaManaProductionStrategy implements ManaProductionStrategy {
    private int INITIAL_MANA = 3;
    @Override
    public int calculateStartRoundMana(Player who){
        return INITIAL_MANA;
    }
}
