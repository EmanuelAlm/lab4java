import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

    @Override
    public BufferedImage getPic() throws IOException {
        return ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
    }
}
