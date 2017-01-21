/**
 * Created by Maxie on 2017-01-17.
 */

/**
 * enums for setting a value to Game difficulty.
 * Used to determine size of board.
 */
public enum GameDifficulty {
    VERY_EASY(1), EASY(2), NORMAL(3), HARD(4), VERY_HARD(5);

    private final int value;
    GameDifficulty(int value) {
        this.value = value;
    }
}
