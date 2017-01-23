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

    private JLabel timerLabel; //placeholder, currently shows difficulty
    private JLabel difficultyLabel;
    private JLabel flagsLabel;
    private JLabel gameStatus;
    private JLabel bombsOnBoard;
    private JLabel messages;

    private JMenuBar menuBar;
    private JMenuItem exitOption;
    private JMenuItem newGameOption;

    private JButton[][] cells;

    public ViewMineSweeper() {
        createMenu();
        createWindow();
        createCells();
    }

    private void createWindow() {
        //create main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        //create top panel
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.setOpaque(true);
        topPanel.setBackground(Color.DARK_GRAY);

        //create game board
        panel = new JPanel();
        panel.setLayout(new GridLayout(8,8));
        panel.setOpaque(true);
        panel.setBackground(Color.DARK_GRAY);

        //create message board
        messageBoard = new JPanel();
        messageBoard.setLayout(new FlowLayout());
        messageBoard.setOpaque(true);
        messageBoard.setBackground(Color.DARK_GRAY);

        //add panels to main panel
        mainPanel.add(topPanel, BorderLayout.PAGE_START);
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.add(messageBoard, BorderLayout.PAGE_END);

        //window settings
        JFrame frame = new JFrame("Minesweeper");
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
        topPanel.add(bombsOnBoard, FlowLayout.LEFT);

        //create timer
        difficultyLabel = new JLabel();
        difficultyLabel.setForeground(Color.WHITE);
        topPanel.add(difficultyLabel, FlowLayout.CENTER);

        //create gameStatus
        gameStatus = new JLabel();
        gameStatus.setForeground(Color.WHITE);
        topPanel.add(gameStatus, FlowLayout.RIGHT);

        //create flags
        flagsLabel = new JLabel();
        flagsLabel.setForeground(Color.WHITE);
        topPanel.add(flagsLabel, FlowLayout.RIGHT);

        //create flags
        timerLabel = new JLabel();
        timerLabel.setForeground(Color.WHITE);
        topPanel.add(timerLabel, FlowLayout.RIGHT);

        //create messages
        messages = new JLabel();
        messages.setForeground(Color.WHITE);
        messageBoard.add(messages);
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

        setCells(new JButton[8][8]);
        for (int i = 0; i < getCells().length; i++) {
            for (int j = 0; j < getCells()[i].length; j++) {
                getCells()[i][j] = new JButton();
                getCells()[i][j].setContentAreaFilled(false);
                getCells()[i][j].setBorderPainted(true);
                getCells()[i][j].setPreferredSize(new Dimension(40, 40));
                getCells()[i][j].setFont(new Font("Tahoma", Font.PLAIN, 20));
                getCells()[i][j].setBackground(new Color(0, 204, 0));
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
