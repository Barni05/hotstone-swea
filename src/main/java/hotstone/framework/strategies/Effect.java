package hotstone.framework.strategies;

import hotstone.framework.MutableCard;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.Status;

public interface Effect {
    Status execute(MutableGame game);
}
