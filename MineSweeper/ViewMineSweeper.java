import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Maxie on 2017-01-17.
 */
public class ViewMineSweeper extends JFrame {

    private JPanel mainPanel;
    private JPanel panel;
    private JPanel topPanel;
    private JPanel messageBoard;

    private JFrame frame;

    private JLabel timerLabel;
    private JLabel difficultyLabel;
    private JLabel flagsLabel;
    private JLabel gameStatus;
    private JLabel bombsOnBoard;
    private JLabel messages;

    private JMenuBar menuBar;
    private JMenuItem exitOption;
    private JMenuItem newGameOption;

    private JButton[][] cells;

    public ViewMineSweeper(int rows, int columns) {
        this.cells = new JButton[rows][columns];
        createMenu();
        createWindow(rows, columns);
        createCells();
    }

    private void createWindow(int rows, int columns) {

        //create main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        //create top panel
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setOpaque(true);
        topPanel.setBackground(Color.DARK_GRAY);

        //create game board
        panel = new JPanel();
        panel.setLayout(new GridLayout(rows, columns));
        panel.setOpaque(true);
        panel.setBackground(Color.DARK_GRAY);

        //create message board
        messageBoard = new JPanel();
        messageBoard.setLayout(new BorderLayout());
        messageBoard.setOpaque(true);
        messageBoard.setBackground(Color.DARK_GRAY);

        //add panels to main panel
        mainPanel.add(topPanel, BorderLayout.PAGE_START);
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.add(messageBoard, BorderLayout.PAGE_END);

        //window settings
        frame = new JFrame("Minesweeper");
        frame.add(mainPanel);
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

        //create bomb label
        bombsOnBoard = new JLabel();
        bombsOnBoard.setForeground(Color.WHITE);
        bombsOnBoard.setHorizontalAlignment(JLabel.LEFT);
        messageBoard.add(bombsOnBoard, BorderLayout.WEST);

        //create messages
        messages = new JLabel();
        messages.setForeground(Color.WHITE);
        messages.setHorizontalAlignment(JLabel.CENTER);
        messageBoard.add(messages, BorderLayout.CENTER);

        //create gameStatus
        gameStatus = new JLabel();
        gameStatus.setForeground(Color.WHITE);
        gameStatus.setHorizontalAlignment(JLabel.RIGHT);
        messageBoard.add(gameStatus, BorderLayout.EAST);

        //create flags
        flagsLabel = new JLabel();
        flagsLabel.setText("Flags remaining : 10"); //default
        flagsLabel.setForeground(Color.WHITE);
        flagsLabel.setHorizontalAlignment(JLabel.LEFT);
        topPanel.add(flagsLabel, BorderLayout.WEST);

        //create timer
        timerLabel = new JLabel();
        timerLabel.setText("Time: 0"); //default-value
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(timerLabel, BorderLayout.CENTER);

        //create difficulty label
        difficultyLabel = new JLabel();
        difficultyLabel.setForeground(Color.WHITE);
        difficultyLabel.setHorizontalAlignment(JLabel.RIGHT);
        topPanel.add(difficultyLabel, BorderLayout.EAST);

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

        setCells(cells);
        for (int i = 0; i < getCells().length; i++) {
            for (int j = 0; j < getCells()[i].length; j++) {
                getCells()[i][j] = new JButton();
                getCells()[i][j].setOpaque(true);
                getCells()[i][j].setContentAreaFilled(true);
                getCells()[i][j].setBorderPainted(true);
                getCells()[i][j].setPreferredSize(new Dimension(40, 40));
                getCells()[i][j].setFont(new Font("Tahoma", Font.PLAIN, 20));
                getCells()[i][j].setBackground(new Color(117, 114, 115));
                getCells()[i][j].getModel().setEnabled(true);
                panel.add(getCells()[i][j]);
            }
        }

    }

    public void setGameStatus(String message) {
        gameStatus.setText(message);
    }

    public void setBombs(int bombs) {
        String message = Integer.toString(bombs);
        bombsOnBoard.setText("Bombs on field: " + message);
    }

    public void setDifficultyLabel(String message) {
        difficultyLabel.setText(message);
    }


    public void setTimerLabel(long timePlayed) {
        timerLabel.setText("Time: " + timePlayed);
    }

    public void setFlagsLabel(int flags) {
        flagsLabel.setText("Flags remaining: " + flags);
    }

    public JButton[][] getCells() {
        return cells;
    }

    public JMenuItem getExitOption() {
        return this.exitOption;
    }

    public void setCells(JButton[][] cells) {
        this.cells = cells;
    }

    public JLabel getMessages() {
        return messages;
    }
}
