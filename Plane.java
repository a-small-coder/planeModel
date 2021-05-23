import java.awt.*;
import java.util.Random;

import javax.swing.JOptionPane;

public abstract class  Plane {
    final int LAYER1_CONST = 0;
    final int LAYER2_CONST = -8936;
    final int LAYER3_CONST = -17872;
    final int LAYER4_CONST = -20000;
    final int LAYER5_CONST = -28800;
    final Random random = new Random();
    final int STANDART_Y = 100;
    final int STANDART_X = 1000;
    final int maximumTravelDistance = 13000;
    
    private int eventPositionX = 600;
    int skySunRiseCordinateStart;
    int skyMorinigCordinateStart;
    int skyDayCordinateStart;
    int skySunSetCordinateStart;
    int skyNightCordinateStart;
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
   

    public Plane(){
        skySunRiseCordinateStart = LAYER1_CONST;
        skyMorinigCordinateStart = LAYER2_CONST;
        skyDayCordinateStart = LAYER3_CONST;
        skySunSetCordinateStart = LAYER4_CONST;
        skyNightCordinateStart = LAYER5_CONST;
        planeDown = false;
        planeRise = true;
        event = false;
        eventDone = false;
        toAirport = false;
        messageFuelNotShowed = true;
        message2FuelNotShowed = true;
        addingFuel = false;
        fuel = 0;
        set_s(0);
        set_x(1400);
        set_y(900);
        set_vX(10);
        set_vY(20);
        set_eventX(900);
        set_eventY(100);
        set_eventVX(20);
        set_eventVY(10);
    }
    
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
        moveLayers( get_vX());
    }
    
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
        moveLayers(get_vX());
    }

    public void moveLayers(int vx){
        if (skyNightCordinateStart + vx >= 0){
            skySunRiseCordinateStart = LAYER1_CONST;
            skyMorinigCordinateStart = LAYER2_CONST;
            skyDayCordinateStart = LAYER3_CONST;
            skySunSetCordinateStart = LAYER4_CONST;
            skyNightCordinateStart = LAYER5_CONST;
        }
        else {
            skySunRiseCordinateStart += vx;
            skyMorinigCordinateStart += vx;
            skyDayCordinateStart += vx;
            skySunSetCordinateStart += vx;
            skyNightCordinateStart += vx;
        }
    }

    public void set_Images(Image[] images){
        tpimg = images[0];
        eventImage = images[1];
        fuelAdderImg = images[2];
    }
    public void set_timeFueling(int num){
        timeFueling = num;
    }
    public void set_s(int num){
        s = num;
    }
    public void set_x(int num){
        x = num;
    }
    public void set_y(int num){
        y = num;
    }
    public void set_vX(int num){
        vX = num;
    }
    public void set_vY(int num){
        vY = num;
    }
    public void set_eventX(int num){
        eventX = num;
    }
    public void set_eventY(int num){
        eventY = num;
    }
    public void set_eventVX(int num){
        eventVX = num;
    }
    public void set_eventVY(int num){
        eventVY = num;
    }
    public void set_fuelX(int num){
        fuelX = num;
    }
    public void set_fuelY(int num){
        fuelY = num;
    }
    public void set_travelDistance(int num){
        travelDistance = num;
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
    public void set_EventDoneFlag(boolean flag){
        eventDone = flag;
    }
    public void set_addingFuelFlag(boolean flag){
        this.addingFuel = flag;
    }
    public void add_fuel(int num){
        this.fuel += num;
    }
    public void add_timeFueling(int num){
        this.timeFueling += num;
    }
    public void set_planeDownFlag(boolean flag){
        planeDown = flag;
    }
    public void set_toAirport(boolean flag){
        toAirport = flag;
    }

    public abstract void downToAirport();
    public abstract void addFuel();
    public abstract void planeEvent();
    
}
