import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Maxie on 2017-01-17.
 */
public class ControllerMineSweeper {

    private ModelGameBoard modelBoard;
    private ViewMineSweeper viewSweeper;

    private int rows;
    private int columns;
    private int mines;
    private static String difficulty;

    private CellListener cellListener;
    private ExitListener exitListener;
    private RightClickListener mouseListener;
    private RestartListener restartListener;

    public ControllerMineSweeper() {
        setGameDifficulty();
        this.viewSweeper = new ViewMineSweeper(rows, columns);
        viewSweeper.setDifficultyLabel("Current difficulty: " + getDifficulty());

        this.modelBoard = new ModelGameBoard(viewSweeper, rows, columns, mines); //i for rows, j for columns
        ControllerPlayer player = new ControllerPlayer(viewSweeper);

        this.cellListener = new CellListener(viewSweeper, modelBoard, player);
        this.mouseListener = new RightClickListener(viewSweeper, modelBoard);
        this.exitListener = new ExitListener();
        this.restartListener = new RestartListener(viewSweeper);

        viewSweeper.getExitOption().addActionListener(exitListener);
        viewSweeper.getNewGameOption().addActionListener(restartListener);
        viewSweeper.setGameStatus(modelBoard.getStatus(0, 0));
        // Add listener to all cells
        for (int i = 0; i < viewSweeper.getCells().length; i++) {
            for (int j = 0; j < viewSweeper.getCells()[i].length; j++) {
                viewSweeper.getCells()[i][j].addActionListener(cellListener);
            }
        }
        // Add right-clicked-listener to all cells
        for (int i = 0; i < viewSweeper.getCells().length; i++) {
            for (int j = 0; j < viewSweeper.getCells()[i].length; j++) {
                viewSweeper.getCells()[i][j].addMouseListener(mouseListener);
            }
        }
        modelBoard.placeMines(modelBoard.getNrOfMines());
        viewSweeper.setBombs(modelBoard.getNrOfMines());
        modelBoard.setFlags(modelBoard.getNrOfMines());
        modelBoard.setCellValues();
    }

    /**
     * Used when custom difficulty is chosen.
     * Prompts the user for rows and columns and sends it to model constructor
     */
    public void setCustomDifficulty() {
        JTextField customRows = new JTextField();
        JTextField customColumns = new JTextField();
        rows = 0;
        columns = 0;
        mines = 0;
        Object[] message = {
                "Rows", customRows,
                "Columns", customColumns,
        };
        do {

            int option = JOptionPane.showConfirmDialog(null, message, "Enter custom settings",
                    JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                rows = Integer.parseInt(customRows.getText());
                columns = Integer.parseInt(customColumns.getText());

            }
        }
        while (rows < 2 && columns < 2);
        mines = rows + columns;

        System.out.println("rows set to " + rows + " and columns set to " + columns + " with " + mines + " mines");
    }

    /**
     * Method used to set the number of mines to be placed.
     * Harder difficulty adds more mines to the field.
     */
    public String setGameDifficulty() {
        Object[] possibilities = {
                GameDifficulty.VERY_EASY.getMessage(),
                GameDifficulty.EASY.getMessage(),
                GameDifficulty.NORMAL.getMessage(),
                GameDifficulty.HARD.getMessage(),
                GameDifficulty.VERY_HARD.getMessage(),
                GameDifficulty.CUSTOM.getMessage()};
        difficulty = (String) JOptionPane.showInputDialog(null, "Choose difficulty", null,
                JOptionPane.PLAIN_MESSAGE, null, possibilities, GameDifficulty.EASY.getMessage());

        switch (getDifficulty()) {
            case "Very Easy":
                mines = GameDifficulty.VERY_EASY.getMines();
                rows = GameDifficulty.VERY_EASY.getRows();
                columns = GameDifficulty.VERY_EASY.getColumns();
                System.out.println("set game to very easy");
                break;
            case "Easy":
                mines = GameDifficulty.EASY.getMines();
                rows = GameDifficulty.EASY.getRows();
                columns = GameDifficulty.EASY.getColumns();
                System.out.println("set game to easy");
                break;
            case "Normal":
                mines = GameDifficulty.NORMAL.getMines();
                rows = GameDifficulty.NORMAL.getRows();
                columns = GameDifficulty.NORMAL.getColumns();
                System.out.println("set game to normal");
                break;
            case "Hard":
                mines = GameDifficulty.HARD.getMines();
                rows = GameDifficulty.HARD.getRows();
                columns = GameDifficulty.HARD.getColumns();
                System.out.println("set game to hard");
                break;
            case "Very Hard":
                mines = GameDifficulty.VERY_HARD.getMines();
                rows = GameDifficulty.VERY_HARD.getRows();
                columns = GameDifficulty.VERY_HARD.getColumns();
                System.out.println("set game to very hard");
                break;
            case "Custom":
                setCustomDifficulty();
                System.out.println("Set game to Custom");
                break;
            default:
                break;
        }
        return getDifficulty();
    }

