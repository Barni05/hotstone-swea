package hotstone.standard.utils;

public class FixedIndexSelectorStub implements IndexSelector{
    private int retIndex;

    public FixedIndexSelectorStub(int initialIndex){
        retIndex = initialIndex;
    }
    public void setIndex(int newIndex){
        retIndex = newIndex;
    }

    @Override
    public int selectIndex(int bound){
        return retIndex;
    }
}
