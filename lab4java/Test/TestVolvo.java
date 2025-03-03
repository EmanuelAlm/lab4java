import org.junit.*;
import static org.junit.Assert.*;
import java.awt.*;
public class TestVolvo {

    @Test
    public void testConstructor() {
        Volvo240 volvo = new Volvo240();
        assertEquals(4, volvo.getNrDoors());
        assertEquals(Color.black, volvo.getColor());
        assertEquals(100, volvo.getEnginePower(), 0.0);
        assertEquals(1.25, Volvo240.getTrimFactor(), 0.0);
        assertEquals(0, volvo.getCurrentSpeed(), 0.0);
        assertEquals(0, volvo.getPos()[0], 0.0);
        assertEquals(0, volvo.getPos()[1], 0.0);
        assertEquals(0, volvo.getDirection()[0]);
        assertEquals(1, volvo.getDirection()[1]);
    }

    @Test
    public void testSetColor(){
        Volvo240 volvo = new Volvo240();
        volvo.setColor(Color.blue);
        assertEquals(Color.blue, volvo.getColor());
    }

    @Test
    public void testEngine(){
        Volvo240 volvo = new Volvo240();
        volvo.startEngine();
        assertEquals(0.1, volvo.getCurrentSpeed(), 0.0);
        volvo.stopEngine();
        assertEquals(0.0, volvo.getCurrentSpeed(), 0.0);
    }


    @Test
    public void testGas(){
        Volvo240 volvo = new Volvo240();
        volvo.startEngine();
        double startSpeed = volvo.getCurrentSpeed();
        volvo.gas(0.3);
        assertTrue(volvo.getCurrentSpeed() > startSpeed);
    }

    @Test
    public void testGasException() {
    Volvo240 volvo = new Volvo240();
    //volvo.startEngine();
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
        volvo.gas(1.5);
    });
    assertEquals("Värdet måste ligga i intervallet 0 till 1", exception.getMessage());
    }


    @Test
    public void testBrake (){
        Volvo240 volvo = new Volvo240();
        volvo.startEngine();
        double startSpeed = volvo.getCurrentSpeed();
        volvo.brake(0.1);
        assertTrue(volvo.getCurrentSpeed() < startSpeed);
    }

    @Test
    public void testBrakeException() {
        Volvo240 volvo = new Volvo240();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            volvo.brake(1.5);
        });
        assertEquals("Värdet måste ligga i intervallet 0 till 1", exception.getMessage());
    }

    @Test
    public void testSpeedAmount() {
        Volvo240 volvo = new Volvo240();

        volvo.currentSpeed = volvo.getEnginePower();
        volvo.gas(1.0);

        assertEquals(volvo.getCurrentSpeed(), volvo.getEnginePower(), 0.0);

        volvo.currentSpeed = 0;
        volvo.brake(1.0);

        assertEquals(0, volvo.getCurrentSpeed(), 0.0);
    }

    @Test
    public void testMove () {
        Volvo240 volvo = new Volvo240();
        volvo.startEngine();
        volvo.move();
        assertEquals(0.0, volvo.getPos()[0],0.0);
        assertEquals(0.1, volvo.getPos()[1], 0.0);
    }

    @Test
    public void testTurnLeft(){
        Volvo240 volvo = new Volvo240();
        volvo.turnLeft();
        assertEquals(-1 ,volvo.getDirection()[0]);
        assertEquals(0 ,volvo.getDirection()[1]);
        volvo.turnLeft();
        assertEquals(0 ,volvo.getDirection()[0]);
        assertEquals(-1 ,volvo.getDirection()[1]);
    }

    @Test
    public void testTurnRight(){
        Volvo240 volvo = new Volvo240();
        volvo.turnRight();
        assertEquals(1 ,volvo.getDirection()[0]);
        assertEquals(0 ,volvo.getDirection()[1]);
        volvo.turnRight();
        assertEquals(0 ,volvo.getDirection()[0]);
        assertEquals(-1 ,volvo.getDirection()[1]);
    }

}
