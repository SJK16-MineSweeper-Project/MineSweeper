import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by bartek on 2017-01-25.
 */

public class ModelPlayer {

    private String name;
    private String level;
    private long time;

    public ModelPlayer() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
