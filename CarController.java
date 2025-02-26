import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Vehicle> cars = new ArrayList<>();
    AllModelWorkshop<Volvo240> volvoWorkshop = new AllModelWorkshop<>(10);


    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        Saab95 saab = new Saab95();
        saab.setPos(new double[]{0,100});

        Volvo240 volvo = new Volvo240();
        volvo.setPos(new double[]{0,0});

        Scania scania = new Scania();
        scania.setPos(new double[]{0,200});


        cc.cars.add(saab);
        cc.cars.add(volvo);
        cc.cars.add(scania);


        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
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


                if (x >= 300 && x <= 401 && y >= 300 && y <= 396 && car instanceof Volvo240) {
                    car.stopEngine();
                    volvoWorkshop.checkInCar((Volvo240) car);
                    cars.remove(car);
                }

                frame.drawPanel.moveit(car, x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
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
}
