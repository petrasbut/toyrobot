package au.com.petras;

import java.io.Console;

public class Main {

    public static void main(String[] args) {

        Console console = System.console();

        System.out.println("Petras Toy Robot");
        System.out.println("PLACE the robot on the table");
        System.out.println("Enter a command, Valid commands are:");
        System.out.println("\'PLACE X,Y,NORTH|SOUTH|EAST|WEST\', MOVE, LEFT, RIGHT, REPORT or EXIT");

        boolean isExit = true;
        while (isExit) {
            String inputString = console.readLine(": ");
            if ("EXIT".equals(inputString)) {
                isExit = false;
            } else {
                String outputVal = game.eval(inputString);
                System.out.println(outputVal);
            }
        }
    }

}
