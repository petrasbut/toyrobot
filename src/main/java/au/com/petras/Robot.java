package au.com.petras;

import static au.com.petras.Direction.*;

public class Robot {
    int col;
    int row;
    int directionIndex;

    private Direction[] cycle = {NORTH, EAST, SOUTH, WEST};

    public Robot(int col, int row, Direction direction) {
        this.col = col;
        this.row = row;
        this.directionIndex = direction.index;
    }

    public void turnRight() {
        directionIndex = ((directionIndex + 1) % 4);
    }

    public void turnLeft() {
        directionIndex = ((directionIndex - 1) % 4);
        if (directionIndex < 0) {
            directionIndex += 4;
        }
        System.out.println(directionIndex);
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Direction getDirection() {
        return Direction.fromValue(directionIndex);
    }
}
