import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.event.*;
import java.util.Random;

public class Sky extends JPanel implements ActionListener{

    JFrame frame;
    JPanel GroupPanel;
    JLabel planesLabel;
    JComboBox<String> planeTypes;
    JLabel spaceToEventStartInfoLabel;
    JButton startEventButton;
    JLabel fuelInfoLabel;
    JLabel fuelValueLabel;
    JLabel spaceToEventStartValueLabel;
    JLabel distanceLostInfoLabel;
    JLabel distanceLostValueLabel;
    JButton addFuelbutton;
    JButton restart;
    JSlider timeSlider;

    final Random random = new Random();
    final int timeFCommon = 1;
    public final String dirName = "planeModel\\img\\";
    Image skySunRizeImg = new ImageIcon(dirName + "skySunRise.jpg").getImage();
    Image skyMorningImg = new ImageIcon(dirName + "skyMorning.jpg").getImage();
    Image skyDayImg = new ImageIcon(dirName + "skyBig.jpg").getImage();
    Image skySunSetImg = new ImageIcon(dirName + "skySunset.jpg").getImage();
    Image skyNightImg = new ImageIcon(dirName + "skyNight.jpg").getImage();
    Image map1 = new ImageIcon(dirName + "map1.png").getImage();
    Image map2 = new ImageIcon(dirName + "map2.png").getImage();
    Image map3 = new ImageIcon(dirName + "map3.png").getImage();
    Image map4 = new ImageIcon(dirName + "map4.png").getImage();
    Image map5 = new ImageIcon(dirName + "map5.png").getImage();
    Image map6 = new ImageIcon(dirName + "map6.png").getImage();
    Image map7 = new ImageIcon(dirName + "map7.png").getImage();
    Image map8 = new ImageIcon(dirName + "map8.png").getImage();
    Image[] eventMap = {map1, map2, map3, map4, map5, map6, map7, map8};

    Image skyImg = skySunRizeImg;
    Image[] skyes = {skySunRizeImg, skyMorningImg, skyDayImg, skySunSetImg, skyNightImg};
    Timer mainTimer = new Timer(20, this);

    private int startY = 0;
    private int i = 0;

    Plane plane;

    public void createPlane(int type, int timeF){
        switch (type){
            case 0: this.plane = new TransportPlane(timeF); break;
            case 1: this.plane = new BomberPlane(timeF); break;
            case 2: this.plane = new FierFighter(timeF); break;
            case 3: this.plane = new ScouterPlane(timeF); break;
            default: break;
        }
        
    }

    public Sky(JFrame frame){
        this.frame = frame;
        createGUI(frame);
        int t = random.nextInt(4); 
        planeTypes.setSelectedIndex(t);
		mainTimer.start();
    }
    @Override
    public void paint(Graphics g){
        g = (Graphics2D) g;
        g.drawImage(skyes[0], plane.skySunRiseCordinateStart, startY, null);
        g.drawImage(skyes[1], plane.skyDayCordinateStart, startY, null);
        g.drawImage(skyes[2], plane.skyMorinigCordinateStart, startY, null);
        g.drawImage(skyes[3], plane.skySunSetCordinateStart, startY, null);
        g.drawImage(skyes[4], plane.skyNightCordinateStart, startY, null);
        Image[] images = plane.get_Images();
        Image planeImg = images[0];
        Image eventImg = images[1];
        Image fuelAdderImg = images[2];
        if (plane.get_Y() < 1000){
            g.drawImage(planeImg, plane.get_X(), plane.get_Y(), null);
        }
        if (plane.isEvent() && plane.get_X() <= plane.get_eventPositionX()){
            if (plane instanceof ScouterPlane){
                if (plane.MayChangeMap()){
                    i = plane.get_mapNumber();
                }
                
                g.drawImage(eventMap[i], plane.get_eventX(), plane.get_eventY(), null);
            }
            else{
                g.drawImage(eventImg, plane.get_eventX(), plane.get_eventY(), null);
            }
        }
        if (plane.isAddingFuel()){
            g.drawImage(fuelAdderImg, plane.get_fuelX(), plane.get_fuelY(), null);
        }
        
    }

