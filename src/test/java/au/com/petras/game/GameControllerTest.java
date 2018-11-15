package au.com.petras.game;

import au.com.petras.game.exception.GameCmdException;
import au.com.petras.robot.Direction;
import au.com.petras.robot.Position;
import au.com.petras.robot.Robot;
import au.com.petras.tableTop.TableTop;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

    @Mock
    Robot mockRobot;

    @Mock
    Position mockPosition;

    @Mock
    TableTop mockTable;

    @InjectMocks
    GameController game = new GameController(5, 5);

    @Test
    public void turnRight() {
        game.turnRight();
        verify(mockRobot, atLeastOnce()).turnRight();
    }

    @Test
    public void turnLeft() {
        game.turnLeft();
        verify(mockRobot, atLeastOnce()).turnLeft();
    }

    @Test
    public void setPosition() {
        game.setPosition(mockPosition, Direction.NORTH);
        verify(mockRobot, atLeastOnce()).setPosition(mockPosition, Direction.NORTH);
    }

    @Test
    public void move() {
        game.move();
        verify(mockRobot, atLeastOnce()).move();
    }

    @Test
    public void report() {
        game.report();
        verify(mockRobot, atLeastOnce()).report();
    }

    @Test
    public void action() throws GameCmdException {

        game.action("PLACE 1,1,NORTH");
        verify(mockRobot, atLeastOnce()).setPosition(any(), any());

        game.action("MOVE");
        verify(mockRobot, atLeastOnce()).move();

        game.action("LEFT");
        verify(mockRobot, atLeastOnce()).turnLeft();

        game.action("RIGHT");
        verify(mockRobot, atLeastOnce()).turnRight();

        game.action("REPORT");
        verify(mockRobot, atLeastOnce()).report();
    }
}