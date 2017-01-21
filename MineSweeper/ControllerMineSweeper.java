import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Maxie on 2017-01-17.
 */
public class ControllerMineSweeper {

    private ExitListener exit = new ExitListener();

    public ControllerMineSweeper() {
        ViewMineSweeper viewSweeper = new ViewMineSweeper();
        ModelGameBoard modelBoard = new ModelGameBoard(viewSweeper, 2);
        ModelMineSweeper modelSweeper = new ModelMineSweeper();

        CellListener cellListener = new CellListener(viewSweeper, modelBoard);
        RightClickListener mouseListener = new RightClickListener(viewSweeper, modelBoard);

        viewSweeper.getExitOption().addActionListener(exit);

        // Add clicked-listener to all cells
        for (int i = 0; i < viewSweeper.cells.length; i++) {
            for (int j = 0; j < viewSweeper.cells[i].length; j++) {
                viewSweeper.cells[i][j].addActionListener(cellListener);
            }
        }

        // Add right-clicked-listener to all cells
        for (int i = 0; i < viewSweeper.cells.length; i++) {
            for (int j = 0; j < viewSweeper.cells[i].length; j++) {
                viewSweeper.cells[i][j].addMouseListener(mouseListener);
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
        @Override
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

    public class RightClickListener implements MouseListener {

        /**
         * Action performed after button is right-clicked
         */

        ViewMineSweeper viewSweeper;
        ModelGameBoard modelBoard;

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
                            modelBoard.cells[i][j] = 10000;
                            viewSweeper.cells[i][j].setText("Might be bomb");
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
