package hotstone.framework.strategies;

import hotstone.framework.MutableCard;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.Status;
import wizardhub.EffectWizard;

public interface Effect {
    Status execute(MutableGame game);
}