    public void getInfomationOfPlane(Plane p){
        System.out.print("Событие идет: ");
        System.out.println(p.isEvent());
        System.out.print("Событие завершилось: ");
        System.out.println(p.isEventDone());
        System.out.print("Самолет взлетает: ");
        System.out.println(p.isPlaneRise());
        System.out.print("Самолет падает: ");
        System.out.println(p.isPlaneDown());
        System.out.print("Топливо: ");
        System.out.println(p.get_Fuel());
        System.out.print("Пройденный путь: ");
        System.out.println(p.get_s());
        System.out.print("Путь до события: ");
        System.out.println(p.get_distanceToEvent());
        System.out.print("Кордината Х картинки события: ");
        System.out.println(p.get_eventX());
        System.out.print("Кордината Y картинки события: ");
        System.out.println(p.get_eventY());
        System.out.print("Кордината Х самолета: ");
        System.out.println(p.get_X());
        System.out.print("Кордината Y самолета: ");
        System.out.println(p.get_Y());
        System.out.println("\n");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (plane.isPlaneRise()){
            plane.planeRize();
        }
        else if (plane.isEvent()){
            plane.planeEvent();
        }
        else if (plane.isAddingFuel()){
            plane.addFuel();
            plane.move();
        } 
        else{
            plane.move();
        }
        
        // изменение подписей при посадке\падении
        if (!plane.isPlaneDown()){
            int b = plane.get_travelDistance() - plane.get_s();
            if (b < 0){
                b = 0;
                if (plane.isEventDone()){
                    plane.downToAirport();
                }
            }
            distanceLostInfoLabel.setText("Пролететь осталось: " + Integer.toString(b) + "км");
        }
        else{
            if (plane.get_travelDistance() - plane.get_s() <= 0){
                distanceLostInfoLabel.setText("Самолет сел");
            }
            else{
                distanceLostInfoLabel.setText("Самолет разбился");
            }
            distanceLostInfoLabel.setText("Пролететь осталось: 0 км");
        }

        // отключение кнопок
        if (plane.isEvent() || plane.isEventDone() || plane.isPlaneRise() || plane.isPlaneDown()){
            startEventButton.setEnabled(false);
            if (plane.isEventDone() && !plane.isPlaneDown()){
                addFuelbutton.setEnabled(true);
            }
            else{
                addFuelbutton.setEnabled(false);
            }
        }
        else{
            startEventButton.setEnabled(true);
            addFuelbutton.setEnabled(true);
        }

        // изменение подписей при событии
        if (!plane.isEventDone()){
            int a = plane.get_distanceToEvent() - plane.get_s();
            if (a < 0){
                a = 0;
            }
            spaceToEventStartInfoLabel.setText("До точки события осталось: " + Integer.toString(a) + "км");
        }
        else{
            spaceToEventStartInfoLabel.setText("Событие произошло");
            spaceToEventStartInfoLabel.setText("До точки события осталось: 0км");
        }
        fuelInfoLabel.setText("Топливо осталось: " + Integer.toString(plane.get_Fuel()) + "км");

        repaint();
        //getInfomationOfPlane(tPlane);
    }

