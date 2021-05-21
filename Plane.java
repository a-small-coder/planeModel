import java.awt.*;
import java.util.Random;

import javax.swing.*;

public class Plane {
    Image tpimg = new ImageIcon("planeModel\\img\\transportPlane.png").getImage();

    final int LAYER1_CONST = 0;
    final int LAYER2_CONST = -8936;
    final int LAYER3_CONST = -17872;
    final int LAYER4_CONST = -20000;
    final int LAYER5_CONST = -28800;
    int skySunRiseCordinateStart = LAYER1_CONST;
    int skyMorinigCordinateStart = LAYER2_CONST;
    int skyDayCordinateStart = LAYER3_CONST;
    int skySunSetCordinateStart = LAYER4_CONST;
    int skyNightCordinateStart = LAYER5_CONST;
    int v = 100;
    int x = 1000;
    int y = 100;
    
    final Random random = new Random();
    int fuel = random.nextInt(10000) + 10000;
    int s = 0;
    boolean planeIsDown = false;

    public void move(){
        
        if (fuel < s && !planeIsDown){
            JOptionPane.showMessageDialog(null, "fuel is end");
            planeIsDown = true;
        }
        if(planeIsDown){
            if ( y < 1000){
                y += v/10;
                s += v;
            }
        }
        else{
            s += v;
        }
        if (skyNightCordinateStart + v >= 0){
            skySunRiseCordinateStart = LAYER1_CONST;
            skyMorinigCordinateStart = LAYER2_CONST;
            skyDayCordinateStart = LAYER3_CONST;
            skySunSetCordinateStart = LAYER4_CONST;
            skyNightCordinateStart = LAYER5_CONST;
        }
        else {
            skySunRiseCordinateStart += v;
            skyMorinigCordinateStart += v;
            skyDayCordinateStart += v;
            skySunSetCordinateStart += v;
            skyNightCordinateStart += v;
        }
        // System.out.println(layer1);
        // System.out.println(layer2);
        // System.out.println();
    }
}
