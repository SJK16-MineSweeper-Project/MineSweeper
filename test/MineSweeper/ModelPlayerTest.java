package MineSweeper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Maxie on 2017-01-26.
 */
public class ModelPlayerTest {
    ModelPlayer player;

    @Before
    public void setUp() {
        this.player = new ModelPlayer();
        player.setName("test");
        player.setCompleted("Done");
        player.setLevel("Easy");
        player.setTime((long) 1.0);

    }

    @After
    public void tearDown() {
        player.setName(null);
        player.setCompleted(null);
        player.setLevel(null);
        player.setTime(0);
    }

    @Test
    public String getName() {
        return player.getName();
    }


    @Test
    public String getLevel() {
        return player.getLevel();
    }


    @Test
    public long getTime() {
        return player.getTime();
    }

    @Test
    public String getCompleted() {
        return player.getCompleted();
    }

    @Test
    public void assertData() {
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