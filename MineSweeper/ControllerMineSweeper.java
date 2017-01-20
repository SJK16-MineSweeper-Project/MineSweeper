import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Maxie on 2017-01-17.
 */
public class ControllerMineSweeper {

    ModelGameBoard modelBoard;
    ModelMineSweeper modelSweeper;
    ViewMineSweeper viewSweeper;
    CellListener cellListener;

    private ExitListener exit = new ExitListener();

    public ControllerMineSweeper() {
        ViewMineSweeper viewSweeper = new ViewMineSweeper();
        this.viewSweeper = viewSweeper;
        ModelGameBoard modelBoard = new ModelGameBoard(viewSweeper, 2);
        this.modelBoard = modelBoard;
        ModelMineSweeper modelSweeper = new ModelMineSweeper();
        this.modelSweeper = modelSweeper;

        CellListener cellListener = new CellListener(viewSweeper, modelBoard);
        this.cellListener = cellListener;

        viewSweeper.getExitOption().addActionListener(exit);

        // Add listener to all cells
        for (int i = 0; i < viewSweeper.cells.length; i++) {
            for (int j = 0; j < viewSweeper.cells[i].length; j++) {
                viewSweeper.cells[i][j].addActionListener(cellListener);
            }
        }

        modelBoard.setCellValues();
    }

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
    public static class CellListener implements ActionListener {

        ViewMineSweeper viewSweeper;
        ModelGameBoard modelBoard;

        public CellListener(ViewMineSweeper viewSweeper, ModelGameBoard modelBoard) {
            this.viewSweeper = viewSweeper;
            this.modelBoard = modelBoard;
        }

        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < viewSweeper.cells.length; i++) {
                for (int j = 0; j < viewSweeper.cells[i].length; j++) {
                    if (viewSweeper.cells[i][j].getModel().isEnabled()) {
                        if (e.getSource() == viewSweeper.cells[i][j]) {
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
