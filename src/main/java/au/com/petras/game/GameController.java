package au.com.petras.game;

import au.com.petras.game.exception.GameCmdException;
import au.com.petras.game.parser.GameCmdParser;
import au.com.petras.robot.Direction;
import au.com.petras.robot.Position;
import au.com.petras.robot.Robot;
import au.com.petras.robot.RobotInterface;
import au.com.petras.tableTop.TableTop;
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

        GameCmd cmd = GameCmdParser.parseCommand(inputString);

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


}
