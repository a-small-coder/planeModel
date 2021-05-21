import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;



public class MainApp{

    public static void main(String args[]){
        JFrame f = new JFrame();
        f.setTitle("моделирование полета самолета");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        f.setBounds(100, 100, (int) dim.getWidth(), (int) dim.getHeight());
        f.setLocationRelativeTo(null);

        // f.setLayout(new FlowLayout());
        GroupsMainPanelFactory gPanelFactory = new GroupsMainPanelFactory();
        JPanel panel = new JPanel();
        
        panel.add(gPanelFactory.create("выбор самолета")); // настройки будильника
        panel.add(gPanelFactory.create("Топливо"));// тип будильника
        panel.add(gPanelFactory.create("событие"));// настройки погоды
        //panel.add(gPanelFactory.create("управление временем"));; // управление временем
        f.add(panel, BorderLayout.NORTH);
        f.add(new Sky(), BorderLayout.CENTER);
        f.setVisible(true);
        
        
    }

    

}