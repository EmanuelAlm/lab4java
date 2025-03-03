import java.util.ArrayList;

public interface Workshop<T> {
    void checkInCar(T car);
    void checkOutCar(T car);
    ArrayList<T> getCheckedInCars();
}
