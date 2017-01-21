import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Maxie on 2017-01-17.
 */
public class ControllerMineSweeper {

    private ModelGameBoard modelBoard;
    private ModelMineSweeper modelSweeper;
    private ViewMineSweeper viewSweeper;

    private CellListener cellListener;
    private ExitListener exitListener;

    private GameDifficulty gameDifficulty;

    public ControllerMineSweeper() {
        this.viewSweeper = new ViewMineSweeper();
        this.modelBoard = new ModelGameBoard(viewSweeper, 2);
        this.modelSweeper = new ModelMineSweeper();

        this.cellListener = new CellListener(viewSweeper, modelBoard);
        this.exitListener = new ExitListener();

        viewSweeper.getExitOption().addActionListener(exitListener);

        // Add listener to all cells
        for (int i = 0; i < viewSweeper.getCells().length; i++) {
            for (int j = 0; j < viewSweeper.getCells()[i].length; j++) {
                viewSweeper.getCells()[i][j].addActionListener(cellListener);
            }
        }

        gameDifficulty = GameDifficulty.EASY; //placeholder
        setGameDifficulty(gameDifficulty);
        modelBoard.placeMines(modelBoard.getNrOfMines());
        modelBoard.setCellValues();


    }

    /**
     * Method used to set the number of mines to be placed.
     * Harder difficulty adds more mines to the field.
     *
     * @param gameDifficulty
     */
    public void setGameDifficulty(GameDifficulty gameDifficulty) {
        GameDifficulty difficulty = gameDifficulty;
        switch (difficulty) {
            case VERY_EASY:
                modelBoard.setNrOfMines(5);
                System.out.println("set game to very easy");
                break;
            case EASY:
                modelBoard.setNrOfMines(10);
                System.out.println("set game to easy");
                break;
            case NORMAL:
                modelBoard.setNrOfMines(15);
                System.out.println("set game to normal");
                break;
            case HARD:
                modelBoard.setNrOfMines(20);
                System.out.println("set game to hard");
                break;
            case VERY_HARD:
                modelBoard.setNrOfMines(25);
                System.out.println("set game to very hard");
                break;
            default:
                break;
        }
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
     * Class that adds an actionListener to dice
     *
     * @author Bartek
     */
    private static class CellListener implements ActionListener {

        private ViewMineSweeper viewSweeper;
        private ModelGameBoard modelBoard;

        public CellListener(ViewMineSweeper viewSweeper, ModelGameBoard modelBoard) {
            this.viewSweeper = viewSweeper;
            this.modelBoard = modelBoard;
        }

        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < viewSweeper.getCells().length; i++) {
                for (int j = 0; j < viewSweeper.getCells()[i].length; j++) {
                    if (viewSweeper.getCells()[i][j].getModel().isEnabled()) {
                        if (e.getSource() == viewSweeper.getCells()[i][j]) {
                            modelBoard.cellClicked(i, j);
                            modelBoard.openCell(i, j);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new ControllerMineSweeper();
    }
}
