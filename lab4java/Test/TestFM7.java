import org.junit.*;

import java.awt.*;

import static org.junit.Assert.*;

public class TestFM7 {
    @Test
    public void testConstructor() {
        VolvoFM7 fm7 = new VolvoFM7();
        assertEquals(2, fm7.getNrDoors());
        assertEquals(Color.white, fm7.getColor());
        assertEquals(90, fm7.getEnginePower(), 0.0);
        assertEquals(0, fm7.getCurrentSpeed(), 0.0);
        assertEquals(0,fm7.getPos()[0],0.0);
        assertEquals(0,fm7.getPos()[1],0.0);
        assertEquals(0 ,fm7.getDirection()[0]);
        assertEquals(1 ,fm7.getDirection()[1]);
        assertFalse(fm7.getTruckBedStatus());
        assertEquals(4 ,fm7.getMaxSlots());
        assertEquals(0 ,fm7.getLoadedCars().size());
    }
    @Test
    public void testMoveWhileRaisedException() {
        VolvoFM7 fm7 = new VolvoFM7();
        fm7.setTruckBedExtended();
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> fm7.move());
        assertEquals("Flaket måste vara nedfällt för att köra", exception.getMessage());
    }

    @Test
    public void testRaiseWhileMove(){
        VolvoFM7 fm7 = new VolvoFM7();
        fm7.startEngine();
        fm7.gas(1);
        fm7.move();
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> fm7.setTruckBedExtended());
        assertEquals("Bilen måste stå still för att tippa flaket uppåt", exception.getMessage());
    }

    @Test
    public void testLoadCarsWhileRaisedTruckBed(){
        VolvoFM7 fm7 = new VolvoFM7();
        Saab95 saab = new Saab95();
        fm7.setTruckBedNeutral();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            fm7.loadCars(saab);
        });
        assertEquals("Rampen måste vara nedfälld (truckBedExtended = true) för att lasta en bil", exception.getMessage());
    }

    @Test
    public void testLoadCarsWhenFullTruckBed(){
        VolvoFM7 fm7 = new VolvoFM7();
        Saab95 saab1 = new Saab95();
        Saab95 saab2 = new Saab95();
        Saab95 saab3 = new Saab95();
        Saab95 saab4 = new Saab95();
        Saab95 saab5 = new Saab95();
        fm7.setTruckBedExtended();
        fm7.loadCars(saab1);
        fm7.loadCars(saab2);
        fm7.loadCars(saab3);
        fm7.loadCars(saab4);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            fm7.loadCars(saab5);
        });
        assertEquals("Flaket är fullt", exception.getMessage());
    }

    @Test
    public void testLoadCarTooFarAway(){
        VolvoFM7 fm7 = new VolvoFM7();
        Saab95 saab = new Saab95();
        saab.gas(1);
        saab.move();
        fm7.setTruckBedExtended();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            fm7.loadCars(saab);
        });
        assertEquals("Avståndet är för långt för att lasta", exception.getMessage());
    }

    @Test
    public void testLoadCar(){
        VolvoFM7 fm7 = new VolvoFM7();
        Saab95 saab = new Saab95();
        Volvo240 volvo = new Volvo240();
        fm7.setTruckBedExtended();
        fm7.loadCars(saab);
        fm7.loadCars(volvo);
        assertEquals(2, fm7.getNumberLoadedCars());
    }

    @Test
    public void testOffloadWhileTruckBedNeutral(){
        VolvoFM7 fm7 = new VolvoFM7();
        Saab95 saab = new Saab95();
        fm7.setTruckBedExtended();
        fm7.loadCars(saab);
        fm7.setTruckBedNeutral();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            fm7.offLoadCars();
        });
        assertEquals("Rampen måste vara nedfälld (truckBedExtended = true) för att lasta en bil", exception.getMessage());
    }

    @Test
    public void testOffloadWhileTruckBedEmpty(){
        VolvoFM7 fm7 = new VolvoFM7();
        fm7.setTruckBedExtended();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            fm7.offLoadCars();
        });
        assertEquals("Just nu är inga bilar lastade", exception.getMessage());
    }

    @Test
    public void testMoveCarsWithTruck(){
        VolvoFM7 fm7 = new VolvoFM7();
        Saab95 saab = new Saab95();
        fm7.setTruckBedExtended();
        fm7.loadCars(saab);
        fm7.setTruckBedNeutral();
        for (int i = 0; i < 10; i++) {
            fm7.gas(1);
            fm7.move();
        }
        assertEquals(fm7.getPos()[0], saab.getPos()[0], 0.0);
        assertEquals(fm7.getPos()[1], saab.getPos()[1], 0.0);
    }

}
