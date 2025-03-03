import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class TestVolvoWorkshop {

    @Test
    public void testCheckInCar() {
        Volvo240Workshop volvoWorkshop = new Volvo240Workshop(15);
        Volvo240 volvo1 = new Volvo240();
        Volvo240 volvo2 = new Volvo240();
        int size = volvoWorkshop.getCheckedInCars().size();
        volvoWorkshop.checkInCar(volvo1);
        volvoWorkshop.checkInCar(volvo2);
        assertEquals(size+2, volvoWorkshop.getCheckedInCars().size());
    }

    @Test
    public void testCheckInCarWhenFullWorkshop() {
        Volvo240Workshop volvoWorkshop = new Volvo240Workshop(2);
        Volvo240 volvo1 = new Volvo240();
        Volvo240 volvo2 = new Volvo240();
        Volvo240 volvo3 = new Volvo240();
        volvoWorkshop.checkInCar(volvo1);
        volvoWorkshop.checkInCar(volvo2);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            volvoWorkshop.checkInCar(volvo3);
        });
        assertEquals("Verkstaden är full", exception.getMessage());
    }

    @Test
    public void testCheckInSameCarTwice() {
        Volvo240Workshop volvoWorkshop = new Volvo240Workshop(15);
        Volvo240 volvo1 = new Volvo240();
        volvoWorkshop.checkInCar(volvo1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            volvoWorkshop.checkInCar(volvo1);
        });
        assertEquals("Denna bil är redan inlämnad till verkstaden", exception.getMessage());
    }

    @Test
    public void testCheckOutCar() {
        Volvo240Workshop volvoWorkshop = new Volvo240Workshop(15);
        Volvo240 volvo = new Volvo240();
        volvoWorkshop.checkInCar(volvo);
        int size = volvoWorkshop.getCheckedInCars().size();
        volvoWorkshop.checkOutCar(volvo);
        assertEquals(0, size-1);
    }

    @Test
    public void testCheckOutNotExistingCar() {
        Volvo240Workshop volvoWorkshop = new Volvo240Workshop(15);
        Volvo240 volvo = new Volvo240();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            volvoWorkshop.checkOutCar(volvo);
        });
        assertEquals("Bilen är inte i denna verkstaden", exception.getMessage());
    }
}
