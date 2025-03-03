
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestAllModelWorkshop {

    @Test
    public void testCheckInCar() {
        AllModelWorkshop<VehicleFunctionality> workshop = new AllModelWorkshop<>(15);
        Volvo240 volvo = new Volvo240();
        Saab95 saab = new Saab95();
        int size = workshop.getCheckedInCars().size();
        workshop.checkInCar(volvo);
        workshop.checkInCar(saab);
        assertEquals(size+2, workshop.getCheckedInCars().size());
    }

    @Test
    public void testCheckInCarWhenFullWorkshop() {
        AllModelWorkshop<VehicleFunctionality> workshop = new AllModelWorkshop<>(2);
        Volvo240 volvo1 = new Volvo240();
        Volvo240 volvo2 = new Volvo240();
        Volvo240 volvo3 = new Volvo240();
        workshop.checkInCar(volvo1);
        workshop.checkInCar(volvo2);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            workshop.checkInCar(volvo3);
        });
        assertEquals("Verkstaden är full", exception.getMessage());
    }

    @Test
    public void testCheckInSameCarTwice() {
        AllModelWorkshop<VehicleFunctionality> workshop = new AllModelWorkshop<>(15);
        Volvo240 volvo1 = new Volvo240();
        workshop.checkInCar(volvo1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            workshop.checkInCar(volvo1);
        });
        assertEquals("Denna bil är redan inlämnad till verkstaden", exception.getMessage());
    }

    @Test
    public void testCheckOutCar() {
        AllModelWorkshop<VehicleFunctionality> workshop = new AllModelWorkshop<>(2);
        Volvo240 volvo = new Volvo240();
        workshop.checkInCar(volvo);
        int size = workshop.getCheckedInCars().size();
        workshop.checkOutCar(volvo);
        assertEquals(0, size-1);
    }

    @Test
    public void testCheckOutNotExistingCar() {
        AllModelWorkshop<VehicleFunctionality> workshop = new AllModelWorkshop<>(2);
        Volvo240 volvo = new Volvo240();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            workshop.checkOutCar(volvo);
        });
        assertEquals("Bilen är inte i denna verkstaden", exception.getMessage());
    }

}
