package au.com.petras.game;

import au.com.petras.game.exception.GameCmdException;
import au.com.petras.robot.Direction;
import au.com.petras.robot.Position;
import au.com.petras.robot.Robot;
import au.com.petras.robot.RobotInterface;
import au.com.petras.tableTop.TableTop;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameController implements RobotInterface {

    static Logger LOGGER;

    static {
        LOGGER = LoggerFactory.getLogger(Robot.class);
    }

    TableTop table;
    Robot robot;

    public GameController(int rows, int cols) {
        this.table = new TableTop(rows, cols);
        this.robot = new Robot(table.tablePredicates);
    }

    @Override
    public void turnRight() {
        robot.turnRight();
    }

    @Override
    public void turnLeft() {
        robot.turnLeft();
    }

    @Override
    public void setPosition(Position newPos, Direction direction) {
        robot.setPosition(newPos, direction);
    }

    @Override
    public void move() {
        robot.move();
    }

    @Override
    public String report() {
        return robot.report();
    }

    public void action(String inputString) throws GameCmdException {

        GameCmd cmd = parseCommand(inputString);

        switch (cmd.input) {
            case PLACE:
                robot.setPosition(cmd.position, cmd.direction);
                break;
            case MOVE:
                robot.move();
                break;
            case LEFT:
                robot.turnLeft();
                break;
            case RIGHT:
                robot.turnRight();
                break;
            case REPORT:
                System.out.println(robot.report());
                break;
        }

        return;
    }

    private GameCmd parseCommand(String inputString) throws GameCmdException {

        String[] rawCmd = StringUtils.split(inputString);

        // cmd identification
        GameInput input = null;
        try {
            input = GameInput.valueOf(rawCmd[0]);
        } catch (IllegalArgumentException e) {
            throw new GameCmdException(e, rawCmd);
        }

        if (input.equals(GameInput.PLACE)) {
            return parsePlaceCmd(StringUtils.split(rawCmd[1], ','));
        } else {
            return new GameCmd(input, null, null);
        }
    }

    private GameCmd parsePlaceCmd(String[] rawCmd) throws GameCmdException {

        try {
            int[] xy = new int[2];
            xy[0] = Integer.valueOf(rawCmd[0]);
            xy[1] = Integer.valueOf(rawCmd[1]);
            Direction direction = Direction.valueOf(rawCmd[2].toUpperCase());

            return new GameCmd(GameInput.PLACE, xy, direction);

        } catch (RuntimeException e) {
            throw new GameCmdException(e, rawCmd);
        }
    }
}
