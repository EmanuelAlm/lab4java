import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;


public class VolvoFM7 implements Movable, TruckBed, Vehicle{
    private TruckBedState truckBedState;
    private int maxSlots;
    private Stack<Vehicle> slots;

    public VehicleFunctionality parent;

    public VolvoFM7 (){
        this.parent = new VehicleFunctionality(2, Color.white, 90,10);
        this.truckBedState = new TruckBedNeutral(this.parent); // initieras till att rampen är uppfälld (dvs går ej att lasta)
        this.maxSlots = 4;
        this.slots = new Stack<Vehicle>();
    }

    public int getNrDoors(){
        return parent.getNrDoors();
    }

    public double getEnginePower(){
        return parent.getEnginePower();
    }

    public double getCurrentSpeed(){
        return parent.getCurrentSpeed();
    }

    public Color getColor(){
        return parent.getColor();
    }

    public double getWeight(){
        return parent.getWeight();
    }

    public void setColor(Color clr){
        parent.setColor(clr);
    }

    public void startEngine(){
        parent.startEngine();
    }

    public void stopEngine(){
        parent.stopEngine();
    }

    public double[] getPos(){
        return parent.getPos();
    }

    public int[] getDirection(){
        return parent.getDirection();
    }

    public void gas(double amount){
        parent.gas(amount);
    }

    public void brake(double amount){
        parent.brake(amount);
    }

    public void turnLeft(){
        parent.turnLeft();
    }

    public void turnRight(){
        parent.turnRight();
    }

    public void setPos(double[] pos) {
        parent.setPos(pos);
    }

    public void setDirection(int[] direction){
        parent.setDirection(direction);
    }

    public void loadCars(Vehicle car) {
        double deltaX = this.getPos()[0]-car.getPos()[0];
        double deltaY = this.getPos()[1]-car.getPos()[1];
        double distance = Math.sqrt(Math.pow(deltaX,2) + Math.pow(deltaY,2));
        if (this.truckBedState instanceof TruckBedNeutral) {
            throw new IllegalArgumentException("Rampen måste vara nedfälld för att lasta en bil");
        }
        else if (this.slots.size() >= maxSlots){
            throw new IllegalArgumentException("Flaket är fullt");
        }
        else if (car.getWeight() > 3.5) {
            throw new IllegalArgumentException("Bilen du försöker lasta är för stor");
        }
        else if (distance > 1){
            throw new IllegalArgumentException("Avståndet är för långt för att lasta");
        }
        else this.slots.push(car);
    }

    public void offLoadCars() {
        if (this.truckBedState instanceof TruckBedNeutral){
            throw new IllegalArgumentException("Rampen måste vara nedfälld för att lasta en bil");
        }
        else if (this.slots.isEmpty()){
            throw new IllegalArgumentException("Just nu är inga bilar lastade");
        }
        else {
            Vehicle offLoadedCar = this.slots.pop();
            offLoadedCar.setDirection(this.getDirection());
            double[] newPos = {this.getPos()[0]+1,this.getPos()[1]};
            offLoadedCar.setPos(newPos);
        }
    }

    public int getMaxSlots(){
        return this.maxSlots;
    }

    public ArrayList<Vehicle> getLoadedCars(){
        ArrayList<Vehicle> safeCopy = new ArrayList<>(this.slots); //In order to prevent users from manipulating the original arraylist
        return safeCopy;
    }


    public void setTruckBedExtended(){
        if (this.getCurrentSpeed() != 0){
            throw new IllegalArgumentException("Bilen måste stå still för att tippa flaket uppåt");
        } else{
            this.switchStateToExtended();
        }
    }

    public void switchStateToNeutral(){
        truckBedState = new TruckBedNeutral(this.parent);
    }

    public void switchStateToExtended(){
        truckBedState = new TruckBedExtended();
    }

    public void setTruckBedNeutral(){
        switchStateToNeutral();
    }

    public TruckBedState getTruckBedState(){
        return this.truckBedState;
    }

    public int getNumberLoadedCars(){
        return this.slots.size();
    }


    protected double speedFactor(){
        return getEnginePower() * 0.01;
    }

    @Override
    public void move() {
        truckBedState.move();
        if (this.truckBedState instanceof TruckBedNeutral) {
            for (Vehicle car : slots) {
                car.setPos(this.getPos());
            }
        }
    }

    @Override
    public BufferedImage getPic() throws IOException {
        return ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
    }
}



