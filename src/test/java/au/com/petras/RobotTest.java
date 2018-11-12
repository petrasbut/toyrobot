package au.com.petras;

import org.junit.Test;

import static au.com.petras.Direction.*;
import static org.junit.Assert.*;

public class RobotTest {

    @Test
    public void turnRight() {
        Robot robot = new Robot(0,0, NORTH);
        assertEquals(NORTH, robot.getDirection());

        robot.turnRight();
        assertEquals(EAST, robot.getDirection());

        robot.turnRight();
        assertEquals(SOUTH, robot.getDirection());

        robot.turnRight();
        assertEquals(WEST, robot.getDirection());

        robot.turnRight();
        assertEquals(NORTH, robot.getDirection());

        robot.turnRight();
        assertEquals(EAST, robot.getDirection());
    }

    @Test
    public void turnLeft() {
        Robot robot = new Robot(0,0, NORTH);
        assertEquals(NORTH, robot.getDirection());

        robot.turnLeft();
        assertEquals(WEST, robot.getDirection());

        robot.turnLeft();
        assertEquals(SOUTH, robot.getDirection());

        robot.turnLeft();
        assertEquals(EAST, robot.getDirection());

        robot.turnLeft();
        assertEquals(NORTH, robot.getDirection());

        robot.turnLeft();
        assertEquals(WEST, robot.getDirection());
    }
}