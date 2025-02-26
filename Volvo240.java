import java.awt.*;

public class Volvo240 extends VehicleFunctionality implements Movable{

    private final static double trimFactor = 1.25;
    
    public Volvo240(){
        super(4,Color.black,100,1.5);
    }

    @Override
    protected double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }

    public static double getTrimFactor() {
        return trimFactor;
    }

}
