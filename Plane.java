import java.awt.*;
import java.util.Random;
import javax.swing.*;

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
    
    // private int eventPositionX = 600;
    int skySunRiseCordinateStart;
    int skyMorinigCordinateStart;
    int skyDayCordinateStart;
    int skySunSetCordinateStart;
    int skyNightCordinateStart;
    // private Image tpimg;
    // private Image eventImage;
    // private Image fuelAdderImg;
    // private int eventX;
    // private int eventY;
    // private int fuelX;
    // private int fuelY;
    // private int eventVX;
    // private int eventVY;
    // private int vX;
    // private int vY;
    // private int x;
    // private int y;
    // private int fuel;
    // private int s;
    // private int travelDistance;
    // private int distanceToEvent;
    // private int timeFueling;
    // private boolean planeDown;
    // private boolean planeRise;
    // private boolean event;
    // private boolean eventDone;
    // private boolean messageFuelNotShowed;
    // private boolean message2FuelNotShowed;
    // private boolean addingFuel;
    // private boolean toAirport;
   


    public abstract Image[] get_Images();
    public abstract int get_Fuel();
    public abstract int get_fuelX();
    public abstract int get_fuelY();
    public abstract int get_vX();
    public abstract int get_vY();
    public abstract int get_X();
    public abstract int get_Y();
    public abstract int get_s();
    public abstract int get_eventVX();
    public abstract int get_eventVY();
    public abstract int get_eventX();
    public abstract int get_eventY();
    public abstract int get_timeFueling();
    public abstract int get_travelDistance();
    public abstract int get_distanceToEvent();
    public abstract int get_eventPositionX();
    public abstract boolean isEvent();
    public abstract boolean isEventDone();
    public abstract boolean isMessageFuelNotShowed();
    public abstract boolean isMessage2FuelNotShowed();
    public abstract boolean isToAirport();
    public abstract boolean isPlaneDown();
    public abstract boolean isPlaneRise();
    public abstract boolean isAddingFuel();
    public abstract void set_distanceToEvent(int d);
    public abstract void set_EventFlag(boolean flag);
    
    public abstract void set_addingFuelFlag(boolean flag);
    public abstract void set_fuel(int num);
    public abstract void add_timeFueling(int num);


    public Plane(){
        skySunRiseCordinateStart = LAYER1_CONST;
        skyMorinigCordinateStart = LAYER2_CONST;
        skyDayCordinateStart = LAYER3_CONST;
        skySunSetCordinateStart = LAYER4_CONST;
        skyNightCordinateStart = LAYER5_CONST;
    }

    public abstract void downToAirport();

    public abstract void addFuel();

    public abstract void planeRize();

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

    public abstract void move();

    public abstract void planeEvent();
    
}