    public void createGUI(JFrame f){
        GroupPanel = new JPanel();
        GroupPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 30, 30));

        JPanel createPlane = new JPanel(new GridLayout(3, 1, 15, 5));
        planesLabel = new JLabel("Выбор самолета: ");
        createPlane.add(planesLabel);
        planeTypes = new JComboBox<String>();
        planeTypes.setPreferredSize(new Dimension(150, 30)); 
        planeTypes.addItem("десантный");
        planeTypes.addItem("бомбардировщик");
        planeTypes.addItem("пожарный");
        planeTypes.addItem("разведчик");
        ButtonHandler planeChooser = new ButtonHandler();
        planeTypes.addActionListener(planeChooser);
        createPlane.add(planeTypes);
        restart = new JButton("новый самолет");
        restart.setPreferredSize(new Dimension(215, 30)); 
        ButtonHandler restartHundler = new ButtonHandler();
        restart.addActionListener(restartHundler);
        createPlane.add(restart);
        GroupPanel.add(createPlane);

        JLabel spaceBlock1 = new JLabel("");
        spaceBlock1.setPreferredSize(new Dimension(100, 30));
        GroupPanel.add(spaceBlock1);

        JPanel eventPanel = new JPanel(new GridLayout(3, 1, 0, 5));
        JLabel eventPanelInfo = new JLabel(" Событие ");
        eventPanel.add(eventPanelInfo);
        spaceToEventStartInfoLabel = new JLabel("До точки события осталось: ");
        eventPanel.add(spaceToEventStartInfoLabel);
        startEventButton = new JButton("запустить событие");
        startEventButton.setPreferredSize(new Dimension(215, 30)); 
        ButtonHandler startEventHandler = new ButtonHandler();
        startEventButton.addActionListener(startEventHandler);
        eventPanel.add(startEventButton);
        GroupPanel.add(eventPanel);

        JLabel spaceBlock2 = new JLabel("");
        spaceBlock2.setPreferredSize(new Dimension(100, 30));
        GroupPanel.add(spaceBlock2);

        JPanel fuelAndDistanceP = new JPanel(new GridLayout(3, 1, 0, 5));
        fuelInfoLabel = new JLabel("Топливо осталось: ");
        fuelAndDistanceP.add(fuelInfoLabel);
        distanceLostInfoLabel = new JLabel("Пролететь осталось: ");
        fuelAndDistanceP.add(distanceLostInfoLabel);

        addFuelbutton = new JButton("вызвать заправщик");
        ButtonHandler addFuelHundler = new ButtonHandler();
        addFuelbutton.addActionListener(addFuelHundler);
        addFuelbutton.setPreferredSize(new Dimension(215, 30)); 
        fuelAndDistanceP.add(addFuelbutton);
        GroupPanel.add(fuelAndDistanceP);
        
        JLabel spaceBlock3 = new JLabel("");
        spaceBlock3.setPreferredSize(new Dimension(100, 30));
        GroupPanel.add(spaceBlock3);

        JPanel timePanel = new JPanel(new GridLayout(2, 1, 0, 10));
        JLabel timeInfoLabel = new JLabel("Управление временем: ");
        timePanel.add(timeInfoLabel);
        timeSlider = new JSlider(0, 10, 1);
        timeSlider.setMajorTickSpacing(5);
        timeSlider.setPaintLabels(true);
        timeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int tF = ((JSlider)e.getSource()).getValue();
                plane.set_timeFactor(tF);
                plane.changeTimeSpeed();
            }
         });
         timePanel.add(timeSlider);
         GroupPanel.add(timePanel);

        f.add(GroupPanel, BorderLayout.NORTH);
    }

    class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("вызвать заправщик")) {
                if (plane.get_timeFueling() == 0){
                    JOptionPane.showMessageDialog(null, "Можно вызвать не более одного дозаправщика");
                }
                else if (plane instanceof FierFighter || plane instanceof ScouterPlane){
                    JOptionPane.showMessageDialog(null, "Дозаправка для даного \nтипа самолета невозможна");
                }
                else if (plane.get_travelDistance() - plane.get_s() < 1000){
                    JOptionPane.showMessageDialog(null, "Начинается посадка. Дозаправка невозможна");
                }
                else{
                    plane.set_addingFuelFlag(true);
                }
            }
            else if(command.equals("новый самолет")){
                int t = random.nextInt(4); 
                planeTypes.setSelectedIndex(t);
            }
            else if(command.equals("запустить событие")){
                plane.set_EventFlag(true);
                plane.set_distanceToEvent(plane.get_s());
            }
            else if (e.getSource() instanceof JComboBox) {
                JComboBox<String> mySource = (JComboBox) e.getSource();
                int index = mySource.getSelectedIndex();
                if (timeSlider.getValue() != timeFCommon);
                createPlane(index, timeSlider.getValue());
            }
		}	
	} 

}
