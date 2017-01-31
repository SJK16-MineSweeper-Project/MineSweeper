package MineSweeper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Maxie on 2017-01-26.
 */
public class GameDifficultyTest {
    @Test
    public void getMines() {
        int mines = GameDifficulty.EASY.getMines();
        int expected = 16;

        assertEquals(expected, mines);
    }

    @Test
    public void getMessage() {
        String message = GameDifficulty.EASY.getMessage();
        String expected = "Easy";

        assertEquals(expected, message);
    }

    @Test
    public void getRows() {
        int rows = GameDifficulty.EASY.getRows();
        int expected = 8;

        assertEquals(expected, rows);
    }

    @Test
    public void getColumns() {
        int columns = GameDifficulty.EASY.getColumns();
        int expected = 8;

        assertEquals(expected, columns);

    }
}