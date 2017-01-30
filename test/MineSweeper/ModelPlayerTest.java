package MineSweeper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Maxie on 2017-01-26.
 */
class ModelPlayerTest {
    ModelPlayer player;

    @BeforeEach
    void setUp() {
        this.player = new ModelPlayer();
        player.setName("test");
        player.setCompleted("Done");
        player.setLevel("Easy");
        player.setTime((long) 1.0);

    }

    @AfterEach
    void tearDown() {
        player.setName(null);
        player.setCompleted(null);
        player.setLevel(null);
        player.setTime(0);
    }

    @Test
    String getName() {
        return player.getName();
    }


    @Test
    String getLevel() {
        return player.getLevel();
    }


    @Test
    long getTime() {
        return player.getTime();
    }

    @Test
    String getCompleted() {
        return player.getCompleted();
    }

    @Test
    void assertData() {
        String name = getName();
        String completed = getCompleted();
        String level = getLevel();
        long time = getTime();

        String expectedName = "test";
        String expectedCompleted = "Done";
        String expectedLevel = "Easy";
        long expectedTime = (long) 1.0;

        assertEquals(expectedName, name);
        assertEquals(expectedCompleted, completed);
        assertEquals(expectedLevel, level);
        assertEquals(expectedTime, time);
    }

}