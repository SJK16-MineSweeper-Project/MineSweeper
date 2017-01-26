package MineSweeper;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Maxie on 2017-01-26.
 */
class ModelGameBoardTest {

    ModelGameBoard modelBoard;
    ViewMineSweeper viewSweeper;
    int mines = 10;
    int rows = 8;
    int columns = 8;

    @BeforeEach
    void setUp() {
        this.modelBoard = new ModelGameBoard(viewSweeper, rows, columns, mines);
    }

    @AfterEach
    void tearDown() {
        modelBoard = null;
    }

    @Test
    void placeMines() {
        modelBoard.placeMines(mines);

        int placedMines = modelBoard.getNrOfMines();
        int expected = mines;

        assertEquals(expected, placedMines);
    }

    @Test
    void getNrOfMines() {
        int mines = modelBoard.getNrOfMines();
        int expected = 10;

        assertEquals(expected, mines);
    }

    @Test
    void getStatus() {
        boolean status = modelBoard.gameStatus();
        boolean expected = true; //should be true since game hasn't been set to isGoing = false

        assertEquals(expected, status);
    }

}