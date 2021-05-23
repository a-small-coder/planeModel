import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class TransportPlane extends Plane{
    private final Random random = new Random();
    // private boolean eventAndFuel; // фалг необходимый для учета координат изображения события при одновременной дозаправке
    private int desantCounter;
    private int eventPositionY;
    private boolean desantCounterChange;
    public TransportPlane(int timeFactor){
        super(timeFactor);
        Image tpimg = new ImageIcon("planesModel\\img\\transportPlane.png").getImage();
        Image eventImage = new ImageIcon("planesModel\\img\\desant.png").getImage();
        Image fuelAdderImg = new ImageIcon("planesModel\\img\\fuelAdder.png").getImage();
        Image[] images = {tpimg, eventImage, fuelAdderImg};
        set_Images(images);
        int distanceEvent = random.nextInt(3000) + 1000;
        set_distanceToEvent(distanceEvent);
        set_timeFueling(30);
        add_fuel(random.nextInt(5000) + 4000);
        set_fuelX(STANDART_X + 400);
        set_fuelY(STANDART_Y);
        set_travelDistance(distanceEvent * 2 + 1000);
        // eventAndFuel = false;
        desantCounter = 3;
        eventPositionY = 400;
        desantCounterChange = false;

}
    // дозапрвка
    @Override
    public void addFuel(){
        // смещение самолета на 200 пикселей вниз
        if (get_Y() < STANDART_Y + 200 && get_timeFueling() > 0){
            set_y(get_Y() + get_vY());
        }
        // вылет дозаправщика
        if (get_fuelX() > STANDART_X - 550){
            set_fuelX(get_fuelX() - get_vX());
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

    // выполнение события
    @Override
    public void planeEvent(){
        // путь к точке события
        if((get_X() >= get_eventPositionX() || get_Y() <= eventPositionY) && get_eventY() < 1000 && desantCounter > 0){
            if (get_X() >= get_eventPositionX()){
                set_x(get_X() - get_vX());
            }
            if (get_Y() <= eventPositionY){
                set_y(get_Y() + get_vY());
            }
            
        }
        else{
            // если событие началось, то меняем стартовое значение изображения собития
            if (!desantCounterChange){
                set_eventX(get_X() + 215);
                set_eventY(get_Y());
                desantCounterChange = true;
                desantCounter -= 1;
            }
            // движение изображения события
            set_eventX(get_eventX() + get_eventVX());
            set_eventY(get_eventY() + get_eventVY());
        }
        // если изображение дошло до точки повтора события или его завершения
        if (get_eventY() > 800){
            if (desantCounter > 0){
                set_eventX(get_X() + 215);
                set_eventY(get_Y());
                desantCounter -= 1;
            }
            else{
                    // возврат самолета к исходной позиции
                if (isAddingFuel()){
                    if(get_X() < STANDART_X || get_Y() > STANDART_Y + 200){
                        if (get_X() < STANDART_X){
                            set_x(get_X() + get_vX());
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
                            set_x(get_X() + get_vX());
                        }
                        if (get_Y() > STANDART_Y){
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

}
