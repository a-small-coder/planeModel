import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class BomberPlane extends Plane{
    private final Random random = new Random();
    private boolean eventAndFuel;
    public BomberPlane(int timeFactor){
        super(timeFactor);
        Image tpimg = new ImageIcon("planeModel\\img\\bomber.png").getImage();
        Image eventImage = new ImageIcon("planeModel\\img\\bombs.png").getImage();
        Image fuelAdderImg = new ImageIcon("planeModel\\img\\fuelAdder.png").getImage();
        Image[] images = {tpimg, eventImage, fuelAdderImg};
        set_Images(images);
        int distanceEvent = random.nextInt(3000) + 2000;
        set_distanceToEvent(distanceEvent);
        set_timeFueling(60);
        add_fuel(random.nextInt(4000) + 4000);
        set_fuelX(STANDART_X + 400);
        set_fuelY(STANDART_Y);
        set_travelDistance(distanceEvent * 2 + 1500);
        eventAndFuel = false;
}
    
    @Override
    public void addFuel(){
        // смещение самолета на 200 пикселей вниз
        if (get_Y() < STANDART_Y + 200 && get_timeFueling() > 0){
            set_y(get_Y() + get_vY()*2);
        }
        // вылет дозаправщика
        if (get_fuelX() > STANDART_X - 550){
            set_fuelX(get_fuelX() - get_vX()*2);
        }
        else{
            // заправка
            if(get_timeFueling() > 0){
                add_fuel(150);
                add_timeFueling(-1);
            }
            else{
                // дозапрвщик улетает
                if (get_fuelY() > STANDART_Y - 400){
                    set_fuelY(get_fuelY() - get_vY());
                }
                // возварт самолета на исходную позицию
                else if (get_Y() > STANDART_Y){
                    set_y(get_Y() - get_vY());
                }
                // установка флага окончания дозапрвки
                else{
                    set_addingFuelFlag(false);
                }
            }
        }
    }
    
    @Override
    public void planeEvent(){
        
        // путь к точке события
        if(get_X() >= get_eventPositionX() && get_eventY() < 700){
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
        if (get_eventY() > 700){
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
