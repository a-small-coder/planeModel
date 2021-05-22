import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class Plane {
    final int LAYER1_CONST = 0;
    final int LAYER2_CONST = -8936;
    final int LAYER3_CONST = -17872;
    final int LAYER4_CONST = -20000;
    final int LAYER5_CONST = -28800;
    final Random random = new Random();
    final int STANDART_Y = 100;
    final int STANDART_X = 1000;
    final int maximumTravelDistance = 13000;
    Image tpimg;
    Image eventImage;
    Image fuelAdderImg;
    int eventX;
    int eventY;
    int fuelX;
    int fuelY;
    int eventVX;
    int eventVY;
    int eventPositionX = 600;
    int skySunRiseCordinateStart = LAYER1_CONST;
    int skyMorinigCordinateStart = LAYER2_CONST;
    int skyDayCordinateStart = LAYER3_CONST;
    int skySunSetCordinateStart = LAYER4_CONST;
    int skyNightCordinateStart = LAYER5_CONST;
    int vX;
    int vY;
    int x;
    int y;
    int fuel;
    int s;
    int travelDistance;
    int distanceToEvent;
    boolean planeIsDown;
    boolean isPlaneRise;
    boolean isEvent;
    boolean isEventDone;
    boolean messageFuelNotShowed;
    boolean message2FuelNotShowed;
    boolean addingFuel;
    boolean toAirport;
    int timeFueling;

    

    public Plane(){
        tpimg = new ImageIcon("planesModel\\img\\transportPlane.png").getImage();
        eventImage = new ImageIcon("planesModel\\img\\desant.png").getImage();
        fuelAdderImg = new ImageIcon("planesModel\\img\\fuelAdder.png").getImage();
        fuel = random.nextInt(5000) + 8000;
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
        distanceToEvent = random.nextInt(1000) + 2000;
        timeFueling = 50;
        travelDistance = distanceToEvent * 2;
        planeIsDown = false;
        isPlaneRise = true;
        isEvent = false;
        isEventDone = false;
        toAirport = false;
        messageFuelNotShowed = true;
        message2FuelNotShowed = true;
        addingFuel = false;
    }

    public void downToAirport(){
        planeIsDown = true;
        toAirport = true;
    }

    public void addFuel(){
        if (y < STANDART_Y + 200 && timeFueling > 0){
            y += vY;
        }
        if (fuelX > STANDART_X - 550){
            fuelX -= vX;
        }
        else{
            if(timeFueling > 0){
                this.fuel += 150;
                timeFueling -= 1;
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

    public void planeRize(){
        if (y > STANDART_Y) {
            y -= vY;
            if (x > STANDART_X){
                x -= vX;
            }
        }
        if (y == STANDART_Y){
            isPlaneRise = false;
        }
        fuel -= vX;
        s += vX;
        moveLayers();
    }

    public void moveLayers(){
        if (skyNightCordinateStart + vX >= 0){
            skySunRiseCordinateStart = LAYER1_CONST;
            skyMorinigCordinateStart = LAYER2_CONST;
            skyDayCordinateStart = LAYER3_CONST;
            skySunSetCordinateStart = LAYER4_CONST;
            skyNightCordinateStart = LAYER5_CONST;
        }
        else {
            skySunRiseCordinateStart += vX;
            skyMorinigCordinateStart += vX;
            skyDayCordinateStart += vX;
            skySunSetCordinateStart += vX;
            skyNightCordinateStart += vX;
        }
    }

    public void move(){
        if (travelDistance >= fuel + 1500 && messageFuelNotShowed){
            JOptionPane.showMessageDialog(null, "Потребуется дозаправка в воздухе");
            messageFuelNotShowed = false;
        }

        if (fuel < 2000  && travelDistance > 1000 && !planeIsDown && message2FuelNotShowed){
            JOptionPane.showMessageDialog(null, "Мало топлива! Вызовите дозаправщик");
            message2FuelNotShowed = false;
        }

        if (s >= distanceToEvent && !isEventDone && !addingFuel){
            isEvent = true;
        }

        if (fuel <= 0 && !planeIsDown){
            JOptionPane.showMessageDialog(null, "fuel is end");
            planeIsDown = true;
        }
        if(planeIsDown){
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
        moveLayers();
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
                isEvent = false;
                distanceToEvent = 0;
                isEventDone = true;
            }
        }
        fuel -= vX;
        s += vX;
        if (fuel < 0){
            fuel = 0;
        }
        moveLayers();
    }
}
