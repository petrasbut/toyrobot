package au.com.petras;

import au.com.petras.config.RobotGameConfig;
import au.com.petras.game.GameController;
import au.com.petras.game.exception.GameCmdException;
import au.com.petras.robot.Robot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Console;

public class Main {

    static RobotGameConfig config;

    static Logger LOGGER;

    static {
        LOGGER = LoggerFactory.getLogger(Robot.class);
    }

    public static void main(String[] args) {

        Console console = System.console();

        setupGameConfig(args[0]);

        GameController gameController = new GameController(config.rows, config.columns);

        System.out.println("Petras Toy Robot");
        System.out.println("PLACE the robot on the table");
        System.out.println("Enter a command, Valid commands are:");
        System.out.println("\'PLACE X,Y,NORTH|SOUTH|EAST|WEST\', MOVE, LEFT, RIGHT, REPORT or EXIT");

        boolean isExit = true;
        while (isExit) {
            String inputString = console.readLine(": ").toUpperCase();
            if ("EXIT".equals(inputString)) {
                isExit = false;
            } else {
                try {
                    gameController.action(inputString);
                } catch (GameCmdException e) {
                    LOGGER.error("Error while parsing Game Command", e);
                }
            }
        }
    }

    private static void setupGameConfig(String configFilePath) {
        config = new RobotGameConfig(configFilePath);
    }
}
