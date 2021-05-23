import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class FierFighter extends Plane{
    private final Random random = new Random();
    private boolean eventAndFuel;
    public FierFighter(){
        super();
        Image tpimg = new ImageIcon("planesModel\\img\\fierFighter.png").getImage();
        Image eventImage = new ImageIcon("planesModel\\img\\whater.png").getImage();
        Image fuelAdderImg = new ImageIcon("planesModel\\img\\fuelAdder.png").getImage();
        Image[] images = {tpimg, eventImage, fuelAdderImg};
        set_Images(images);
        int distanceEvent = random.nextInt(1000) + 1000;
        set_distanceToEvent(distanceEvent);
        add_fuel(random.nextInt(2000) + 5000);
        set_travelDistance(distanceEvent * 2);
        eventAndFuel = false;
}
    
    @Override
    public void addFuel(){
        set_addingFuelFlag(true);
    }

    @Override
    public void planeEvent(){
        
        if(get_X() >= get_eventPositionX() && get_eventY() < 1000){
            set_x(get_X() - get_vX());
        }
        else{
            if (isAddingFuel() && isEvent() && !eventAndFuel){
                set_eventX(get_X() + 215);
                set_eventY(get_Y());
                eventAndFuel = true;
            }
            set_eventX(get_eventX() + get_eventVX());
            set_eventY(get_eventY() + get_eventVY());
        }
        
        if (get_eventY() > 1000){
            
            if(get_X() < STANDART_X){
                set_x(get_X() +get_vX());
            }
            else{
                set_EventFlag(false);
                set_distanceToEvent(0);
                set_EventDoneFlag(true);
            }
        }
        add_fuel(-1 * get_vX());
        set_s(get_s() + get_vX());
        if (get_Fuel() < 0){
            add_fuel(-1 * get_Fuel());
        }
        moveLayers(get_vX());
    }

    @Override
    public void downToAirport(){
        set_planeDownFlag(true);
        set_toAirport(true);
    }

}
