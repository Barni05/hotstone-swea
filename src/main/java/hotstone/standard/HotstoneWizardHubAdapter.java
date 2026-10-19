package hotstone.standard;

import wizardhub.HotStoneGameTarget;

public class HotstoneWizardHubAdapter implements HotStoneGameTarget {
    @Override
    public int getThePlayerInTurn() {
        return 0;
    }

    @Override
    public int getSizeOfField(int playerIndex) {
        return 0;
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
