import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Sky extends JPanel implements ActionListener{
    Image skyDayImg = new ImageIcon("planesModel/img/skyBig.jpg").getImage();
    Image skyNightImg = new ImageIcon("planesModel/img/skyNight.jpg").getImage();

    Image skyImg = skyNightImg;
    Timer mainTimer = new Timer(20, this);

    private int startY = 0;
    private int startX = 0;

    Plane tPlane = new Plane();

    public Sky(){
        mainTimer.start();
    }
    public void paint(Graphics g){
        g = (Graphics2D) g;
        g.drawImage(skyImg, tPlane.layer1, startY, null);
        g.drawImage(skyImg, tPlane.layer2, startY, null);
        g.drawImage(tPlane.tpimg, tPlane.x, tPlane.y, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tPlane.move();
        repaint();
    }

}
