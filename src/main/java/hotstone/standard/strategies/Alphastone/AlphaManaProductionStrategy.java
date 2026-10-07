package hotstone.standard.strategies.Alphastone;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.strategies.ManaProductionStrategy;

public class AlphaManaProductionStrategy implements ManaProductionStrategy {
    private int INITIAL_MANA = 3;
    @Override
    public int calculateStartRoundMana(MutableGame game, Player who){
        return INITIAL_MANA;
    }
}
