package MineSweeper;

/**
 * Created by Maxie on 2017-01-17.
 */
public interface Game {
    public boolean move(int i, int j);

    public String getStatus(int i, int j);

    public String getMessage();
}
