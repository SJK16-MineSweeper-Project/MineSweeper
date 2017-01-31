package MineSweeper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
/**
 * Created by Maxie on 2017-01-26.
 */
public class ModelGameBoardTest {

    ModelGameBoard modelBoard;
    ViewMineSweeper viewSweeper;
    int mines = 10;
    int rows = 8;
    int columns = 8;

    @Before
    public void setUp() {
        this.modelBoard = new ModelGameBoard(viewSweeper, rows, columns, mines);
    }

    @After
    public void tearDown() {
        modelBoard = null;
    }

    @Test
    public void placeMines() {
        modelBoard.placeMines(mines);

        int placedMines = modelBoard.getNrOfMines();
        int expected = mines;

        assertEquals(expected, placedMines);
    }

    @Test
    public void getNrOfMines() {
        int mines = modelBoard.getNrOfMines();
        int expected = 10;

        assertEquals(expected, mines);
    }

    @Test
    public void getStatus() {
        boolean status = modelBoard.gameStatus();
        boolean expected = true; //should be true since game hasn't been set to isGoing = false

        assertEquals(expected, status);
    }

    @Test
    public void cellClicked() {
        int cellValue = CellValue.EMPTY.getValue();
        int expected = 0;

        assertEquals(expected, cellValue);
    }

}