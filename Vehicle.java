import java.awt.*;

public interface Vehicle {
    int getNrDoors();
    double getEnginePower();
    double getCurrentSpeed();
    Color getColor();
    double getWeight();
    void setColor(Color clr);
    void startEngine();
    void stopEngine();
    double[] getPos();
    int[] getDirection();
    void gas(double amount);
    void brake(double amount);
    void setPos(double[] pos);
    void setDirection(int[] direction);
    void move();
    void turnLeft();
    void turnRight();
}
