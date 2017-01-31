package MineSweeper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Maxie on 2017-01-26.
 */
class GameDifficultyTest {
    @Test
    void getMines() {
        int mines = GameDifficulty.EASY.getMines();
        int expected = 16;

        assertEquals(expected, mines);
    }

    @Test
    void getMessage() {
        String message = GameDifficulty.EASY.getMessage();
        String expected = "Easy";

        assertEquals(expected, message);
    }

    @Test
    void getRows() {
        int rows = GameDifficulty.EASY.getRows();
        int expected = 8;

        assertEquals(expected, rows);
    }

    @Test
    void getColumns() {
        int columns = GameDifficulty.EASY.getColumns();
        int expected = 8;

        assertEquals(expected, columns);

    }
}