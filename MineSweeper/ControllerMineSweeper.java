import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

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

    public ControllerMineSweeper() {
        this.viewSweeper = new ViewMineSweeper(8, 8);
        this.modelBoard = new ModelGameBoard(viewSweeper, 8, 8); //i for rows, j for columns
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
        for (int i = 0; i < viewSweeper.getCells().length; i++) {
            for (int j = 0; j < viewSweeper.getCells()[i].length; j++) {
                viewSweeper.getCells()[i][j].addMouseListener(mouseListener);
            }
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
                            modelBoard.move(i, j);
                            viewSweeper.getMessages().setText(modelBoard.getMessage());
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