    public static String getDifficulty() {
        return difficulty;
    }

    /**
     * Class that adds an actionListener used to exit the application
     *
     * @author Maxie
     */
    private static class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int confirmExit = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to exit?", null, JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if (confirmExit == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    /**
     * Listener that creates a new controller and disposes of the old one when new game is selected
     *
     * @author Maxie
     */
    private static class RestartListener implements ActionListener {
        private ViewMineSweeper viewSweeper;

        public RestartListener(ViewMineSweeper viewSweeper) {
            this.viewSweeper = viewSweeper;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int confirmRestart = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to restart?", null, JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if (confirmRestart == JOptionPane.YES_OPTION) {
                new ControllerMineSweeper();
                viewSweeper.closeWindow();
                System.gc(); //garbage collection, free up memory
            }
        }
    }

    /**
     * Class that adds an actionListener to dice
     *
     * @author Bartek, Maxie
     */
    private static class CellListener implements ActionListener {

        private ViewMineSweeper viewSweeper;
        private ModelGameBoard modelBoard;
        private ControllerPlayer player;
        private ControllerMineSweeper controllerSweeper;

        public CellListener(ViewMineSweeper viewSweeper, ModelGameBoard modelBoard, ControllerPlayer player) {
            this.viewSweeper = viewSweeper;
            this.modelBoard = modelBoard;
            this.player = player;
            this.controllerSweeper = controllerSweeper;
        }

        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < viewSweeper.getCells().length; i++) {
                for (int j = 0; j < viewSweeper.getCells()[i].length; j++) {
                    if (viewSweeper.getCells()[i][j].getModel().isEnabled()) {
                        if (e.getSource() == viewSweeper.getCells()[i][j]) {
                            modelBoard.cellClicked(i, j);
                            modelBoard.move(i, j);
                            viewSweeper.getMessages().setText(modelBoard.getMessage());
                        }
                    }
                }
            }
            if (modelBoard.gameStatus() == false) {
                player.setPlayerScore(difficulty, modelBoard.getTimePlayed(), modelBoard.getCompleted());
                player.addPlayerScore();
            }
        }
    }

    private class RightClickListener implements MouseListener {

        /**
         * Action performed after button is right-clicked
         */

        private ViewMineSweeper viewSweeper;
        private ModelGameBoard modelBoard;

        public RightClickListener(ViewMineSweeper viewSweeper, ModelGameBoard modelBoard) {
            this.viewSweeper = viewSweeper;
            this.modelBoard = modelBoard;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isRightMouseButton(e) || e.isControlDown()) {
                System.out.println("Right Worked");
                for (int i = 0; i < viewSweeper.getCells().length; i++) {
                    for (int j = 0; j < viewSweeper.getCells()[i].length; j++) {
                        if (e.getSource() == viewSweeper.getCells()[i][j]) {
                            modelBoard.toggleMarkMine(i, j);
                            viewSweeper.getMessages().setText(modelBoard.getMessage());
                        }
                    }
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

    }


    public static void main(String[] args) {
        new ControllerMineSweeper();
    }
}
