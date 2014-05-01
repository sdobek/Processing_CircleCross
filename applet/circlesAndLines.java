import processing.core.*; 
import processing.xml.*; 

import processing.pdf.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class circlesAndLines extends PApplet {




public void setup(){
  size(800, 500);
  //For exporting to a pdf
  //size(800, 500, PDF, "circlesAndLines.pdf");
  noFill();
  smooth();
  ellipseMode(CENTER);
}

public void draw(){
  background(0,0,81);
  
  noStroke();
  float rW = 1300;
  float rH = 1200;
  for (int f = 80; f > 0; f-=1){
    fill(0, 0, f);
    ellipse(0, 0, rW, rH);
    rW *= .97f;
    rH *= .97f;
  }
  
  noFill();
  strokeWeight(1);
  stroke(0,0,255);
  for (float t = 20; t <= 80; t+=2.2f){
      int x = 100;
      float y = 0;
      float dX = 1.5f;
      float theta = radians(t);
      float noiseVal = noise(radians(t)*200)*4-2;
      pushMatrix();
        translate(0, -100);
        rotate(theta+noiseVal/3);
        beginShape();
        while (x < width*2){
           vertex(x, y+noiseVal*20.4f);
           x += dX;
        }
        endShape();
         popMatrix();
  }
  
  
  pushMatrix();
  translate(0, 46);
  circleCross(550, 150, 160, 0, 1);
  popMatrix();
  
  //comment out if not pdf
  //exit();
}


//recurrsive class to create circle design
/*
* x,y,z - coordinates
* numberSide - position of the circles relative to the parent
* it - iteration number
*/
public void circleCross(float x, float y, float r, int numberSide, int it){
  if(it <= 4){
   strokeWeight(3);
   stroke(255, 0, 245);
   ellipse(x, y, r, r);
   
   strokeWeight(1);
   ellipse(x, y, .7f*r, .7f*r);
   stroke(175, 0, 170);
   //ellipse in a cross shape
   ellipse(x+.3f*r, y, .5f*r, .35f*r);
   ellipse(x-.3f*r, y, .5f*r, .35f*r);
   ellipse(x, y+.3f*r, .35f*r, .5f*r);
   ellipse(x, y-.3f*r, .35f*r, .5f*r);
   stroke(255, 0, 245);
   it+=1;
   switch(numberSide){
     case 0: 
       circleCross(x+.85f*r, y, .7f*r, 1, it);
       circleCross(x-.85f*r, y, .7f*r, 3, it);  
       circleCross(x, y-.85f*r, .7f*r, 2, it);
       circleCross(x, y+.95f*r, .9f*r, 4, it);
       break;
     case 1:
       circleCross(x+.85f*r, y, .7f*r, 1, it);
       circleCross(x-.65f*r, y,  .3f*r, 3, it); 
       circleCross(x, y-.85f*r, .7f*r, 2, it);
       circleCross(x, y+.95f*r, .9f*r, 4, it);
       break;
     case 2:
       circleCross(x+.85f*r, y, .7f*r, 1, it);
       circleCross(x-.85f*r, y, .7f*r, 3, it);  
       circleCross(x, y-.85f*r, .7f*r, 2, it);
       circleCross(x, y+.75f*r,  .5f*r, 4, it); 
       break;
      case 3:
       circleCross(x+.65f*r, y,  .3f*r, 1, it); 
       circleCross(x-.85f*r, y, .7f*r, 3, it);
       circleCross(x, y-.85f*r, .7f*r, 2, it);
       circleCross(x, y+.95f*r, .9f*r, 4, it);
       break;
      case 4:
       circleCross(x+.85f*r, y, .7f*r, 1, it);
       circleCross(x-.85f*r, y, .7f*r, 3, it); 
       circleCross(x, y-.65f*r,  .3f*r, 2, it);  
       circleCross(x, y+.95f*r, .9f*r, 4, it);
       break;
      default:
        break;
   }
  }
}

  
  public void drawLine(int x, int y, float dX, float theta){
      while (x < width*2){
         pushMatrix();
         translate(0, -100);
         rotate(theta);
         point(x, y);
         popMatrix();
         x += dX;
      }
  }
  
  public void drawLine2(int x, float y, float dX, float theta){
        pushMatrix();
        translate(0, -100);
        rotate(theta);
        beginShape();
        while (x < width*2){
           float noiseVal = noise(x)*8-4;
           vertex(x, y+1.2f*noiseVal);
           x += dX;
           //noise
        }
        endShape();
         popMatrix();
  }
      

  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#F0F0F0", "circlesAndLines" });
  }
}
