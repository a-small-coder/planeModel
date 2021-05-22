import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.Random;

public class MainApp {

    Plane plane;
    JPanel GroupPanel;
    JLabel planesLabel;
    JComboBox planeTypes;
    JLabel spaceToEventStartInfoLabel;
    JButton startEventButton;
    JLabel fuelInfoLabel;
    JButton addFuelbutton;
    JButton restart;

    public void createGUI(JFrame f){
        GroupPanel = new JPanel();
        GroupPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        planesLabel = new JLabel("выбор самолета: ");
        GroupPanel.add(planesLabel);
        planeTypes = new JComboBox();
        planeTypes.setPreferredSize(new Dimension(150, 30)); 
        planeTypes.addItem("1");
        planeTypes.addItem("2");
        planeTypes.addItem("3");
        GroupPanel.add(planeTypes);

        spaceToEventStartInfoLabel = new JLabel("до точки события осталось: ");
        GroupPanel.add(spaceToEventStartInfoLabel);
        startEventButton = new JButton("запустить событие");
        startEventButton.setPreferredSize(new Dimension(215, 30)); 
        ButtonHandler startEventHandler = new ButtonHandler();
        startEventButton.addActionListener(startEventHandler);
        GroupPanel.add(startEventButton);

        fuelInfoLabel = new JLabel("Топливо осталось: ");
        GroupPanel.add(fuelInfoLabel);
        addFuelbutton = new JButton("вызвать заправщик");
        ButtonHandler addFuelHundler = new ButtonHandler();
        addFuelbutton.addActionListener(addFuelHundler);
        addFuelbutton.setPreferredSize(new Dimension(215, 30));   
        GroupPanel.add(addFuelbutton);
        

        restart = new JButton("новый самолет");
        restart.setPreferredSize(new Dimension(215, 30)); 
        ButtonHandler restartHundler = new ButtonHandler();
        restart.addActionListener(restartHundler);
        GroupPanel.add(restart);

        f.add(GroupPanel, BorderLayout.NORTH);
        f.add(new Sky(), BorderLayout.CENTER);
        f.setVisible(true);
    }


    public JFrame createFrame(){
        JFrame f = new JFrame();
        createGUI(f);
        f.setTitle("моделирование полета самолета");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        f.setBounds(100, 100, (int) dim.getWidth(), (int) dim.getHeight());
        f.setLocationRelativeTo(null);
        return f;
    }

    class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("вызвать заправщик")) {
                plane.addFuel();
                System.out.println(plane.fuel);
            }
		}	
	} 
      

    public static void main(String args[]){
        MainApp app = new MainApp();
        app.createFrame();
    }

    

}