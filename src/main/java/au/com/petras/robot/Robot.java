package au.com.petras.robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Robot implements RobotInterface {
    public static final int MOVE_INC = 1;
    static Logger LOGGER;

    static {
        LOGGER = LoggerFactory.getLogger(Robot.class);
    }

    Position pos;
    Direction direction;
    List<Predicate<Position>> predicates = new ArrayList<>();

    public Robot(List<Predicate<Position>> robotPredicates) {
//        this.pos = pos;
//        this.direction = direction;
        this.predicates = robotPredicates;
    }

    @Override
    public void turnRight() {

        if (IsInPlace()) return;

        direction = Direction.fromValue((direction.index + 1) % 4);
    }

    @Override
    public void turnLeft() {

        if (IsInPlace()) return;

        int directionIndex = ((direction.index - 1) % 4);
        if (directionIndex < 0) {
            directionIndex += 4;
        }

        direction = Direction.fromValue(directionIndex);
    }

    public Direction getDirection() {
        return direction;
    }

    private void addPos(int row, int col) {
        int newRow = this.pos.row + row;
        int newCol = this.pos.col + col;
        Position newPosition = new Position(newRow, newCol);

        if (IsInvalidPosition(newPosition)) {
            return;
        } else {
            setPosition(newPosition, this.direction);
        }
    }

    @Override
    public void setPosition(Position newPos, Direction direction) {

        if (IsInvalidPosition(newPos)) {
            return;
        } else {
            this.pos = newPos;
            this.direction = direction;
        }

    }

    public boolean IsInvalidPosition(Position newPos) {
        for (Predicate<Position> predicate : predicates) {
            boolean invalidPosition = predicate.test(newPos);
            if (invalidPosition) {
                LOGGER.info("Robot predicate violation. No move");
                return true;
            }
        }
        return false;
    }

    @Override
    public void move() {

        if (IsInPlace()) return;

        switch (direction) {
            case NORTH:
                addPos(0, MOVE_INC);
                break;
            case EAST:
                addPos(MOVE_INC, 0);
                break;
            case SOUTH:
                addPos(0, -MOVE_INC);
                break;
            case WEST:
                addPos(-MOVE_INC, 0);
                break;
        }
    }

    @Override
    public String report() {
        if (IsInPlace()) return null;
        return this.toString();
    }

    private boolean IsInPlace() {
        if (pos == null) {
            LOGGER.info("Robot not in place. Command ignored");
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "position=" + pos +
                ", direction=" + direction +
                '}';
    }

    public int getRow() {
        return this.pos.row;
    }

    public int getCol() {
        return this.pos.col;
    }
}
