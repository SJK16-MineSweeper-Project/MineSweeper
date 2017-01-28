package MineSweeper;

import javax.swing.*;

/**
 * Created by bartek on 2017-01-26.
 */
public interface View {

    public void createWindow(int rows, int columns);

    public void createMenu();

    public void createCells();

    public void createLabels();

    public void setGameStatus(String message);

    public JMenuItem getExitOption();

    public JMenuItem getNewGameOption();
}
