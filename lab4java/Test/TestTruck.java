import org.junit.*;

import java.awt.*;

import static org.junit.Assert.*;

public class TestTruck {

    @Test
    public void testConstructor() {
        Scania scania = new Scania();
        assertEquals(2, scania.getNrDoors());
        assertEquals(Color.white, scania.getColor());
        assertEquals(90, scania.getEnginePower(), 0.0);
        assertEquals(0, scania.getCurrentSpeed(), 0.0);
        assertEquals(0,scania.getPos()[0],0.0);
        assertEquals(0,scania.getPos()[1],0.0);
        assertEquals(0 ,scania.getDirection()[0]);
        assertEquals(1 ,scania.getDirection()[1]);
        assertFalse(scania.getTruckBedStatus());
        assertEquals(0 ,scania.getTruckBedAngle());
    }
    @Test
    public void testBedDownFalse() {
        Scania scania = new Scania();
        scania.tiltBed(50);
        assertTrue(scania.getTruckBedStatus());
        scania.tiltBed(-50);
        assertFalse(scania.getTruckBedStatus());
    }

    @Test
    public void testMoveWithBedUp () {
        Scania scania = new Scania();
        int degrees = 30;
        scania.tiltBed(degrees);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            scania.move();
        });
        assertEquals("Du kan inte röra dig när flaket är uppe", exception.getMessage());
    }

    @Test
    public void testTiltAboveMaxTiltLimit (){
    Scania scania = new Scania();
    int degrees = 40;
    scania.tiltBed(degrees);
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
        scania.tiltBed(degrees);
    });
    int maxTiltPossible = scania.getMaxTilt() - degrees;
    int minTiltPossible = scania.getTruckBedAngle();
    String expectedMessage = "vinkeln får inte höjas mer än " + maxTiltPossible + " eller sänkas med mer än " + minTiltPossible;
    assertEquals(expectedMessage, exception.getMessage());
    }
}
