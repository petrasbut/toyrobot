package au.com.petras.config;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class RobotGameConfigTest {

    @Test
    public void testDefaultConfig() {
        RobotGameConfig config = new RobotGameConfig(null);
        assertEquals(5, config.columns);
        assertEquals(5, config.rows);
    }

    @Test
    public void testGoodConfig() {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("goodConfig.properties").getFile());
        System.out.println();

        RobotGameConfig config = new RobotGameConfig(file.getAbsolutePath());
        assertEquals(11, config.columns);
        assertEquals(11, config.rows);
    }

    @Test
    public void testBadConfig() {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("badConfig.properties").getFile());
        System.out.println();

        RobotGameConfig config = new RobotGameConfig(file.getAbsolutePath());
        assertEquals(5, config.columns);
        assertEquals(5, config.rows);
    }


}