import java.lang.reflect.Array;
import java.util.Random;

/**
 * Created by Maxie on 2017-01-17.
 */
public class ModelGameBoard {

    private ViewMineSweeper viewSweeper;
    private Random random = new Random();

    private Array[][] gameBoard;

    private int[][] cells;
    private int rows;
    private int columns;
    private static int nrOfMines;

    public ModelGameBoard(ViewMineSweeper viewSweeper, int value) {
        this.viewSweeper = viewSweeper;
        rows = 8; //placeholder
        columns = 8; //placeholder
        cells = new int[rows][columns];
    }

    /**
     * Method to place mines. It places a number of mines determined by game difficulty
     *
     * @param nrOfMines number of mines set by setGameDifficulty()
     * @see ControllerMineSweeper
     */
    public void placeMines(int nrOfMines) {
        int mine = CellValue.MINE.getValue();
        int placedMines = 0;
        while (placedMines < nrOfMines) {
            int x = random.nextInt(rows);
            int y = random.nextInt(columns);
            if (cells[x][y] != mine) {
                cells[x][y] = mine;
                placedMines++;
            }
        }
        System.out.println("placed " + nrOfMines + " mines");
        System.out.println("placedMines is now " + placedMines);
    }

    public void cellClicked(int i, int j) {
        System.out.println(cells[i][j]);
    }

    public int cellValue(int i, int j) {
        /**
         * Calculate mine-adjacent-value for cell.
         * @param totalCells total value for max 8 surrounding cells.
         * @param cell value of surrounding cell.
         */
        int totalCells = 0;
        int cell = 0;
        for (int n = 0; n < 8; n++) {
            if (n == 0) {
                if (i == 0 || j == 0) {
                    cell = 0;
                } else {
                    cell = cells[i - 1][j - 1];
                }
            }
            if (n == 1) {
                if (i == 0) {
                    cell = 0;
                } else {
                    cell = cells[i - 1][j];
                }
            }
            if (n == 2) {
                if (i == 0 || j == cells.length - 1) {
                    cell = 0;
                } else {
                    cell = cells[i - 1][j + 1];
                }
            }
            if (n == 3) {
                if (j == 0) {
                    cell = 0;
                } else {
                    cell = cells[i][j - 1];
                }
            }
            if (n == 4) {
                if (j == cells.length - 1) {
                    cell = 0;
                } else {
                    cell = cells[i][j + 1];
                }
            }
            if (n == 5) {
                if (i == cells.length - 1 || j == 0) {
                    cell = 0;
                } else {
                    cell = cells[i + 1][j - 1];
                }
            }
            if (n == 6) {
                if (i == cells.length - 1) {
                    cell = 0;
                } else {
                    cell = cells[i + 1][j];
                }
            }
            if (n == 7) {
                if (i == cells.length - 1 || j == cells.length - 1) {
                    cell = 0;
                } else {
                    cell = cells[i + 1][j + 1];
                }
            }
            totalCells += cell;
        }
        return totalCells;
    }

    public void setCellValues() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j] != 100) {
                    // calculate value, 0 for non-adjacent cells and >0 for mine-adjacent
                    int cellValue = 0;
                    cellValue = cellValue(i, j);
                    setCellValue(cellValue, i, j);
                }
            }
        }
    }

    public int setCellValue(int c, int i, int j) {
        int cellValue;
        if (c < 100) {
            cells[i][j] = 0;
            cellValue = 0;
            System.out.println(cells[i][j]);
        } else {
            cells[i][j] = c / 100;
            cellValue = c / 100;
            System.out.println(cells[i][j]);
        }
        return cellValue;
    }

    public void openNeighbours(int i, int j) {
        for (int n = 0; n < 5; n++) {
            if (n == 0) {
                if (i != 0) {
                    if (cells[i - 1][j] == 0) {
                        cells[i - 1][j] = 1;
                        viewSweeper.getCells()[i - 1][j].setEnabled(false);
                        openNeighbours(i - 1, j);
                    }
                }
            }
            if (n == 2) {
                if (j != 0) {
                    if (cells[i][j - 1] == 0) {
                        cells[i][j - 1] = 1;
                        viewSweeper.getCells()[i][j - 1].setEnabled(false);
                        openNeighbours(i, j - 1);
                    }
                }
            }
            if (n == 3) {
                if (j != cells.length - 1) {
                    if (cells[i][j + 1] == 0) {
                        cells[i][j + 1] = 1;
                        viewSweeper.getCells()[i][j + 1].setEnabled(false);
                        openNeighbours(i, j + 1);
                    }
                }
            }
            if (n == 4) {
                if (i != cells.length - 1) {
                    if (cells[i + 1][j] == 0) {
                        cells[i + 1][j] = 1;
                        viewSweeper.getCells()[i + 1][j].setEnabled(false);
                        openNeighbours(i + 1, j);
                    }
                }
            }
        }
    }

    public void openCell(int i, int j) {
        if(cells[i][j] == 100) {
            viewSweeper.cells[i][j].setText("Bomb");
            viewSweeper.cells[i][j].setEnabled(false);
            System.out.println("Bomb. Game over!");
        }
        else if(cells[i][j] == 0) {
            viewSweeper.cells[i][j].setEnabled(false);
            openNeighbours(i, j);
        }
        else {
            viewSweeper.cells[i][j].setEnabled(false);
            viewSweeper.cells[i][j].setText(String.valueOf(cells[i][j]));
        }
    }

    /**
     * getter for number of mines on the board
     *
     * @return placed mines
     */
    public int getNrOfMines() {
        return nrOfMines;
    }

    /**
     * Setter for placed mines on board
     *
     * @param nrOfMines sets number of mines to be placed
     * @see ControllerMineSweeper
     */
    public void setNrOfMines(int nrOfMines) {
        this.nrOfMines = nrOfMines;
    }
}
