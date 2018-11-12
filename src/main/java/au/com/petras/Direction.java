package au.com.petras;

public enum Direction {
    EAST(1),
    NORTH(0),
    SOUTH(2),
    WEST(3);

    int index;

    Direction(int index) {
        this.index = index;
    }

    public static Direction fromValue(int index) {
        for (Direction value : Direction.values()) {
            if (value.index == index)
                return value;

        }
        return null;
    }
}
