public class TruckBedExtended implements TruckBedState {

    public void move(){
        throw new IllegalArgumentException("Du kan inte röra dig när flaket är uppe");
    }
}
