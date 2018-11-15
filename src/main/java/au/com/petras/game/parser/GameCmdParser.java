package au.com.petras.game.parser;

import au.com.petras.game.GameCmd;
import au.com.petras.game.GameInput;
import au.com.petras.game.exception.GameCmdException;
import au.com.petras.robot.Direction;
import org.apache.commons.lang3.StringUtils;

public class GameCmdParser {

    public static GameCmd parseCommand(String inputString) throws GameCmdException {

        if (StringUtils.isBlank(inputString))
            throw new GameCmdException(inputString);

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

    private static GameCmd parsePlaceCmd(String[] rawCmd) throws GameCmdException {

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
