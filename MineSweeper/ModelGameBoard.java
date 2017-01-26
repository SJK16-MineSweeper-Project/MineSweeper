import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private String difficulty;
    private Player player;

    public ModelGameBoard(ViewMineSweeper viewSweeper, int i, int j, int mines) {
        this.viewSweeper = viewSweeper;
        this.rows = i;
        this.columns = j;
        this.nrOfMines = mines;
        this.cells = new int[rows][columns];
        this.mines = new int[rows][columns];
        addTimer();
        createPlayer();
    }

    public Player createPlayer() {
        player = new Player();
        player.setPlayerName();

        return player;
    }

    public void setPlayerScore(Player player) {
        player.setLevel(difficulty);
        player.setTime(getTimePlayed());
    }

    public void addPlayerScore(Player player) {

        Singleton newInstance = Singleton.getInstance();
        System.out.println("Instance ID: " + System.identityHashCode(newInstance));

        String[] playerInfo = {player.getName(), player.getLevel(), String.valueOf(player.getTime())};

        newInstance.setScoreList(playerInfo);

        ArrayList scoreList = newInstance.getScoreList();

        for(int i = 0; i < scoreList.size(); i++) {
            String[] playerScore = (String[]) scoreList.get(i);
            viewSweeper.createScoreBoardLabel();
            viewSweeper.setScoreBoardLabel(i, playerScore[0], playerScore[1], playerScore[2]);
        }
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
        if (cells[i][j] != CellValue.EMPTY.getValue())
            message = "Clicked cell, it was a " + cells[i][j];
        else
            message = "Clicked cell, it was empty";
    }

    /**
     * Calculate mine-adjacent-value for cell.
     *
     * @param i number of rows
     * @param j number of columns
     */
    public int cellValue(int i, int j) {
        /*
        totalCells - total value for max 8 surrounding cells.
        cell - value of surrounding cell.
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
                if (i == 0 || j == cells[i].length - 1) {
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
                if (j == cells[i].length - 1) {
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
                if (i == cells.length - 1 || j == cells[i].length - 1) {
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
        System.out.println("Length i: " + cells.length);
        for (int i = 0; i < cells.length; i++) {
            System.out.println("Length j: " + cells[i].length);
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
            System.out.println("Empty cell: " + i + ", " + j + ". Value " + cells[i][j]);
        } else {
            cells[i][j] = c / CellValue.MINE.getValue();
            cellValue = c / CellValue.MINE.getValue();
            System.out.println("Mine-adjacent cell: " + i + ", " + j + ". Value " + cells[i][j]);
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
        if (cells[i][j] != CellValue.MAYBE_MINE.getValue()) {
            if (openedCells == 0) {
                startTimer();
                tStart = System.nanoTime();
                System.out.println("Starting elapsed time: " + tStart);
            }
            if (cells[i][j] == CellValue.MINE.getValue()) {
                viewSweeper.getCells()[i][j].setText("Bomb");

                for (int e = 0; e < viewSweeper.getCells().length; ++e) {
                    for (int c = 0; c < viewSweeper.getCells()[e].length; ++c) {
                        convertCellValuesToString(e, c);
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
        if(cells[i][j] == CellValue.MINE.getValue()) {
            viewSweeper.getCells()[i][j].setText(String.valueOf("Mine"));
        } else if (cells[i][j] == CellValue.OPEN.getValue() || cells[i][j] == CellValue.EMPTY.getValue()) {
            viewSweeper.getCells()[i][j].setText(String.valueOf(""));
        } else if (cells[i][j] == CellValue.MAYBE_MINE.getValue()) {
            toggleMarkMine(i, j);
            viewSweeper.getCells()[i][j].setText(String.valueOf(cells[i][j]));
        } else {
            viewSweeper.getCells()[i][j].setText(String.valueOf(cells[i][j]));
        }
    }

    public void toggleCellVisibility(int i, int j, boolean value) {
        viewSweeper.getCells()[i][j].setEnabled(value);
        viewSweeper.getCells()[i][j].setBackground(new Color(99, 99, 99));
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
        System.out.println("Cells opened " + openedCells);
        if (openedCells == rows * columns - nrOfMines && isGoing == false) {
            System.out.println("All cells opened. Game end.");
            viewSweeper.setGameStatus("Game ended succefully");
            message = "Game ended succefully";
            stopTimer();
            return false;
        }
        if (isGoing == false) {
            viewSweeper.setGameStatus("Game over");
            message = "You clicked on a mine!";
            stopTimer();
            setPlayerScore(player);
            addPlayerScore(player);
            return false;
        } else {
            System.out.println("Still cells to open.");
            return true;
        }
    }

    public void toggleMarkMine(int i, int j) {
        if (cells[i][j] == CellValue.MAYBE_MINE.getValue()) {
            cells[i][j] = mines[i][j];
            mines[i][j] = 0;
            viewSweeper.getCells()[i][j].setText("");
            message = "removed flag from";
            removeFlag();
        } else {
            if (flags > 0) {
                mines[i][j] = cells[i][j];
                cells[i][j] = CellValue.MAYBE_MINE.getValue();
                viewSweeper.getCells()[i][j].setText("?");
                viewSweeper.getCells()[i][j].setForeground(new Color(255, 0, 0));
                message = "Set flag";
                addFlag();
            }
        }
        System.out.println("Flags: " + flags);
    }

    public void addTimer() {
        ActionListener timerPerformer = evt -> viewSweeper.setTimerLabel(timePlayed());

        int delay = 1000;

        timer = new Timer(delay, timerPerformer);
        getTimer().setInitialDelay(delay);

    }

    public long timePlayed() {
        long tCurrent = System.nanoTime();
        tRes = (tCurrent - tStart) / 1000000000L;
        return tRes;
    }

    public long getTimePlayed() {
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
