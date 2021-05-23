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
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        JFrame f = new JFrame();
        f.setTitle("моделирование полета самолета");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(true);
        f.setBounds(100, 100, 1500, 800);
        f.setLocationRelativeTo(null);
        f.add(new Sky(f), BorderLayout.CENTER);
        f.setIconImage(new ImageIcon("planeModel\\img\\planeIcon.png").getImage());
        f.setVisible(true);

    }    

}