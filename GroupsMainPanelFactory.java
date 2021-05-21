import javax.swing.*;
import java.awt.*;

public class GroupsMainPanelFactory{
    JPanel GroupPanel;
    public JPanel create(String typeOfPanel){
        if (typeOfPanel.equals("выбор самолета")) {
            GroupPanel = new JPanel(new GridLayout(0, 1, 15, 15));
            GroupPanel.setBorder(BorderFactory.createTitledBorder("выбор самолета"));
            GroupPanel.setPreferredSize(new Dimension(400, 100));

            JComboBox planeTypes = new JComboBox();
            planeTypes.setPreferredSize(new Dimension(200, 30));
            planeTypes.addItem("1");
            planeTypes.addItem("2");
            planeTypes.addItem("3");
            GroupPanel.add(planeTypes);

        }

        if (typeOfPanel.equals("погода")){
            GroupPanel = new JPanel(new GridLayout(0, 2, 15, 15));
            GroupPanel.setBorder(BorderFactory.createTitledBorder("погода"));
    
            JLabel falloutChanceL = new JLabel("вероятность осадков");
            GroupPanel.add(falloutChanceL);
    
            JComboBox falloutChanceCB = new JComboBox();
            falloutChanceCB.setSize(50, 30);
            falloutChanceCB.addItem("0%");
            falloutChanceCB.addItem("20%");
            falloutChanceCB.addItem("40%");
            falloutChanceCB.addItem("60%");
            falloutChanceCB.addItem("80%");
            falloutChanceCB.addItem("100%");
            GroupPanel.add(falloutChanceCB);
    
            JLabel daysTypeL = new JLabel("отношение ясных дней к пасмурным");
            GroupPanel.add(daysTypeL);
    
            JComboBox daysTypeCB = new JComboBox();
            daysTypeCB.setSize(50, 30);
            daysTypeCB.addItem("1:1");
            daysTypeCB.addItem("1:2");
            daysTypeCB.addItem("1:5");
            daysTypeCB.addItem("5:1");
            daysTypeCB.addItem("2:1");
            daysTypeCB.addItem("5:1");
            GroupPanel.add(daysTypeCB); 
        }

        if (typeOfPanel.equals("событие")){
            GroupPanel = new JPanel(new GridLayout(0, 1, 15, 15));
            GroupPanel.setBorder(BorderFactory.createTitledBorder("событие"));

            JLabel spaceToEventStartInfoLabel = new JLabel("до точки события осталось: ");
            // spaceToEventStartInfoLabel.setLocation(800, 125);
            GroupPanel.add(spaceToEventStartInfoLabel);

            
            JLabel spaceToEventStartLabel = new JLabel("");
            GroupPanel.add(spaceToEventStartLabel);

            JButton addFuelbutton = new JButton("запустить событие");
            // addFuelbutton.setLocation(1050, 175);
            addFuelbutton.setSize(215, 30); 

        }

        if (typeOfPanel.equals("Топливо")){
            GroupPanel = new JPanel(new GridLayout(0, 2, 15, 15));
            GroupPanel.setBorder(BorderFactory.createTitledBorder("Топливо"));

            JLabel fuelInfoLabel = new JLabel("Топливо осталось: ");
            GroupPanel.add(fuelInfoLabel);

            JLabel fuelLabel = new JLabel("");
            GroupPanel.add(fuelLabel);

            JButton addFuelbutton = new JButton("вызвать заправщик");
            // addFuelbutton.setLocation(1050, 175);
            addFuelbutton.setSize(215, 30);            
        }
        return GroupPanel;     
    }
}