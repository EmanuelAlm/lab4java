import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

public class CarModel {
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());
    private final ArrayList<ModelUpdateListener> listeners = new ArrayList<>();

    // A list of cars, modify if needed
    Stack<Vehicle> cars = new Stack<>();
    AllModelWorkshop<Volvo240> volvoWorkshop = new AllModelWorkshop<>(10);


    public CarModel() {
        // Instance of this class

        Vehicle saab = VehicleFactory.createSaab95();
        saab.setPos(new double[]{0,100});

        Vehicle volvo = VehicleFactory.createVolvo240();
        volvo.setPos(new double[]{0,0});

        Vehicle scania = VehicleFactory.createScania();
        scania.setPos(new double[]{0,200});

        cars.push(saab);
        cars.push(volvo);
        cars.push(scania);

        // Start the timer
        timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {
                car.move();
                int x = (int) Math.round(car.getPos()[0]);
                int y = (int) Math.round(car.getPos()[1]);

                // x gränser: 800 - 100 (bredd på bilens bild)
                //y gränser: 800 - 240 (DrawPanel drawPanel = new DrawPanel(X, Y-240)) - 60 (höjd på bilens bild)
                if (x < 0 || x > 700 || y < 0 || y > 500){
                    //car.stopEngine();
                    car.turnLeft();
                    car.turnLeft();
                    //car.startEngine();
                }


                if (x >= 300 && x <= 401 && y >= 300 && y <= 396) {
                    try {
                        volvoWorkshop.checkInCar((Volvo240) car);
                        car.stopEngine();
                        cars.remove(car);
                    }
                    catch (ClassCastException er) {
                    }
                }

                notifyListeners();
            }
        }
    }


    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars){
            car.brake(brake);
        }
    }


    void setTurboOff(){
        for (Vehicle car : cars){
            if (car instanceof Saab95){
                ((Saab95)car).setTurboOff();
            }
        }
    }
    void setTurboOn(){
        for (Vehicle car : cars) {
            if (car instanceof Saab95){
                ((Saab95) car).setTurboOn();
            }
        }
    }

    void liftBed(){
        for (Vehicle car : cars) {
            if (car instanceof TruckBed){
                ((TruckBed) car).setTruckBedExtended();
            }
        }
    }

    void lowerBed(){
        for (Vehicle car : cars) {
            if (car instanceof TruckBed){
                ((TruckBed) car).setTruckBedNeutral();
            }
        }
    }

    void start(){
        for (Vehicle car : cars){
            car.startEngine();
        }
    }

    void stop(){
        for (Vehicle car : cars){
            car.stopEngine();
        }
    }

    void turnLeft(){
        for (Vehicle car : cars){
            car.turnLeft();
        }
    }

    void turnRight(){
        for (Vehicle car : cars){
            car.turnRight();
        }
    }


    public void addListener(ModelUpdateListener l){
        listeners.add(l);
    }

    public void addCar(){
        if (cars.size() < 10){
            Vehicle newCar = VehicleFactory.createRandomCar();
            this.cars.push(newCar);
            newCar.setPos(new double[]{0,0});
        }

    }

    public void removeCar(){
        if (cars.size() > 1){
            this.cars.pop();
        }

    }

    protected void notifyListeners(){

        for (ModelUpdateListener l : listeners)
            l.actOnModelUpdate();
    }
}
