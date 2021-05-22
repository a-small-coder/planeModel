import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

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
    private int startX = 0;

    Plane tPlane;

    public void createPlane(){
        this.tPlane = new Plane();
    }

    public Sky(JFrame frame){
        this.frame = frame;
        createGUI(frame);
        createPlane();
		mainTimer.start();
    }
    @Override
    public void paint(Graphics g){
        g = (Graphics2D) g;
        g.drawImage(skyes[0], tPlane.skySunRiseCordinateStart, startY, null);
        g.drawImage(skyes[1], tPlane.skyDayCordinateStart, startY, null);
        g.drawImage(skyes[2], tPlane.skyMorinigCordinateStart, startY, null);
        g.drawImage(skyes[3], tPlane.skySunSetCordinateStart, startY, null);
        g.drawImage(skyes[4], tPlane.skyNightCordinateStart, startY, null);
        if (tPlane.y < 1000){
            g.drawImage(tPlane.tpimg, tPlane.x, tPlane.y, null);
        }
        if (tPlane.isEvent && tPlane.x <= tPlane.eventPositionX){
            g.drawImage(tPlane.eventImage, tPlane.eventX, tPlane.eventY, null);
        }
        if (tPlane.addingFuel){
            g.drawImage(tPlane.fuelAdderImg, tPlane.fuelX, tPlane.fuelY, null);
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
        if (tPlane.isPlaneRise){
            tPlane.planeRize();
        }
        else if (tPlane.isEvent){
            tPlane.planeEvent();
        }
        else if (tPlane.addingFuel){
            tPlane.addFuel();
            tPlane.move();
        } 
        else{
            tPlane.move();
        }
        
        // изменение подписей при посадке\падении
        if (!tPlane.planeIsDown){
            int b = tPlane.travelDistance - tPlane.s;
            if (b < 0){
                b = 0;
                tPlane.downToAirport();
            }
            distanceLostValueLabel.setText(" " + Integer.toString(b) + "км ");
        }
        else{
            if (tPlane.travelDistance - tPlane.s <= 0){
                distanceLostInfoLabel.setText("Самолет сел");
            }
            else{
                distanceLostInfoLabel.setText("Самолет упал");
            }
            distanceLostValueLabel.setText("");
        }

        // отключение кнопок
        if (tPlane.isEvent || tPlane.isEventDone || tPlane.isPlaneRise || tPlane.planeIsDown){
            startEventButton.setEnabled(false);
            if (tPlane.isEventDone && !tPlane.planeIsDown){
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
        if (!tPlane.isEventDone){
            int a = tPlane.distanceToEvent - tPlane.s;
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
        fuelValueLabel.setText(" " + Integer.toString(tPlane.fuel) + "км ");

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
        planeTypes.addItem("1");
        planeTypes.addItem("2");
        planeTypes.addItem("3");
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
                if (tPlane.timeFueling == 0){
                    JOptionPane.showMessageDialog(null, "Можно вызвать не более одного дозаправщика");
                }
                else if (tPlane.travelDistance - tPlane.s < 1000){
                    JOptionPane.showMessageDialog(null, "Начинается посадка. Дозаправка невозможна");
                }
                else{
                    tPlane.addingFuel = true;
                }
            }
            else if(command.equals("новый самолет")){
                createPlane();
            }
            else if(command.equals("запустить событие")){
                tPlane.isEvent = true;
                tPlane.distanceToEvent = tPlane.s;
            }
		}	
	} 

}
