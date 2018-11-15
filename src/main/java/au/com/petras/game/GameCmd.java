package au.com.petras.game;

import au.com.petras.robot.Direction;
import au.com.petras.robot.Position;

public class GameCmd {
    public GameInput input;
    public Position position;
    public Direction direction;

    public GameCmd(GameInput input, int[] args, Direction direction) {
        this.input = input;

        if (args != null) {
            this.position = new Position(args[0], args[1]);
        }

        if (direction != null) {
            this.direction = direction;
        }
    }
}
