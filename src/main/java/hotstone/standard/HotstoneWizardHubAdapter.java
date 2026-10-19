package hotstone.standard;

import hotstone.framework.MutableGame;
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

    }

    @Override
    public void deltaAttackMinion(int playerIndex, int fieldIndex, int attackDelta) {

    }

    @Override
    public void setTauntCategoryMinion(int playerIndex, int fieldIndex, boolean isTaunt) {

    }

    @Override
    public void deltaHealthHero(int playerIndex, int healthDelta) {

    }

    @Override
    public void drawCard(int playerIndex) {

    }
}
