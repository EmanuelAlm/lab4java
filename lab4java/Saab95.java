import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Saab95 extends VehicleFunctionality implements Movable{

    private boolean turboOn;

    public Saab95(){
        super(2, Color.red, 125,2);
	    turboOn = false;
    }

    public void setTurboOn(){
	    turboOn = true;
    }

    public void setTurboOff(){
	    turboOn = false;
    }

    public boolean getTurboStatus(){
        return turboOn;
    }

    @Override
    protected double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }

    @Override
    public BufferedImage getPic() throws IOException {
        return ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
    }

}
