import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Sky extends JPanel implements ActionListener{
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

    Plane tPlane = new Plane();

    

    public Sky(){
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
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tPlane.move();
        repaint();
    }


     
}
