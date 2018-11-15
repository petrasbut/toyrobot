package au.com.petras.game.parser;

import au.com.petras.game.GameInput;
import au.com.petras.game.exception.GameCmdException;
import au.com.petras.robot.Direction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameCmdParserTest {

    @Test
    public void parseValidCommand() throws GameCmdException {
        String place = GameInput.PLACE.toString() + " 1,1,NORTH";
        String move = GameInput.MOVE.toString();
        String left = GameInput.LEFT.toString();
        String right = GameInput.RIGHT.toString();
        String report = GameInput.REPORT.toString();

        assertEquals(GameInput.LEFT, GameCmdParser.parseCommand(left).input);
        assertEquals(GameInput.REPORT, GameCmdParser.parseCommand(report).input);
        assertEquals(GameInput.RIGHT, GameCmdParser.parseCommand(right).input);
        assertEquals(GameInput.MOVE, GameCmdParser.parseCommand(move).input);

        assertEquals(GameInput.PLACE, GameCmdParser.parseCommand(place).input);
        assertEquals(Direction.NORTH, GameCmdParser.parseCommand(place).direction);
        assertEquals(1, GameCmdParser.parseCommand(place).position.col);
        assertEquals(1, GameCmdParser.parseCommand(place).position.row);
    }

    @Test(expected = GameCmdException.class)
    public void parseErrPlace() throws GameCmdException {
        String place = GameInput.PLACE.toString() + " 1,1,ERR";
        GameCmdParser.parseCommand(place);
    }

    @Test(expected = GameCmdException.class)
    public void parseErr() throws GameCmdException {
        String place = "ERR";
        GameCmdParser.parseCommand(place);
    }

    @Test(expected = GameCmdException.class)
    public void parseEmpty() throws GameCmdException {
        String place = "";
        GameCmdParser.parseCommand(place);
    }

    @Test(expected = GameCmdException.class)
    public void parseNull() throws GameCmdException {
        String place = null;
        GameCmdParser.parseCommand(place);
    }

}