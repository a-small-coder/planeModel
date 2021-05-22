import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.Random;

public class MainApp {


    public JFrame createFrame(){
        JFrame f = new JFrame();
        f.setTitle("моделирование полета самолета");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        f.setBounds(100, 100, (int) dim.getWidth(), (int) dim.getHeight());
        f.setLocationRelativeTo(null);
        return f;
    }

    
      

    public static void main(String args[]){
        JFrame f = new JFrame();
        f.setTitle("моделирование полета самолета");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        f.setBounds(100, 100, (int) dim.getWidth(), (int) dim.getHeight());
        f.setLocationRelativeTo(null);
        f.add(new Sky(f), BorderLayout.CENTER);
        f.setVisible(true);

    }    

}