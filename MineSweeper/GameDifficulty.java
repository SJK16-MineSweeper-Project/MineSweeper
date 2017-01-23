/**
 * Created by Maxie on 2017-01-17.
 */

/**
 * enums for setting a value to Game difficulty.
 * Used to determine size of board.
 */
public enum GameDifficulty {
    VERY_EASY(5, "Very Easy"), EASY(10, "Easy"), NORMAL(15, "Normal"), HARD(20, "Hard"), VERY_HARD(25, "Very Hard");

    private final int mines;
    private final String message;

    GameDifficulty(int mines, String message) {
        this.mines = mines;
        this.message = message;
    }

    public int getMines() {
        return mines;
    }

    public String getMessage() {
        return message;
    }
}
