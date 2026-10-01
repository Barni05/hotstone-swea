package hotstone.standard.utils;
import java.util.Random;
public class RandomIndexSelector implements IndexSelector {
    @Override
    public int selectIndex(int bound){
        Random random = new Random();
        return random.nextInt(bound);
    }
}
