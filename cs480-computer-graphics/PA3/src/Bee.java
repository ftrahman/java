/**
 * The implementation for a Bee object.
 * 
 * Boiler Plate:
 * Farheen Rahman U67393642
 * Programming Assignment 3
 * 11/12/19
 * 
 * 
 */
import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import com.jogamp.opengl.util.*;

import com.jogamp.opengl.util.gl2.GLUT;

import java.util.*;

public class Bee extends Component implements Animate{

    private double rotateSpeed = 1;
    private double scale = 1;
    
    public Point3D current;
    public Point3D target;
    public Point3D bird;
    public Point3D r_matrix;
    public double angle;
    public Bee curLeader = null;
  
    public Point3D original;
    
    public Vivarium vivarium;
    
    public Point3D predator;
    
    private Component beeBody1;
    private Component beeBody2;
    private Component beeBody3; 
    private Component beeHead;
    private Component eyeOne;
    private Component eyeTwo;
    private Component pupilOne;
    private Component pupilTwo;
    private Component wingOne; 
    private Component wingTwo; 
    private Component stinger;
    
    private Quaternion orientation = new Quaternion();
    public float[] orientMatrix;
    
    
    public double distance;
    public Point3D food;
    
    public boolean eat;
    public boolean dead;
    
    public boolean visible = true;
    
    
    
    public Bee(Point3D p, Vivarium v) {
        super(new Point3D(p));
        this.vivarium = v;
        this.current = p;
        this.setScale(1.5);
        
        this.beeHead = new Component(new Point3D(0.055, 0, 0), new SphereDisplayable(0.06f, 3, 0));
        this.beeBody1 = new Component(new Point3D(0, 0, 0), new SphereDisplayable(0.1f, 3, 0));
        this.beeBody2 = new Component(new Point3D(0.015, 0, 0), new SphereDisplayable(0.11f, 5, 0));
        this.beeBody3 = new Component(new Point3D(-0.015, 0, 0), new SphereDisplayable(0.11f, 5, 0));
        this.eyeOne = new Component(new Point3D(0.04, 0.00, 0.01), new SphereDisplayable(0.03f, 3, 0));
        this.eyeTwo = new Component(new Point3D(0.04, 0.00, -0.01), new SphereDisplayable(0.03f, 3, 0));
        this.pupilOne = new Component(new Point3D(0.01, 0, 0.005), new SphereDisplayable(0.01f, 3, 0));
        this.pupilTwo = new Component(new Point3D(0.01, 0, -0.005), new SphereDisplayable(0.01f, 3, 0));
        this.wingOne = new Component(new Point3D(-0.01, 0, -0.07), new SphereDisplayable(0.125f, 6, 90));
        this.wingTwo = new Component(new Point3D(0.01, 0, 0.07), new SphereDisplayable(0.125f, 6, 270));
        this.stinger = new Component(new Point3D(-0.04, 0.0, 0), new ConeDisplayable(0.02f, 0.06f, 270, 1));

        beeHead.setColor(new FloatColor(1, 0.84f, 0));
        beeBody1.setColor(new FloatColor(1, 0.84f, 0));
        beeBody2.setColor(new FloatColor(0, 0, 0));
        beeBody3.setColor(new FloatColor(0, 0, 0));
        eyeOne.setColor(new FloatColor(1, 1, 1));
        eyeTwo.setColor(new FloatColor(1, 1, 1));
        pupilOne.setColor(new FloatColor(0, 0, 0));
        pupilTwo.setColor(new FloatColor(0, 0, 0));
        wingOne.setColor(new FloatColor(1, 1, 1));
        wingTwo.setColor(new FloatColor(1, 1, 1));
        stinger.setColor(new FloatColor(0, 0, 0));
        

        this.addChild(beeBody1);
        this.addChild(beeBody2);
        this.addChild(beeBody3);
        beeBody1.addChild(beeHead);
        beeBody1.addChild(wingOne);
        beeBody1.addChild(wingTwo);
        beeBody1.addChild(stinger);
        beeHead.addChild(eyeOne);
        beeHead.addChild(eyeTwo);
        eyeOne.addChild(pupilOne);
        eyeTwo.addChild(pupilTwo);
        
        
        wingOne.setXNegativeExtent(-20);
        wingOne.setXPositiveExtent(20);
        wingTwo.setXNegativeExtent(-20);
        wingTwo.setXPositiveExtent(20);
        

    }
    
    
    // Gaussian potential to model repulsion/attraction
    
