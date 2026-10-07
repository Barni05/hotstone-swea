package hotstone.framework.strategies;

import hotstone.framework.MutableCard;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;

public interface CardEffectStrategy {
    void execute(MutableGame game, Player who, MutableCard source);
}
