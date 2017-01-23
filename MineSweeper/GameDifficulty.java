/**
 * Created by Maxie on 2017-01-17.
 */

/**
 * enums for setting a value to Game difficulty.
 * Used to determine size of board.
 */
public enum GameDifficulty {
    VERY_EASY(1, "Very Easy"), EASY(2, "Easy"), NORMAL(3, "Normal"), HARD(4, "Hard"), VERY_HARD(5, "Very Hard");

    private final int value;
    private final String message;

    GameDifficulty(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
