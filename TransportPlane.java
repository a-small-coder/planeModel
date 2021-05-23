import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class TransportPlane extends Plane{
    private int eventPositionX = 600;
    int skySunRiseCordinateStart = LAYER1_CONST;
    int skyMorinigCordinateStart = LAYER2_CONST;
    int skyDayCordinateStart = LAYER3_CONST;
    int skySunSetCordinateStart = LAYER4_CONST;
    int skyNightCordinateStart = LAYER5_CONST;
    private Image tpimg;
    private Image eventImage;
    private Image fuelAdderImg;
    private int eventX;
    private int eventY;
    private int fuelX;
    private int fuelY;
    private int eventVX;
    private int eventVY;
    private int vX;
    private int vY;
    private int x;
    private int y;
    private int fuel;
    private int s;
    private int travelDistance;
    private int distanceToEvent;
    private int timeFueling;
    private boolean planeDown;
    private boolean planeRise;
    private boolean event;
    private boolean eventDone;
    private boolean messageFuelNotShowed;
    private boolean message2FuelNotShowed;
    private boolean addingFuel;
    private boolean toAirport;


    public TransportPlane(){
        super();
        tpimg = new ImageIcon("planesModel\\img\\transportPlane.png").getImage();
        eventImage = new ImageIcon("planesModel\\img\\desant.png").getImage();
        fuelAdderImg = new ImageIcon("planesModel\\img\\fuelAdder.png").getImage();
        distanceToEvent = random.nextInt(3000) + 1000;
        timeFueling = 30;
        fuel = random.nextInt(5000) + 4000;
        s = 0;
        x = 1400;
        y = 900;
        vX = 10;
        vY = 20;
        eventVX = 20;
        eventVY = 10;
        eventX = 900;
        eventY = 100;
        fuelY = STANDART_Y;
        fuelX = STANDART_X + 400;
        travelDistance = distanceToEvent * 2;
        planeDown = false;
        planeRise = true;
        event = false;
        eventDone = false;
        toAirport = false;
        messageFuelNotShowed = true;
        message2FuelNotShowed = true;
        addingFuel = false;
}
    public Image[] get_Images(){
        Image[] imgs = {tpimg, eventImage, fuelAdderImg};
        return imgs;
    }
    public int get_Fuel(){
        return fuel;
    }
    public int get_fuelX(){
        return fuelX;
    }
    public int get_fuelY(){
        return fuelY;
    }
    public int get_vX(){
        return vX;
    }
    public int get_vY(){
        return vY;
    }
    public int get_X(){
        return x;
    }
    public int get_Y(){
        return y;
    }
    public int get_s(){
        return s;
    }
    public int get_eventVX(){
        return eventVX;
    }
    public int get_eventVY(){
        return eventVY;
    }
    public int get_eventX(){
        return eventX;
    }
    public int get_eventY(){
        return eventY;
    }
    public int get_timeFueling(){
        return timeFueling;
    }
    public int get_travelDistance(){
        return travelDistance;
    }
    public int get_distanceToEvent(){
        return distanceToEvent;
    }
    public int get_eventPositionX(){
        return eventPositionX;
    }
    public boolean isEvent(){
        return event;
    }
    public boolean isEventDone(){
        return eventDone;
    }
    public boolean isMessageFuelNotShowed(){
        return messageFuelNotShowed;
    }
    public boolean isMessage2FuelNotShowed(){
        return message2FuelNotShowed;
    }
    public boolean isToAirport(){
        return toAirport;
    }
    public boolean isPlaneDown(){
        return planeDown;
    }
    public boolean isPlaneRise() {
        return planeRise;
    }
    public boolean isAddingFuel() {
        return addingFuel;
    }
    public void set_distanceToEvent(int d){
        this.distanceToEvent = d;
    }
    public void set_EventFlag(boolean flag){
        this.event = flag;
    }
    
    public void set_addingFuelFlag(boolean flag){
        this.addingFuel = flag;
    }
    public void set_fuel(int num){
        this.fuel += num;
    }
    public void add_timeFueling(int num){
        this.timeFueling += num;
    }
    public void downToAirport(){
        planeDown = true;
        toAirport = true;
    }
    @Override
    public void addFuel(){
        int tF = get_timeFueling();
        if (y < STANDART_Y + 200 && tF > 0){
            y += vY;
        }
        if (fuelX > STANDART_X - 550){
            fuelX -= vX;
        }
        else{
            if(tF > 0){
                set_fuel(150);
                add_timeFueling(-1);
            }
            else{
                if (fuelY > STANDART_Y - 400){
                    fuelY -= vY;
                }
                else if (y > STANDART_Y){
                    y -= vY;
                }
                else{
                    addingFuel = false;
                }
            }
        }
    }
    @Override
    public void planeRize(){
        if (y > STANDART_Y) {
            y -= vY;
            if (x > STANDART_X){
                x -= vX;
            }
        }
        if (y == STANDART_Y){
            planeRise = false;
        }
        fuel -= vX;
        s += vX;
        moveLayers(vX);
    }
    @Override
    public void move(){
        if ((travelDistance - s) >= fuel + 1000 && messageFuelNotShowed){
            JOptionPane.showMessageDialog(null, "Потребуется дозаправка в воздухе");
            messageFuelNotShowed = false;
        }

        if (fuel < 1500  && (travelDistance - s) > 1000 && !planeDown && message2FuelNotShowed){
            JOptionPane.showMessageDialog(null, "Мало топлива! Вызовите дозаправщик");
            message2FuelNotShowed = false;
        }

        if (s >= distanceToEvent && !eventDone && !addingFuel){
            event = true;
        }

        if (fuel <= 0 && !planeDown){
            JOptionPane.showMessageDialog(null, "Топливо закончилось. Самолет упал");
            planeDown = true;
        }
        if(planeDown){
            if ( y < 1000){
                y += vY;
                s += vX;
            }
            if (toAirport){
                x -= vX * 2;
            }
        }
        else{
            s += vX;
            fuel -= vX;
            if (fuel < 0){
                fuel = 0;
            }
        }
        moveLayers(vX);
    }

    public void planeEvent(){
        if(x >= eventPositionX && eventY < 1000){
            x -= vX;
        }
        else{
            eventX += eventVX;
            eventY += eventVY;
        }
        
        if (eventY > 1000){
            
            if(x < STANDART_X){
                x += vX;
            }
            else{
                event = false;
                distanceToEvent = 0;
                eventDone = true;
            }
        }
        fuel -= vX;
        s += vX;
        if (fuel < 0){
            fuel = 0;
        }
        moveLayers(vX);
    }

}
