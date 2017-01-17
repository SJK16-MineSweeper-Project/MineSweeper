/**
 * Created by Maxie on 2017-01-17.
 */

/**
 * enums for setting a value to Game difficulty.
 * Used to determine size of board.
 */
public enum GameDifficulty {
    VERY_EASY(0), EASY(1), NORMAL(2), HARD(3), VERY_HARD(4);

    private final int value;

    GameDifficulty(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
