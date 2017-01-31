package MineSweeper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Maxie on 2017-01-26.
 */
public class CellValueTest {

    @Test
    public void getValue() {
        int empty = CellValue.EMPTY.getValue();
        int maybeMine = CellValue.MAYBE_MINE.getValue();
        int mine = CellValue.MINE.getValue();
        int open = CellValue.OPEN.getValue();

        int expectedEmpty = 0;
        int expectedMaybeMine = 1000;
        int expectedMine = 100;
        int expectedOpen = -1;

        assertEquals(expectedEmpty, empty);
        assertEquals(expectedMaybeMine, maybeMine);
        assertEquals(expectedMine, mine);
        assertEquals(expectedOpen, open);
    }

}