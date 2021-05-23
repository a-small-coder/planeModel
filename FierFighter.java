import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class FierFighter extends Plane{
    private final Random random = new Random();
    private boolean eventAndFuel;
    public FierFighter(int timeFactor){
        super(timeFactor);
        Image tpimg = new ImageIcon("planeModel\\img\\fierFighter.png").getImage();
        Image eventImage = new ImageIcon("planeModel\\img\\whater.png").getImage();
        Image fuelAdderImg = new ImageIcon("planeModel\\img\\fuelAdder.png").getImage();
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
        // установка флага окончания дозаправки (этот тип самолета нельзя заправить)
        set_addingFuelFlag(true);
    }

    @Override
    public void planeEvent(){
        
        // путь к точке события
        if(get_X() >= get_eventPositionX() && get_eventY() < 800){
            set_x(get_X() - get_vX()*2);
        }
        else{
            // если событие началось в момент дозаправки, то меняем стартовое значение изображения собития
            if (isAddingFuel() && isEvent() && !eventAndFuel){
                set_eventX(get_X() + 215);
                set_eventY(get_Y());
                eventAndFuel = true;
            }
            // движение изображения события
            set_eventX(get_eventX() + get_eventVX());
            set_eventY(get_eventY() + get_eventVY());
        }
        // если изображение события ушло за пределы экрана (событие завершилось)
        if (get_eventY() > 800){
            // возврат самолета к исходной позиции
            if(get_X() < STANDART_X){
                set_x(get_X() +get_vX()*2);
            }
            else{
                // когда самолет вернулся, установка флагов завершения собыия
                set_EventFlag(false);
                set_distanceToEvent(0);
                set_EventDoneFlag(true);
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

    // посадка самолета
    @Override
    public void downToAirport(){
        set_planeDownFlag(true);
        set_toAirport(true);
    }

    @Override
    public boolean MayChangeMap(){
        return false;
    }
    @Override
    public int get_mapNumber(){
        return 0;
    }
}
