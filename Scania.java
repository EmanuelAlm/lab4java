import java.awt.*;

public class Scania implements Movable, TruckBed, Vehicle {
    private int truckBed;
    private final int maxTilt;
    private boolean truckBedExtended;

    private final VehicleFunctionality parent;

    public Scania (){
        this.parent = new VehicleFunctionality(2, Color.white, 90,8);
        this.truckBed = 0;
        this.maxTilt = 70;
        this.truckBedExtended = false;
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

        if (this.truckBed != 0) this.truckBedExtended = true;
        else this.truckBedExtended = false;

    }

    public void setTruckBedExtended(){
        if (this.getCurrentSpeed() != 0){
            throw new IllegalArgumentException("Bilen måste stå still för att tippa flaket uppåt");
        } else{
            this.truckBedExtended = true;
            this.truckBed = this.maxTilt;
        }

    }

    public void setTruckBedNeutral(){
        this.truckBedExtended = false;
        this.truckBed = 0;
    }

    public boolean getTruckBedStatus(){
        return this.truckBedExtended;
    }


    protected double speedFactor(){
        return parent.speedFactor();
    }

    public void move() {
        try {
            if (!this.truckBedExtended) {
                parent.move();
            } else throw new IllegalArgumentException("Du kan inte röra dig när flaket är uppe");
        }
        catch (IllegalArgumentException e) {
            return;
        }
    }
}
