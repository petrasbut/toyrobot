package au.com.petras;

import au.com.petras.config.RobotGameConfig;
import au.com.petras.game.GameController;
import au.com.petras.game.exception.GameCmdException;
import au.com.petras.robot.Robot;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {

    static RobotGameConfig config;

    static Logger LOGGER;

    static {
        LOGGER = LoggerFactory.getLogger(Robot.class);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        if (args.length > 0 && StringUtils.isNotBlank(args[0])) {
            setupGameConfig(args[0]);
        } else {
            setupGameConfig(null);
        }

        GameController gameController = new GameController(config.rows, config.columns);

        System.out.println("Petras Toy Robot");
        System.out.println("PLACE the robot on the table");
        System.out.println("Enter a command, Valid commands are:");
        System.out.println("\'PLACE X,Y,NORTH|SOUTH|EAST|WEST\', MOVE, LEFT, RIGHT, REPORT or EXIT");

        boolean isRunning = true;
        while (isRunning) {
            String inputString = scanner.nextLine().toUpperCase();
            if ("EXIT".equals(inputString)) {
                isRunning = false;
            } else {
                try {
                    gameController.action(inputString);
                } catch (GameCmdException e) {
                    LOGGER.error("Error while parsing Game Command", e);
                    System.out.println("Error while parsing your input. Robot says: Goodbye");
                }
            }
        }
        System.out.println("Goodbye...");
    }

    private static void setupGameConfig(String configFilePath) {
        config = new RobotGameConfig(configFilePath);
    }
}
