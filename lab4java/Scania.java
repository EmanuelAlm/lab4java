import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Scania implements Movable, TruckBed, Vehicle {
    private int truckBed;
    private final int maxTilt;
    private TruckBedState truckBedState;

    private final VehicleFunctionality parent;

    public Scania (){
        this.parent = new VehicleFunctionality(2, Color.white, 90,8);
        this.truckBed = 0;
        this.maxTilt = 70;
        this.truckBedState = new TruckBedNeutral(this.parent);
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

    public int getMaxTilt() {
        return maxTilt;
    }

    public int getTruckBedAngle(){
        return this.truckBed;
    }

    public void setPos(double[] pos) {
        parent.setPos(pos);
    }

    public void setDirection(int[] direction){
        parent.setDirection(direction);
    }

    public void tiltBed(int degrees) {
        if ((degrees > 70) || (degrees < -70)){
            throw new IllegalArgumentException("Ge ett värde mellan -70 och 70");
        }
        if (this.getCurrentSpeed() !=0) {
            throw new IllegalArgumentException("Får inte vinkla flaket när lastbilen är i rörelse");
        }
        if (((this.truckBed + degrees) > 70) || ((this.truckBed + degrees) < 0)){
            int maxTiltPossible = this.maxTilt - this.truckBed;
            int minTiltPossible = this.truckBed;
            throw new IllegalArgumentException("vinkeln får inte höjas mer än " + maxTiltPossible + " eller sänkas med mer än " + minTiltPossible);
        }
        else this.truckBed += degrees;

        if (this.truckBed != 0) switchStateToExtended();
        else switchStateToNeutral();

    }

    public void setTruckBedExtended(){
        if (this.getCurrentSpeed() != 0){
            throw new IllegalArgumentException("Bilen måste stå still för att tippa flaket uppåt");
        } else{
            this.switchStateToExtended();
            this.truckBed = this.maxTilt;
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
        this.truckBed = 0;
    }

    public TruckBedState getTruckBedState(){
        return this.truckBedState;
    }


    protected double speedFactor(){
        return parent.speedFactor();
    }

    public void move() {
        try {
            truckBedState.move();
        }
        catch (IllegalArgumentException e) {
            return;
        }
    }

    @Override
    public BufferedImage getPic() throws IOException {
        return ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
    }
}
