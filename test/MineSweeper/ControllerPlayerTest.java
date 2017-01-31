package MineSweeper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Maxie on 2017-01-26.
 */
public class ControllerPlayerTest {

    private ControllerPlayer controllerPlayer;
    private ViewMineSweeper viewSweeper;

    @Before
    public void setUp() {
        this.controllerPlayer = new ControllerPlayer(viewSweeper);
    }

    @After
    public void tearDown() {
        controllerPlayer = null;
    }

    @Test
    public void setPlayerScore() {


    }

    @Test
    public void addPlayerScore() {

    }

    @Test
    public void setPlayerName() {

    }

    @Test
    public void getPlayer() {

    }
}