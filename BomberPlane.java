// import java.awt.*;
// import java.util.Random;
// import javax.swing.*;

// public class BomberPlane extends Plane{
//     final Random random = new Random();
//     final int STANDART_Y = 100;
//     final int STANDART_X = 1000;
//     int vX;
//     int vY;
//     int eventX;
//     int eventY;
//     int eventVX;
//     int eventVY;
//     int eventPositionX;
//     Image tpimg;
//     Image eventImage;
//     Image fuelAdderImg;


//     public BomberPlane(){
//         super();
//         x = 1400;
//         y = 900;
//         vX = 10;
//         vY = 20;
//         super.vX = vX;
//         super.vY = vY;
//         super.x = x;
//         super.y = y;
//         super.tpimg = new ImageIcon("planesModel\\img\\bomber.png").getImage();
//         eventImage = new ImageIcon("planesModel\\img\\bombs.png").getImage();
//         super.fuelAdderImg = new ImageIcon("planesModel\\img\\fuelAdder.png").getImage();
//         fuel = random.nextInt(5000) + 8000;
//         timeFueling = 50;
//         eventPositionX = 600;
//         eventVX = 20;
//         eventVY = 10;
//         eventX = 900;
//         eventY = 100;
//         distanceToEvent = random.nextInt(4000) + 2000;
//         travelDistance = distanceToEvent * 2;
//     }

//     @Override
//     public void planeEvent(){
//         if(x >= eventPositionX && eventY < 1000){
//             x -= vX;
//         }
//         else{
//             eventX += eventVX;
//             eventY += eventVY;
//         }
        
//         if (eventY > 1000){
            
//             if(x < STANDART_X){
//                 x += vX;
//             }
//             else{
//                 isEvent = false;
//                 distanceToEvent = 0;
//                 isEventDone = true;
//             }
//         }
//         fuel -= vX;
//         s += vX;
//         if (fuel < 0){
//             fuel = 0;
//         }
//         moveLayers();
//     }
// }
