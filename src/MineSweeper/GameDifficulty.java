package MineSweeper;
/**
 * enums for setting a value to Game difficulty.
 * Used to determine size of board.
 * Values {mines, description, rows, columns}
 *
 * @author Maxie
 */
public enum GameDifficulty {
    VERY_EASY(10, "Very Easy", 5, 5), EASY(16, "Easy", 8, 8), NORMAL(22, "Normal", 11, 11),
    HARD(28, "Hard", 14, 14), VERY_HARD(34, "Very Hard", 17, 17), CUSTOM(0, "Custom", 0, 0);

    private final int mines;
    private final String message;
    private final int rows;
    private final int columns;

    GameDifficulty(int mines, String message, int rows, int columns) {
        this.mines = mines;
        this.message = message;
        this.rows = rows;
        this.columns = columns;
    }

    public int getMines() {
        return mines;
    }

    public String getMessage() {
        return message;
    }

    public int getRows() { return rows;}

    public int getColumns() { return columns; }

}