    public Point3D potential(Point3D p, Point3D q, double s) {
        double expr = Math.pow(p.x()-q.x(),2) + Math.pow(p.y()-q.y(),2) + Math.pow(p.z()-q.z(),2);
        double scalar = s;
        double partial_x = -2*(p.x() - q.x())*Math.exp(-expr);
        double partial_y = -2*(p.y() - q.y())*Math.exp(-expr);
        double partial_z = -2*(p.z() - q.z())*Math.exp(-expr); 
        Point3D potential = new Point3D(scalar*partial_x,scalar*partial_y,scalar*partial_z);
        return potential; 
    }
    


  
    public void move() {
      current = this.position();
    
      Point3D cur_dist = new Point3D(0, 0, 0); 
      double minDistance = 10;
    
      double min = -2; double max = 2;  
    
      double rand_x = min + Math.random() * (max - min);
      double rand_y = min + Math.random() * (max - min);
      double rand_z = min + Math.random() * (max - min);
      
      Point3D rand = new Point3D(rand_x,rand_y,rand_z);         // Set up a random movement vector
      Point3D rand_pot = potential(current, rand, 0.1);         // Calculate potential between current point and random vec
    
      predator = vivarium.bird.current;                         // Get predator coords, find potential between current and pred
      Point3D pred_pot = potential(current, predator, 0.8);
    
      double food_x = 0, food_y = 0, food_z = 0;
      if (vivarium.curFood != -1) {                             // Loop through and find the visibility of foods
        for(Food object: vivarium.foods) {
          if(object.visible == false) {
            continue;
          }
        cur_dist = object.current;                              // The food closest to bee is the one it will chase
        distance = distance(cur_dist, current);
        if(distance < minDistance) {
          minDistance = distance;
          food = cur_dist;
          }
          else continue;
        }
      
      if(collide(current, food)) {                              // Determine if there is a collision between the bee and food
        for(Food object: vivarium.foods) {                      // food is "eaten"
          if(object.current == food) {
            object.eaten = true;
          }
          else continue;
        }
      }
      Point3D food_pot = potential(current, food, 0.9);                     // Calculate food potential
      food_x = food_pot.x(); food_y = food_pot.y(); food_z = food_pot.z();  
    }
    
    vx += rand_pot.x() + food_x - pred_pot.x();     // vx/vy/vz are going to be a combination of the rondom vec, food vec and pred vec
    vy += rand_pot.y() + food_y - pred_pot.y();
    vz += rand_pot.z() + food_z - pred_pot.z();
    
    // Keep movement within certain bounds, and flips angle and direction once bound is reached
    
    if(current.x() - this.scale < -2.5 || current.x() + this.scale > 2.5 ) {
      vx = - vx; this.beeX = true;
    }
    if(current.y() - this.scale < -2.5  || current.y() + this.scale > 2.5 ) {
      vy = - vy; this.beeY = true;
    }
    if(current.z() - this.scale < -2.5  || current.z() + this.scale > 2.5 ) {
      vz = - vz; this.beeZ = true;
    }
    
    // Find the final orientation of the object and set the final point
    
    orientation();
    this.rotate(this.orientMatrix, this.angle);
    this.setPosition(new Point3D(current.x()+vx*0.0004f, current.y()+vy*0.0004f, current.z()+vz*0.0004f));
  }
  

    @Override
    public void setModelStates(ArrayList<Configuration> config_list) {
        if (config_list.size() > 1) {
            this.setConfiguration(config_list.get(0));
        }
    }
    
    
    // Find the directional orientation of the bird based off of quaternion position
    public void orientation() {
      r_matrix = original.crossProduct(current);
      original.norm(); current.norm(); //might change to norm
      angle = Math.cos(original.dotProduct(current));
          Quaternion q = new Quaternion((float)(angle),(float)r_matrix.x(),
         (float)r_matrix.y(),(float)r_matrix.z());
      this.orientation = q.multiply(this.orientation);
      this.orientMatrix = this.orientation.to_matrix();
    }
    
    double vx = -1, vy = -1, vz = -1;
    
    // Distance function
    public double distance(Point3D a, Point3D b) {
      return Math.sqrt((Math.pow((a.x() - b.x()), 2))+(Math.pow((a.y() - b.y()), 2))+(Math.pow((a.z()- b.z()), 2))); 
    }
    
    
    // Object to determine distance appropriate for collision
    public boolean collide(Point3D a, Point3D b) {
      double xDiff = a.x() - b.x(); double yDiff = a.y() - b.y(); double zDiff = a.z() - b.z();
      if (Math.abs(xDiff) < 0.2 & Math.abs(yDiff) < 0.2 & Math.abs(zDiff) < 0.2) {
          return true;
      }
      return false; 
    }
    
    
    @Override
    public void animationUpdate(GL2 gl) {
        // If bee is dead, initialize out of view
        if(!this.visible) {
          this.setPosition(new Point3D(0, -4, 0));
        }
        else 
          {
          // Otherwise find its movement
          original = new Point3D(current.x(), current.y(), current.z());
          move();

          }
      // Set wings to rotate continually 
      if (wingTwo.checkRotationReachedExtent(Axis.X)) {
        rotateSpeed = -rotateSpeed;
    }
      wingOne.rotate(Axis.X, rotateSpeed);
      wingTwo.rotate(Axis.X,-rotateSpeed);
    }
    
}
