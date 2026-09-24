package hotstone.framework;

public interface WinningStrategy {
    /**
     * @param game - the game object
     * @return returns the winner player, or null if none
     * */
    Player calculateWinner(Game game);

}
