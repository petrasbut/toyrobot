package au.com.petras;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Robot {
    Position pos;
    Direction direction;
    List<Predicate<Position>> predicates = new ArrayList<>();
    static Logger LOGGER;

    static {
        LOGGER = LoggerFactory.getLogger(Robot.class);
    }

    public Robot(Position pos, Direction direction, List<Predicate<Position>> robotPredicates) {
        this.pos = pos;
        this.direction = direction;
        this.predicates = robotPredicates;
    }

    public void turnRight() {
        direction = Direction.fromValue((direction.index + 1) % 4);
    }

    public void turnLeft() {
        int directionIndex = ((direction.index - 1) % 4);
        if (directionIndex < 0) {
            directionIndex += 4;
        }
        direction = Direction.fromValue(directionIndex);
    }

    public Direction getDirection() {
        return direction;
    }

    public void addPos(int x, int y) {
        int newcol = this.pos.col + x;
        int newrow = this.pos.row + y;
        Position newPos = new Position(newrow, newcol);

        for (Predicate<Position> predicate : predicates) {
            boolean invalidPosition = predicate.test(newPos);
            if (invalidPosition){
                LOGGER.info("Robot predicate violation. No move");
                return;
            }
        }

        this.pos.col += x;
        this.pos.row += y;
    }

    public void move() {
        switch (direction) {
            case NORTH:
                addPos(0, 1);
                break;
            case EAST:
                addPos(1, 0);
                break;
            case SOUTH:
                addPos(0, -1);
                break;
            case WEST:
                addPos(-1, 0);
                break;
        }
    }

    public void report() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Robot{" +
                "position=" + pos +
                ", direction=" + direction +
                '}';
    }

    public int getCol() {
        return this.pos.col;
    }

    public int getRow() {
        return this.pos.row;
    }
}
