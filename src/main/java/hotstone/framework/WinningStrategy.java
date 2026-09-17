package hotstone.framework;

public interface WinningStrategy {
    /**
     * @param turnNumber - the number of turns that has passed since the gamestart
     * @return returns the winner player, or null if none
     * */
    public Player calculateWinner(int turnNumber);
}
