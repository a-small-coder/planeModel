import javax.swing.*;
import java.awt.BorderLayout;

public class MainApp {


    public JFrame createFrame(){
        JFrame f = new JFrame();
        f.setTitle("моделирование полета самолета");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setBounds(100, 100, 1500, 900);
        f.setLocationRelativeTo(null);
        return f;
    }

    
      

    public static void main(String args[]){
        JFrame f = new JFrame();
        f.setTitle("моделирование полета самолета");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(true);
        f.setBounds(100, 100, 1600, 900);
        f.setLocationRelativeTo(null);
        f.add(new Sky(f), BorderLayout.CENTER);
        f.setVisible(true);

    }    

}