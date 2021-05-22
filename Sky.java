import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class Sky extends JPanel implements ActionListener{

    JFrame frame;
    JPanel GroupPanel;
    JLabel planesLabel;
    JComboBox planeTypes;
    JLabel spaceToEventStartInfoLabel;
    JButton startEventButton;
    JLabel fuelInfoLabel;
    JLabel fuelValueLabel;
    JLabel spaceToEventStartValueLabel;
    JLabel distanceLostInfoLabel;
    JLabel distanceLostValueLabel;
    JButton addFuelbutton;
    JButton restart;

    final Random random = new Random();
    public final String dirName = "planesModel\\img\\";
    Image skySunRizeImg = new ImageIcon(dirName + "skySunRise.jpg").getImage();
    Image skyMorningImg = new ImageIcon(dirName + "skyMorning.jpg").getImage();
    Image skyDayImg = new ImageIcon(dirName + "skyBig.jpg").getImage();
    Image skySunSetImg = new ImageIcon(dirName + "skySunset.jpg").getImage();
    Image skyNightImg = new ImageIcon(dirName + "skyNight.jpg").getImage();

    Image skyImg = skySunRizeImg;
    Image[] skyes = {skySunRizeImg, skyMorningImg, skyDayImg, skySunSetImg, skyNightImg};
    Timer mainTimer = new Timer(20, this);

    private int startY = 0;

    Plane plane;

    public void createPlane(int type){
        switch (type){
            case 0: this.plane = new Plane(0); break;
            case 1: this.plane = new Plane(1); break;
            case 2: this.plane = new Plane(2); break;
            default: break;
        }
        
    }

    public Sky(JFrame frame){
        this.frame = frame;
        createGUI(frame);
        int t = random.nextInt(3); 
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
        if (plane.y < 1000){
            g.drawImage(plane.tpimg, plane.x, plane.y, null);
        }
        if (plane.isEvent && plane.x <= plane.eventPositionX){
            g.drawImage(plane.eventImage, plane.eventX, plane.eventY, null);
        }
        if (plane.addingFuel){
            g.drawImage(plane.fuelAdderImg, plane.fuelX, plane.fuelY, null);
        }
        
    }

    public void getInfomationOfPlane(Plane p){
        System.out.print("Событие идет: ");
        System.out.println(p.isEvent);
        System.out.print("Событие завершилось: ");
        System.out.println(p.isEventDone);
        System.out.print("Самолет взлетает: ");
        System.out.println(p.isPlaneRise);
        System.out.print("Самолет падает: ");
        System.out.println(p.planeIsDown);
        System.out.print("Топливо: ");
        System.out.println(p.fuel);
        System.out.print("Пройденный путь: ");
        System.out.println(p.s);
        System.out.print("Путь до события: ");
        System.out.println(p.distanceToEvent);
        System.out.print("Кордината Х картинки события: ");
        System.out.println(p.eventX);
        System.out.print("Кордината Y картинки события: ");
        System.out.println(p.eventY);
        System.out.print("Кордината Х самолета: ");
        System.out.println(p.x);
        System.out.print("Кордината Y самолета: ");
        System.out.println(p.y);
        System.out.println("\n");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (plane.isPlaneRise){
            plane.planeRize();
        }
        else if (plane.isEvent){
            plane.planeEvent();
        }
        else if (plane.addingFuel){
            plane.addFuel();
            plane.move();
        } 
        else{
            plane.move();
        }
        
        // изменение подписей при посадке\падении
        if (!plane.planeIsDown){
            int b = plane.travelDistance - plane.s;
            if (b < 0){
                b = 0;
                if (plane.isEventDone){
                    plane.downToAirport();
                }
            }
            distanceLostInfoLabel.setText("Пролететь осталось: ");
            distanceLostValueLabel.setText(" " + Integer.toString(b) + "км ");
        }
        else{
            if (plane.travelDistance - plane.s <= 0){
                distanceLostInfoLabel.setText("Самолет сел");
            }
            else{
                distanceLostInfoLabel.setText("Самолет упал");
            }
            distanceLostValueLabel.setText("");
        }

        // отключение кнопок
        if (plane.isEvent || plane.isEventDone || plane.isPlaneRise || plane.planeIsDown){
            startEventButton.setEnabled(false);
            if (plane.isEventDone && !plane.planeIsDown){
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
        if (!plane.isEventDone){
            int a = plane.distanceToEvent - plane.s;
            if (a < 0){
                a = 0;
            }
            spaceToEventStartInfoLabel.setText("до точки события осталось: ");
            spaceToEventStartValueLabel.setText(" " + Integer.toString(a) + "км ");
        }
        else{
            spaceToEventStartInfoLabel.setText("событие произошло");
            spaceToEventStartValueLabel.setText("");
        }
        fuelValueLabel.setText(" " + Integer.toString(plane.fuel) + "км ");

        repaint();
        //getInfomationOfPlane(tPlane);
    }

    public void createGUI(JFrame f){
        GroupPanel = new JPanel();
        GroupPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        planesLabel = new JLabel("выбор самолета: ");
        GroupPanel.add(planesLabel);
        planeTypes = new JComboBox();
        planeTypes.setPreferredSize(new Dimension(150, 30)); 
        planeTypes.addItem("десантный");
        planeTypes.addItem("бомбардировщик");
        planeTypes.addItem("пожарный");
        ButtonHandler planeChooser = new ButtonHandler();
        planeTypes.addActionListener(planeChooser);
        GroupPanel.add(planeTypes);

        spaceToEventStartInfoLabel = new JLabel("до точки события осталось: ");
        GroupPanel.add(spaceToEventStartInfoLabel);
        spaceToEventStartValueLabel = new JLabel("км");
        GroupPanel.add(spaceToEventStartValueLabel);
        startEventButton = new JButton("запустить событие");
        startEventButton.setPreferredSize(new Dimension(215, 30)); 
        ButtonHandler startEventHandler = new ButtonHandler();
        startEventButton.addActionListener(startEventHandler);
        GroupPanel.add(startEventButton);

        JPanel fuelAndDistanceP = new JPanel(new GridLayout(2, 2, 15, 15));
        fuelInfoLabel = new JLabel("Топливо осталось: ");
        fuelAndDistanceP.add(fuelInfoLabel);
        fuelValueLabel = new JLabel("км");
        fuelAndDistanceP.add(fuelValueLabel);
        distanceLostInfoLabel = new JLabel("Пролететь осталось: ");
        fuelAndDistanceP.add(distanceLostInfoLabel);
        distanceLostValueLabel = new JLabel("км");
        fuelAndDistanceP.add(distanceLostValueLabel);
        GroupPanel.add(fuelAndDistanceP);

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
    }

    class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("вызвать заправщик")) {
                if (plane.timeFueling == 0){
                    JOptionPane.showMessageDialog(null, "Можно вызвать не более одного дозаправщика");
                }
                else if (plane.travelDistance - plane.s < 1000){
                    JOptionPane.showMessageDialog(null, "Начинается посадка. Дозаправка невозможна");
                }
                else{
                    plane.addingFuel = true;
                }
            }
            else if(command.equals("новый самолет")){
                int t = random.nextInt(3); 
                planeTypes.setSelectedIndex(t);
            }
            else if(command.equals("запустить событие")){
                plane.isEvent = true;
                plane.distanceToEvent = plane.s;
            }
            else if (e.getSource() instanceof JComboBox) {
                JComboBox mySource = (JComboBox) e.getSource();
                int index = mySource.getSelectedIndex();
                createPlane(index);
            }
		}	
	} 

}
