package au.com.petras.robot;

public interface RobotInterface {
    void turnRight();

    void turnLeft();

    void setPosition(Position newPos, Direction direction);

    void move();

    String report();
}
