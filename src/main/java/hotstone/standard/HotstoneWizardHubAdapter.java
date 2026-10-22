package hotstone.standard;

import hotstone.framework.MutableGame;
import hotstone.framework.Status;
import wizardhub.HotStoneGameTarget;
import hotstone.framework.Player;
public class HotstoneWizardHubAdapter implements HotStoneGameTarget {
    MutableGame mutableGame;


    public HotstoneWizardHubAdapter(MutableGame game){
        this.mutableGame = game;
    }

    @Override
    public int getThePlayerInTurn() {
       return mutableGame.getPlayerInTurn().ordinal();
    }

    @Override
    public int getSizeOfField(int playerIndex) {
        return mutableGame.getFieldSize(Player.valueOf(playerIndex));
    }

    @Override
    public void deltaHealthOrRemoveMinion(int playerIndex, int fieldIndex, int healthDelta) {
        Status result = mutableGame.deltaHealthOnCard(Player.valueOf(playerIndex), fieldIndex, healthDelta);
        if(result == Status.NO_MORE_HEALTH)
            mutableGame.removeCardFromField(Player.valueOf(playerIndex), fieldIndex);
    }

    @Override
    public void deltaAttackMinion(int playerIndex, int fieldIndex, int attackDelta) {
        mutableGame.deltaAttackOnCard(Player.valueOf(playerIndex), fieldIndex, attackDelta);
    }

    @Override
    public void setTauntCategoryMinion(int playerIndex, int fieldIndex, boolean isTaunt) {

    }

    @Override
    public void deltaHealthHero(int playerIndex, int healthDelta) {
        mutableGame.deltaHealthOnHero(Player.valueOf(playerIndex), healthDelta);
    }

    @Override
    public void drawCard(int playerIndex) {
        mutableGame.drawCard(Player.valueOf(playerIndex));
    }
}
