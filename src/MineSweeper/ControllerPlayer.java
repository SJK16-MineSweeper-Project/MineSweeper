package MineSweeper;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Maxie on 2017-01-26.
 */
public class ControllerPlayer {

    private ViewMineSweeper viewSweeper;

    private static ModelPlayer player;

    public ControllerPlayer(ViewMineSweeper viewSweeper) {
        this.player = new ModelPlayer();
        this.viewSweeper = viewSweeper;
        setPlayerName();
    }

    public static void setPlayerScore(String difficulty, long timePlayed, String completed) {
        player.setLevel(difficulty);
        player.setTime(timePlayed);
        player.setCompleted(completed);
    }

    public void addPlayerScore() {

        Singleton newInstance = Singleton.getInstance();
        System.out.println("Instance ID: " + System.identityHashCode(newInstance));

        String[] playerInfo = {player.getName(), player.getLevel(), String.valueOf(player.getTime()), player.getCompleted()};
        System.out.println(Arrays.toString(playerInfo));

        newInstance.setScoreList(playerInfo);

        ArrayList scoreList = newInstance.getScoreList();

        System.out.println(scoreList.toString());

        for (int i = 0; i < scoreList.size(); i++) {
            String[] playerScore = (String[]) scoreList.get(i);
            viewSweeper.createScoreBoardLabel();
            viewSweeper.setScoreBoardLabel(i, playerScore[0], playerScore[1], playerScore[2], playerScore[3]);
        }
    }

    public void setPlayerName() {
        String message = "Enter playername";
        do {
            player.setName(JOptionPane.showInputDialog(message));
            message = "<html><b style='color:red'>Enter playername:</b><br>"
                    + "Use a-z";
            if (player.getName() == null) {
                int confirmExit = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to exit?", null,
                        JOptionPane.YES_NO_OPTION);
                if (confirmExit == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {
                    player.setName("default");
                }
            }
        } while (player.getName() != null && !player.getName().matches("[a-zA-Z]+"));
        System.out.println("Set player name to " + player.getName());
    }

}
