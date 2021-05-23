import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class BomberPlane extends Plane{
    private final Random random = new Random();
    private boolean eventAndFuel;
    public BomberPlane(){
        super();
        Image tpimg = new ImageIcon("planesModel\\img\\bomber.png").getImage();
        Image eventImage = new ImageIcon("planesModel\\img\\bombs.png").getImage();
        Image fuelAdderImg = new ImageIcon("planesModel\\img\\fuelAdder.png").getImage();
        Image[] images = {tpimg, eventImage, fuelAdderImg};
        set_Images(images);
        int distanceEvent = random.nextInt(3000) + 2000;
        set_distanceToEvent(distanceEvent);
        set_timeFueling(40);
        add_fuel(random.nextInt(4000) + 4000);
        set_fuelX(STANDART_X + 400);
        set_fuelY(STANDART_Y);
        set_travelDistance(distanceEvent * 2 + 1500);
        eventAndFuel = false;
}
    
    @Override
    public void addFuel(){
        if (get_Y() < STANDART_Y + 200 && get_timeFueling() > 0){
            set_y(get_Y() + get_vY());
        }
        if (get_fuelX() > STANDART_X - 550){
            set_fuelX(get_fuelX() - get_vX());
        }
        else{
            if(get_timeFueling() > 0){
                add_fuel(150);
                add_timeFueling(-1);
            }
            else{
                if (get_fuelY() > STANDART_Y - 400){
                    set_fuelY(get_fuelY() - get_vY());
                }
                else if (get_Y() > STANDART_Y){
                    set_y(get_Y() - get_vY());
                }
                else{
                    set_addingFuelFlag(false);
                }
            }
        }
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
