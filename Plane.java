import java.awt.*;
import javax.swing.*;

public class Plane {
    Image tpimg = new ImageIcon("planesModel/img/transportPlane.png").getImage();

    final int LAYER1_CONST = 0;
    final int LAYER2_CONST = -8957;
    int v = 100;
    int x = 1000;
    int y = 300;
    int layer1 = LAYER1_CONST;
    int layer2 = LAYER2_CONST;
    int fuel;
    int s = 0;

    public void move(){
        s += v;
        if (layer2 + v >= 0){
            layer1 = LAYER1_CONST;
            layer2 = LAYER2_CONST;
        }
        else {
            layer1 += v;
            layer2 += v;
        }
        // System.out.println(layer1);
        // System.out.println(layer2);
        // System.out.println();
    }
}
