import javax.swing.*;

/**
 * Created by bartek on 2017-01-25.
 */
public class Player {

    private String name;
    private String level;
    private long time;

    public Player() {

    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public long getTime() {
        return time;
    }

    public void setPlayerName() {
        String message = "Enter playername";
        do {
            name = JOptionPane.showInputDialog(message);
            message = "<html><b style='color:red'>Enter playername:</b><br>"
                    + "Use a-z";
            if (name == null) {
                int confirmExit = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to exit?", null,
                        JOptionPane.YES_NO_OPTION);
                if (confirmExit == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                else {
                    name = "default";
                }
            }
        } while (name != null && !name.matches("[a-zA-Z]+"));
    }
}
