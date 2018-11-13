package au.com.petras;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static au.com.petras.Direction.*;
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
        Robot robot = new Robot(new Position(0, 0), NORTH, null);
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
        Robot robot = new Robot(new Position(0, 0), NORTH, null);
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
    public void addPos() {

        List<Predicate<Position>> predicates = new ArrayList<>();

        Robot robot = new Robot(new Position(0, 0), NORTH, predicates);
        assertEquals(0, robot.getCol());
        assertEquals(0, robot.getRow());

        robot.addPos(1, 1);
        assertEquals(1, robot.getCol());
        assertEquals(1, robot.getRow());

        robot.addPos(2, 2);
        assertEquals(3, robot.getCol());
        assertEquals(3, robot.getRow());

        robot.addPos(-1, -1);
        assertEquals(2, robot.getCol());
        assertEquals(2, robot.getRow());
    }

    @Test
    public void moveNorth() {

        Robot robot = new Robot(new Position(0, 0), NORTH, predicates);
        assertEquals(0, robot.getCol());
        assertEquals(0, robot.getRow());

        robot.move();
        assertEquals(0, robot.getCol());
        assertEquals(1, robot.getRow());
    }

    @Test
    public void moveSouth() {

        Robot robot = new Robot(new Position(0, 0), SOUTH, predicates);
        assertEquals(0, robot.getCol());
        assertEquals(0, robot.getRow());

        robot.move();
        assertEquals(0, robot.getCol());
        assertEquals(-1, robot.getRow());
    }

    @Test
    public void moveEast() {

        Robot robot = new Robot(new Position(1, 1), EAST, predicates);
        assertEquals(1, robot.getCol());
        assertEquals(1, robot.getRow());

        robot.move();
        assertEquals(1, robot.getCol());
        assertEquals(2, robot.getRow());
    }

    @Test
    public void moveWest() {

        Robot robot = new Robot(new Position(1, 1), WEST, predicates);
        assertEquals(1, robot.getCol());
        assertEquals(1, robot.getRow());

        robot.move();
        assertEquals(0, robot.getCol());
        assertEquals(1, robot.getRow());
    }

    @Test
    public void invalidMoveNorth() {

        Robot robot = new Robot(new Position(5, 5), NORTH, predicates);
        assertEquals(5, robot.getCol());
        assertEquals(5, robot.getRow());

        robot.move();
        assertEquals(5, robot.getCol());
        assertEquals(5, robot.getRow());
    }

    @Test
    public void report() {
        Robot robot = new Robot(new Position(1, 1), WEST, predicates);
        assertEquals(1, robot.getCol());
        assertEquals(1, robot.getRow());
    }
}