package hotstone.framework;
public interface HeroPowerStrategy {
    /**
     * @param game the mutable game instance
     * @param who the player who is using the power
     * */
    Status usePower(Game game, Player who);

}
