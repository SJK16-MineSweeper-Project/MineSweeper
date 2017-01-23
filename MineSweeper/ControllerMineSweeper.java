import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;

/**
 * Created by Maxie on 2017-01-17.
 */
public class ControllerMineSweeper {

    private ModelGameBoard modelBoard;
    private ModelMineSweeper modelSweeper;
    private ViewMineSweeper viewSweeper;

    private CellListener cellListener;
    private ExitListener exitListener;
    private RightClickListener mouseListener;

    private GameDifficulty gameDifficulty;

    public ControllerMineSweeper() {
        this.viewSweeper = new ViewMineSweeper();
        this.modelBoard = new ModelGameBoard(viewSweeper, 2);
        this.modelSweeper = new ModelMineSweeper();

        this.cellListener = new CellListener(viewSweeper, modelBoard);
        this.mouseListener = new RightClickListener(viewSweeper, modelBoard);
        this.exitListener = new ExitListener();

        viewSweeper.getExitOption().addActionListener(exitListener);
        viewSweeper.setGameStatus(modelBoard.getStatus(0, 0));

        // Add listener to all cells
        for (int i = 0; i < viewSweeper.getCells().length; i++) {
            for (int j = 0; j < viewSweeper.getCells()[i].length; j++) {
                viewSweeper.getCells()[i][j].addActionListener(cellListener);
            }
        }

        // Add right-clicked-listener to all cells
        for (int i = 0; i < viewSweeper.cells.length; i++) {
            for (int j = 0; j < viewSweeper.cells[i].length; j++) {
                viewSweeper.cells[i][j].addMouseListener(mouseListener);
            }
        }

        gameDifficulty = GameDifficulty.EASY; //placeholder
        setGameDifficulty();
        modelBoard.placeMines(modelBoard.getNrOfMines());
        modelBoard.setCellValues();
    }

    /**
     * Method used to set the number of mines to be placed.
     * Harder difficulty adds more mines to the field.
     *
     */
    public void setGameDifficulty() {//GameDifficulty gameDifficulty) {
        Object[] possibilities = {
                GameDifficulty.VERY_EASY.getMessage(),
                GameDifficulty.EASY.getMessage(),
                GameDifficulty.NORMAL.getMessage(),
                GameDifficulty.HARD.getMessage(),
                GameDifficulty.VERY_HARD.getMessage()};
        String difficulty = (String)JOptionPane.showInputDialog(null, "Choose difficulty", null,
                JOptionPane.PLAIN_MESSAGE, null, possibilities, GameDifficulty.EASY.getMessage());

        //GameDifficulty difficulty = gameDifficulty;
        switch (difficulty) {
            case "Very Easy":
                modelBoard.setNrOfMines(5);
                System.out.println("set game to very easy");
                break;
            case "Easy":
                modelBoard.setNrOfMines(10);
                System.out.println("set game to easy");
                break;
            case "Normal":
                modelBoard.setNrOfMines(15);
                System.out.println("set game to normal");
                break;
            case "Hard":
                modelBoard.setNrOfMines(20);
                System.out.println("set game to hard");
                break;
            case "Very Hard":
                modelBoard.setNrOfMines(25);
                System.out.println("set game to very hard");
                break;
            default:
                break;
        }
        viewSweeper.setTimerLabel(" | Current difficulty " + difficulty + " | ");
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
                for (int i = 0; i < viewSweeper.cells.length; i++) {
                    for (int j = 0; j < viewSweeper.cells[i].length; j++) {
                        if (e.getSource() == viewSweeper.cells[i][j]) {
                            modelBoard.getCells()[i][j] = CellValue.MAYBEMINE.getValue();
                            viewSweeper.getCells()[i][j].setText("Might be bomb");
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
