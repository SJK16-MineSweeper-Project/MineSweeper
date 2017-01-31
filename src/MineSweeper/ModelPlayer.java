package MineSweeper;

/**
 * Created by bartek on 2017-01-25.
 */

public class ModelPlayer {

    private String name;
    private String level;
    private long time;
    private String completed;

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

    public void setCompleted(String completed) { this.completed = completed; }

    public String getCompleted() { return completed; };
}
