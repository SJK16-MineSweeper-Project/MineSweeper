import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Maxie on 2017-01-17.
 */
public class ViewMineSweeper extends JFrame {

    private JPanel panel;

    private JMenuBar menuBar;
    private JMenuItem exitOption;
    private JMenuItem newGameOption;

    JButton cells[][];

    public ViewMineSweeper() {
        createMenu();
        createWindow();
        createCells();
    }

    private void createWindow() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(8,8));
        panel.setOpaque(true);
        panel.setBackground(Color.DARK_GRAY);

        //window settings
        JFrame frame = new JFrame("Minesweeper");
        frame.add(panel);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setLocationRelativeTo(null); //center window
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.setJMenuBar(menuBar);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    private void createMenu() {
        menuBar = new JMenuBar();

        JMenu systemMenu = new JMenu("System"); //create menubar titled system
        menuBar.add(systemMenu);
        setJMenuBar(menuBar);

        newGameOption = new JMenuItem("New game");
        newGameOption.setToolTipText("Starts a new game");
        systemMenu.add(newGameOption);

        exitOption = new JMenuItem("Exit");
        exitOption.setToolTipText("Exit application");
        systemMenu.add(exitOption);
    }

    private void createCells() {

        cells = new JButton[8][8];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new JButton();
                cells[i][j].setContentAreaFilled(false);
                cells[i][j].setBorderPainted(true);
                cells[i][j].setPreferredSize(new Dimension(40, 40));
                cells[i][j].setFont(new Font("Tahoma", Font.PLAIN, 20));
                cells[i][j].setBackground(new Color(0, 204, 0));
                cells[i][j].getModel().setEnabled(true);
                panel.add(cells[i][j]);
            }
        }

    }

    public JButton[][] getCells() {
        return cells;
    }

    public JMenuItem getExitOption() {
        return this.exitOption;
    }


}
