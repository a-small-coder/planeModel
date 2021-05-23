import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class ScouterPlane extends Plane{
    private final Random random = new Random();
    // private boolean eventAndFuel; // фалг необходимый для учета координат изображения события при одновременной дозаправке
    private int mapShowed;
    private boolean mapShowing;
    private int eventShowedTime = 250;
    private boolean changeMap;
    public ScouterPlane(int timeFactor){
        super(timeFactor);
        Image tpimg = new ImageIcon("planeModel\\img\\Scouter.png").getImage();
        Image eventImage = new ImageIcon("planeModel\\img\\desant.png").getImage();
        Image fuelAdderImg = new ImageIcon("planeModel\\img\\fuelAdder.png").getImage();
        Image[] images = {tpimg, eventImage, fuelAdderImg};
        set_Images(images);
        int distanceEvent = random.nextInt(1000) + 1000;
        set_distanceToEvent(distanceEvent);
        set_timeFueling(30);
        add_fuel(random.nextInt(300) + 5000);
        set_travelDistance(distanceEvent + 1000);
        mapShowed = 3;
        set_eventPositionX(600);
        set_eventPositionY(400);
        mapShowing = true;
        set_eventX(1375);
        set_eventY(500);
        set_STANDART_Y(300);
        
}

    @Override
    public void downToAirport() {
        set_planeDownFlag(true);
        set_toAirport(true);
        
    }

    @Override
    public void addFuel() {
        set_addingFuelFlag(true);
        
    }
    @Override
    public boolean MayChangeMap(){
        return changeMap;
    }
    @Override
    public int get_mapNumber(){
        return random.nextInt(7) + 1;
    }

    @Override
    public void planeEvent() {
        // путь к точке события
        if((get_X() >= get_eventPositionX() || get_Y() <= get_eventPositionY()) && get_eventY() < 1000 && mapShowed > 0){
            if (get_X() >= get_eventPositionX()){
                set_x(get_X() - get_vX()*2);
            }
            if (get_Y() <= get_eventPositionY()){
                set_y(get_Y() + get_vY());
            }
            
        }
        else{
            // если событие началось, то меняем стартовое значение изображения собития
            if (!mapShowing){
                mapShowing = true;
                mapShowed -= 1;
            }
            // движение изображения события
            eventShowedTime -= get_vX();
            if (MayChangeMap()){
                changeMap = false;
            }
        }
        // если изображение дошло до точки повтора события или его завершения
        if (eventShowedTime <= 0){
            if (mapShowed > 0){
                eventShowedTime = 250;
                mapShowed -= 1;
                changeMap = true;
            }
            else{
                    // возврат самолета к исходной позиции
                if (isAddingFuel()){
                    if(get_X() < STANDART_X || get_Y() > STANDART_Y + 200){
                        if (get_X() < STANDART_X){
                            set_x(get_X() + get_vX()*2);
                        }
                        if (get_Y() > STANDART_Y + 200){
                            set_y(get_Y() - get_vY());
                        }
                    }
                    else{
                        // когда самолет вернулся, установка флагов завершения собыия
                        set_EventFlag(false);
                        set_distanceToEvent(0);
                        set_EventDoneFlag(true);
                    }
                }
                else {
                    if(get_X() < STANDART_X || get_Y() > STANDART_Y){
                        if (get_X() < STANDART_X){
                            set_x(get_X() + get_vX()*2);
                        }
                        if (get_Y() > STANDART_Y){
                            set_y(get_Y() - get_vY()*2);
                        }
                    }
                    else{
                        // когда самолет вернулся, установка флагов завершения собыия
                        set_EventFlag(false);
                        set_distanceToEvent(0);
                        set_EventDoneFlag(true);
                    }
                }
                
            }
            
        }
        // учет топлива и пройденного расстояния при выполнении события
        add_fuel(-1 * get_vX());
        set_s(get_s() + get_vX());
        if (get_Fuel() < 0){
            // сброс показателей топлива до 0, если оно < 0
            add_fuel(0 - get_Fuel());
            set_EventFlag(false);
            set_distanceToEvent(0);
            set_EventDoneFlag(true);
            set_planeDownFlag(true);
        }
        // имитация движения самолета 
        moveLayers(get_vX());
        
    }
    
}
