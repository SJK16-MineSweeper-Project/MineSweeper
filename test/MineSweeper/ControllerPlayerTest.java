package MineSweeper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Maxie on 2017-01-26.
 */
class ControllerPlayerTest {

    private ControllerPlayer controllerPlayer;
    private ViewMineSweeper viewSweeper;

    @BeforeEach
    void setUp() {
        this.controllerPlayer = new ControllerPlayer(viewSweeper);
    }

    @AfterEach
    void tearDown() {
        controllerPlayer = null;
    }

    @Test
    void setPlayerScore() {


    }

    @Test
    void addPlayerScore() {

    }

    @Test
    void setPlayerName() {

    }

    @Test
    void getPlayer() {

    }

}