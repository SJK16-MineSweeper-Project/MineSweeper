// Show values of all cells if game over
// Singelton
// Make room for timer, flags

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Random;

/**
 * Created by Maxie on 2017-01-17.
 */
public class ModelGameBoard implements Game {

    private ViewMineSweeper viewSweeper;
    private Timer timer;
    private Random random = new Random();

    private int[][] cells;
    private int[][] mines;
    private int rows;
    private int columns;
    private int nrOfMines;
    private boolean isGoing = true;
    private int openedCells;
    private long tStart;
    private long tRes;
    private int flags;
    private String message;

    public ModelGameBoard(ViewMineSweeper viewSweeper, int i, int j) {
        this.viewSweeper = viewSweeper;
        this.rows = i; //placeholder
        this.columns = j; //placeholder
        this.cells = new int[rows][columns];
        this.mines = new int[rows][columns];
        addTimer();
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
        viewSweeper.setBombs(placedMines);
    }

    public void cellClicked(int i, int j) {
        System.out.println(cells[i][j]);
        if (cells[i][j] != 0)
            message = "Clicked cell, it was a " + cells[i][j];
        else
            message = "Clicked cell, it was empty";

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
                    cell = CellValue.EMPTY.getValue();
                } else {
                    cell = cells[i - 1][j - 1];
                }
            }
            if (n == 1) {
                if (i == 0) {
                    cell = CellValue.EMPTY.getValue();
                } else {
                    cell = cells[i - 1][j];
                }
            }
            if (n == 2) {
                if (i == 0 || j == cells.length - 1) {
                    cell = CellValue.EMPTY.getValue();
                } else {
                    cell = cells[i - 1][j + 1];
                }
            }
            if (n == 3) {
                if (j == 0) {
                    cell = CellValue.EMPTY.getValue();
                } else {
                    cell = cells[i][j - 1];
                }
            }
            if (n == 4) {
                if (j == cells.length - 1) {
                    cell = CellValue.EMPTY.getValue();
                } else {
                    cell = cells[i][j + 1];
                }
            }
            if (n == 5) {
                if (i == cells.length - 1 || j == 0) {
                    cell = CellValue.EMPTY.getValue();
                } else {
                    cell = cells[i + 1][j - 1];
                }
            }
            if (n == 6) {
                if (i == cells.length - 1) {
                    cell = CellValue.EMPTY.getValue();
                } else {
                    cell = cells[i + 1][j];
                }
            }
            if (n == 7) {
                if (i == cells.length - 1 || j == cells.length - 1) {
                    cell = CellValue.EMPTY.getValue();
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
                if (cells[i][j] != CellValue.MINE.getValue()) {
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
        if (c < CellValue.MINE.getValue()) {
            cells[i][j] = CellValue.EMPTY.getValue();
            cellValue = CellValue.EMPTY.getValue();
            System.out.println(cells[i][j]);
        } else {
            cells[i][j] = c / CellValue.MINE.getValue();
            cellValue = c / CellValue.MINE.getValue();
            System.out.println(cells[i][j]);
        }
        return cellValue;
    }

    public void openNeighbours(int i, int j) {
        for (int n = 0; n < 4; n++) {
            if (n == 0) {
                if (i != 0) {
                    if (cells[i - 1][j] == CellValue.EMPTY.getValue()) {
                        cells[i - 1][j] = CellValue.OPEN.getValue();
                        toggleCellVisibility(i - 1, j, false);
                        ++openedCells;
                        System.out.println("n0: " + openedCells);
                        openNeighbours(i - 1, j);
                    }
                }
            }
            if (n == 1) {
                if (j != 0) {
                    if (cells[i][j - 1] == CellValue.EMPTY.getValue()) {
                        cells[i][j - 1] = CellValue.OPEN.getValue();
                        toggleCellVisibility(i, j - 1, false);
                        ++openedCells;
                        System.out.println("n1: " + openedCells);
                        openNeighbours(i, j - 1);
                    }
                }
            }
            if (n == 2) {
                if (j != cells.length - 1) {
                    if (cells[i][j + 1] == CellValue.EMPTY.getValue()) {
                        cells[i][j + 1] = CellValue.OPEN.getValue();
                        toggleCellVisibility(i, j + 1, false);
                        ++openedCells;
                        System.out.println("n2: " + openedCells);
                        openNeighbours(i, j + 1);
                    }
                }
            }
            if (n == 3) {
                if (i != cells.length - 1) {
                    if (cells[i + 1][j] == CellValue.EMPTY.getValue()) {
                        cells[i + 1][j] = CellValue.OPEN.getValue();
                        toggleCellVisibility(i + 1, j, false);
                        ++openedCells;
                        System.out.println("n3: " + openedCells);
                        openNeighbours(i + 1, j);
                    }
                }
            }
        }
    }

    @Override
    public boolean move(int i, int j) {
        boolean move = false;
        if (cells[i][j] != CellValue.MAYBEMINE.getValue()) {
            if (openedCells == 0) {
                startTimer();
                tStart = System.nanoTime();
                System.out.println("Starting elapsed time: " + tStart);
            }
            if (cells[i][j] == CellValue.MINE.getValue()) {
                viewSweeper.getCells()[i][j].setText("Bomb");

                for (int e = 0; e < viewSweeper.getCells().length; ++e) {
                    for (int c = 0; c < viewSweeper.getCells()[e].length; ++c) {
                        if (cells[e][c] != CellValue.OPEN.getValue()) {
                            convertCellValuesToString(e, c);
                        }
                        toggleCellVisibility(e, c, false);
                    }
                }
                System.out.println("Bomb. Game over!");
                isGoing = false;
                gameStatus();
            } else if (cells[i][j] == CellValue.EMPTY.getValue()) {
                cells[i][j] = CellValue.OPEN.getValue();
                toggleCellVisibility(i, j, false);
                ++openedCells;
                openNeighbours(i, j);
                gameStatus();
            } else {
                toggleCellVisibility(i, j, false);
                convertCellValuesToString(i, j);
                ++openedCells;
                gameStatus();
            }
            move = true;
        }

        return move;
    }

    public void convertCellValuesToString(int i, int j) {
        if (cells[i][j] == CellValue.MINE.getValue()) {
            viewSweeper.getCells()[i][j].setText(String.valueOf("Mine"));
        } else {
            viewSweeper.getCells()[i][j].setText(String.valueOf(cells[i][j]));
        }
    }

    public void toggleCellVisibility(int i, int j, boolean value) {
        viewSweeper.getCells()[i][j].setEnabled(value);
    }

    public void addFlag() {
        --flags;
        viewSweeper.setFlagsLabel(flags);
    }

    public void removeFlag() {
        ++flags;
        viewSweeper.setFlagsLabel(flags);
        ;
    }

    public boolean gameStatus() {
        System.out.println("Cells opened" + openedCells);
        if (openedCells == rows * columns - nrOfMines || isGoing == false) {
            System.out.println("All cells opened. Game end.");
            viewSweeper.setGameStatus("Game over");
            message = "Game Over!";
            stopTimer();
            return false;
        } else {
            System.out.println("Still cells to open.");
            return true;
        }
    }

    public void toggleMarkMine(int i, int j) {
        if (cells[i][j] == CellValue.MAYBEMINE.getValue()) {
            cells[i][j] = mines[i][j];
            mines[i][j] = 0;
            viewSweeper.getCells()[i][j].setText("");
            message = "removed flag from";
            removeFlag();
        } else {
            if (flags > 0) {
                mines[i][j] = cells[i][j];
                cells[i][j] = CellValue.MAYBEMINE.getValue();
                viewSweeper.getCells()[i][j].setText("Mine");
                message = "Set flag";
                addFlag();
            }
        }
        System.out.println("Flags: " + flags);
    }

    public void addTimer() {
        ActionListener timerPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                viewSweeper.setTimerLabel(timePlayed());
            }
        };

        int delay = 1000;

        timer = new Timer(delay, timerPerformer);
        getTimer().setInitialDelay(delay);

    }

    public long timePlayed() {
        long tCurrent = System.nanoTime();
        tRes = (tCurrent - tStart) / 1000000000L;
        return tRes;
    }

    public void stopTimer() {
        getTimer().stop();
    }

    public void startTimer() {
        getTimer().start();
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

    @Override
    public String getStatus(int i, int j) {
        boolean isGoing = gameStatus();
        String gameStatus;
        if (isGoing == true)
            gameStatus = "Game active";
        else
            gameStatus = "Game over";
        return gameStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

}
