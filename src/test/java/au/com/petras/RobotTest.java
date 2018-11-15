package au.com.petras;

import au.com.petras.robot.Position;
import au.com.petras.robot.Robot;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static au.com.petras.robot.Direction.*;
import static org.junit.Assert.assertEquals;

public class RobotTest {

    List<Predicate<Position>> predicates = new ArrayList<>();

    @Before
    public void setup() {
        predicates.add((pos) -> pos.col < 0);
        predicates.add((pos) -> pos.row < 0);
        predicates.add((pos) -> pos.col > 5);
        predicates.add((pos) -> pos.row > 5);
    }

    @Test
    public void turnRight() {
        Robot robot = new Robot(predicates);
        robot.setPosition(new Position(0, 0), NORTH);

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
        Robot robot = new Robot(predicates);
        robot.setPosition(new Position(0, 0), NORTH);

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

    @Test
    public void moveNorth() {

        Robot robot = new Robot(predicates);
        robot.setPosition(new Position(0, 0), NORTH);

        assertEquals(0, robot.getRow());
        assertEquals(0, robot.getCol());

        robot.move();
        assertEquals(0, robot.getRow());
        assertEquals(1, robot.getCol());
    }

    @Test
    public void moveSouth() {

        Robot robot = new Robot(predicates);
        robot.setPosition(new Position(1, 1), SOUTH);

        assertEquals(1, robot.getRow());
        assertEquals(1, robot.getCol());

        robot.move();
        assertEquals(1, robot.getRow());
        assertEquals(0, robot.getCol());
    }

    @Test
    public void moveEast() {

        Robot robot = new Robot(predicates);
        robot.setPosition(new Position(1, 1), EAST);

        assertEquals(1, robot.getRow());
        assertEquals(1, robot.getCol());

        robot.move();
        assertEquals(2, robot.getRow());
        assertEquals(1, robot.getCol());
    }

    @Test
    public void moveWest() {

        Robot robot = new Robot(predicates);
        robot.setPosition(new Position(1, 1), WEST);

        assertEquals(1, robot.getRow());
        assertEquals(1, robot.getCol());

        robot.move();
        assertEquals(0, robot.getRow());
        assertEquals(1, robot.getCol());
    }

    @Test
    public void invalidMoveNorth() {

        Robot robot = new Robot(predicates);
        robot.setPosition(new Position(5, 5), NORTH);

        assertEquals(5, robot.getRow());
        assertEquals(5, robot.getCol());

        robot.move();
        assertEquals(5, robot.getRow());
        assertEquals(5, robot.getCol());
    }

    @Test
    public void report() {
        Robot robot = new Robot(predicates);
        robot.setPosition(new Position(1, 1), WEST);

        assertEquals(1, robot.getRow());
        assertEquals(1, robot.getCol());

        assertEquals("Robot{position=Position{row=1, col=1}, direction=WEST}", robot.report());
    }

    @Test
    public void reporNotInPlace() {
        Robot robot = new Robot(predicates);

        assertEquals("Robot not in PLACE", robot.report());
    }
}