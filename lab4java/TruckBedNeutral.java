public class TruckBedNeutral implements TruckBedState{
    private final VehicleFunctionality parent;

    public TruckBedNeutral(VehicleFunctionality parent) {
        this.parent = parent;
    }

    public void move(){
        this.parent.move();
    }
}
