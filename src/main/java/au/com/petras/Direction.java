package au.com.petras;

public enum Direction {
    NORTH(0),
    EAST(1),
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
