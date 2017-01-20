import java.lang.reflect.Array;

/**
 * Created by Maxie on 2017-01-17.
 */
public class ModelGameBoard {

    ViewMineSweeper viewSweeper;

    private Array[][] gameBoard;

    int[][] cells;

    public ModelGameBoard (ViewMineSweeper viewSweeper, int value) {
        this.viewSweeper = viewSweeper;
        cells = new int[8][8];
        placeMines();
    }

    public void placeMines() {
        /**
         * Places mines.
         * @param mines array containing value are mines.
         */
        cells[0][3] = 100;
        cells[1][3] = 100;
        cells[2][0] = 100;
        cells[2][1] = 100;
        cells[2][2] = 100;
        cells[2][3] = 100;
        cells[4][4] = 100;
        cells[5][5] = 100;
        cells[6][6] = 100;
        cells[7][7] = 100;
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
                }
                else {
                    cell = cells[i - 1][j - 1];
                }
            }
            if (n == 1) {
                if(i == 0) {
                    cell = 0;
                }
                else {
                    cell = cells[i-1][j];
                }
            }
            if (n == 2) {
                if(i == 0 || j == cells.length - 1) {
                    cell = 0;
                }
                else {
                    cell = cells[i-1][j+1];
                }
            }
            if (n == 3) {
                if(j == 0) {
                    cell = 0;
                }
                else {
                    cell = cells[i][j-1];
                }
            }
            if (n == 4) {
                if(j == cells.length - 1) {
                    cell = 0;
                }
                else {
                    cell = cells[i][j+1];
                }
            }
            if (n == 5) {
                if(i == cells.length - 1 || j == 0) {
                    cell = 0;
                }
                else {
                    cell = cells[i+1][j-1];
                }
            }
            if (n == 6) {
                if(i == cells.length - 1) {
                    cell = 0;
                }
                else {
                    cell = cells[i+1][j];
                }
            }
            if (n == 7) {
                if(i == cells.length - 1 || j == cells.length - 1) {
                    cell = 0;
                }
                else {
                    cell = cells[i+1][j+1];
                }
            }
            totalCells += cell;
        }
        return totalCells;
    }

    public void setCellValues() {
        for(int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j] == 100) {
                    // Remain value 100 for boms
                } else {
                    // calculate value, 0 for non-adjacent cells and >0 for mine-adjacent
                    int cellValue = 0;
                    cellValue = cellValue(i, j);
                    int setCellValue = 0;
                    setCellValue = setCellValue(cellValue, i, j);
                }
            }
        }
    }

    public int setCellValue(int c, int i, int j) {
        int cellValue;
        if(c < 100) {
            cells[i][j] = 0;
            cellValue = 0;
            System.out.println(cells[i][j]);
        }
        else {
            cells[i][j] = c/100;
            cellValue = c/100;
            System.out.println(cells[i][j]);
        }
        return cellValue;
    }

    public void openNeigbours(int i, int j) {
        for (int n = 0; n < 5; n++) {
            if (n == 0) {
                if(i != 0) {
                    if(cells[i-1][j] == 0) {
                        cells[i-1][j] = 1;
                        viewSweeper.cells[i-1][j].setEnabled(false);
                        openNeigbours(i-1, j);
                    }
                }
            }
            if (n == 2) {
                if(j != 0) {
                    if(cells[i][j-1] == 0) {
                        cells[i][j-1] = 1;
                        viewSweeper.cells[i][j-1].setEnabled(false);
                        openNeigbours(i, j-1);
                    }
                }
            }
            if (n == 3) {
                if(j != cells.length - 1) {
                    if(cells[i][j+1] == 0) {
                        cells[i][j+1] = 1;
                        viewSweeper.cells[i][j+1].setEnabled(false);
                        openNeigbours(i, j+1);
                    }
                }
            }
            if (n == 4) {
                if(i != cells.length - 1) {
                    if(cells[i+1][j] == 0) {
                        cells[i + 1][j] = 1;
                        viewSweeper.cells[i + 1][j].setEnabled(false);
                        openNeigbours(i+1, j);
                    }
                }
            }
        }
    }

    public void openCell(int i, int j) {
        if(cells[i][j] == 0) {
            viewSweeper.cells[i][j].setEnabled(false);
            openNeigbours(i, j);
        }
        else {
            viewSweeper.cells[i][j].setEnabled(false);
            viewSweeper.cells[i][j].setText(String.valueOf(cells[i][j]));
        }
    }
}
