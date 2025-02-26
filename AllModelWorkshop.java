import java.util.ArrayList;

public class AllModelWorkshop<T extends Vehicle> implements Workshop<T> {
    private final int maxCapacity;
    private final ArrayList<T> checkedInCars;

    public AllModelWorkshop(int maxCapacity){
        this.maxCapacity = maxCapacity;
        this.checkedInCars  = new ArrayList<>(maxCapacity);
    }

    public void checkInCar(T car) {
        if (checkedInCars.contains(car)){
            throw new IllegalArgumentException("Denna bil är redan inlämnad till verkstaden");
        }
        else if (checkedInCars.size() >= this.maxCapacity){
            throw new IllegalArgumentException("Verkstaden är full");
        }
        else checkedInCars.add(car);
    }

    public void checkOutCar(T car){
        if (!checkedInCars.contains(car)){
            throw new IllegalArgumentException("Bilen är inte i denna verkstaden");
        }
        else checkedInCars.remove(car);
    }

    public ArrayList<T> getCheckedInCars(){
        ArrayList<T> safeCopy = new ArrayList<>(this.checkedInCars); //In order to prevent users from manipulating the original arraylist
        return safeCopy;
    }

}
