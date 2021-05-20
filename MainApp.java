import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;



public class MainApp{

    public static void main(String args[]){
        JFrame f = new JFrame();
        f.setTitle("моделирование полета самолета");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setSize(1572, 900);
        // f.setLocation(100, 100);
        // f.setLayout(new FlowLayout());
        
        // GroupsPanelFactory gPanelFactory = new GroupsPanelFactory();
        // f.add(gPanelFactory.create("будильник")); // настройки будильника
        // f.add(gPanelFactory.create("Тип будильника"));// тип будильника
        // f.add(gPanelFactory.create("погода"));// настройки погоды
        // f.add(gPanelFactory.create("управление временем"));; // управление временем
        f.add(new Sky(), BorderLayout.CENTER);
        f.setVisible(true);
        
        
    }

    

}