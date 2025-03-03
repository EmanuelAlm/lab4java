public interface TruckBed {
    void setTruckBedNeutral();

    void setTruckBedExtended();

    TruckBedState getTruckBedState();

    void switchStateToNeutral();

    void switchStateToExtended();
}
