package au.com.petras.game.exception;

public class GameCmdException extends Throwable {
    public GameCmdException(RuntimeException e, String[] rawCmd) {
        super("Invalid Game Command: " + rawCmd.toString(), e);
    }
}
