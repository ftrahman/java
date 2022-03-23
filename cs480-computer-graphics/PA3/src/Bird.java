
/**
 * The implementation for a Bird object.
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

public class Bird extends Component implements Animate{

	private double rotateSpeed = 1;
	private double scale = 1;
	
	private Quaternion orientation = new Quaternion();
    
    
    public Point3D current;
    public Point3D prey;
    public Point3D r_matrix;
   
    public Point3D original;
    
    public Vivarium vivarium;
    public final Component birdBody;
    public final Component birdHead;
    public final Component tailOne;
    public final  Component tailTwo;
    public final Component tailThree;
    private Component eyeOne;
    private Component eyeTwo;
    private Component pupilOne;
    private Component pupilTwo;
    private Component leftWing;
    private Component rightWing;
    private Component featherOneL;
    private Component featherTwoL;
    private Component featherThreeL;
    private Component featherFourL;
    private Component featherFiveL;
    private Component featherSixL;
    private Component featherSevenL;
    private Component featherEightL;
    private Component featherOneR;
    private Component featherTwoR;
    private Component featherThreeR;
    private Component featherFourR;
    private Component featherFiveR;
    private Component featherSixR;
    private Component featherSevenR;
    private Component featherEightR;
    private Component beak;
    public double distance;
    public double angle;
    
    public float[] orientMatrix;
    
    
    
	
	public Bird(Point3D p, Vivarium v, double scale) {
		super(new Point3D(p));
		this.setScale(scale);
		
        this.vivarium = v;
        this.current = p;
		
		this.birdBody = new Component(new Point3D(0, 0, 0), new SphereDisplayable(0.4f, 0, 0));
		this.birdHead = new Component(new Point3D(0, 0, 0.4), new SphereDisplayable(0.2f, 1, 0));
		this.leftWing = new Component(new Point3D(0.12, 0, 0.05), new ConeDisplayable(0.12f, 0.8f, 90, 0));
		this.rightWing = new Component(new Point3D(-0.12, 0, 0.05), new ConeDisplayable(0.12f, 0.8f, 270, 0));
		this.tailOne = new Component(new Point3D(0.07, 0, -0.4), new SphereDisplayable(0.2f, 2, 0));
		this.tailTwo = new Component(new Point3D(-0.07, 0, -0.4), new SphereDisplayable(0.2f, 2, 0));
		this.tailThree = new Component(new Point3D(0, 0, -0.4), new SphereDisplayable(0.2f, 2, 0));
		this.beak = new Component(new Point3D(0, 0, 0), new ConeDisplayable(0.1f, 0.3f, 0, 1));
		
		this.eyeOne = new Component(new Point3D(0.05, 0, 0.1), new SphereDisplayable(0.08f, 3, 0));
        this.eyeTwo = new Component(new Point3D(-0.05, 0, 0.1), new SphereDisplayable(0.08f, 3, 0));
        this.pupilOne = new Component(new Point3D(0.01, 0.01, 0.04), new SphereDisplayable(0.04f, 3, 0));
        this.pupilTwo = new Component(new Point3D(-0.01, 0.01, 0.04), new SphereDisplayable(0.04f, 3, 0));
		
		this.featherOneL = new Component(new Point3D(0.8, 0, -0.09), new SphereDisplayable(0.15f, 7, -15));
		this.featherTwoL = new Component(new Point3D(0.7, 0, -0.1), new SphereDisplayable(0.15f, 7, -15));
		this.featherThreeL = new Component(new Point3D(0.6, 0, -0.1), new SphereDisplayable(0.15f, 7, -15));
		this.featherFourL = new Component(new Point3D(0.5, 0, -0.1), new SphereDisplayable(0.15f, 7, -15));
		this.featherFiveL = new Component(new Point3D(0.4, 0, -0.1), new SphereDisplayable(0.15f, 7, -15));
		this.featherSixL = new Component(new Point3D(0.3, 0, -0.1), new SphereDisplayable(0.15f, 7, -15));
		this.featherSevenL = new Component(new Point3D(0.2, 0, -0.1), new SphereDisplayable(0.15f, 7, -15));
        this.featherEightL = new Component(new Point3D(0.1, 0, -0.1), new SphereDisplayable(0.15f, 7, -15));
        
        this.featherOneR = new Component(new Point3D(-0.8, 0, -0.09), new SphereDisplayable(0.15f, 7, 15));
        this.featherTwoR = new Component(new Point3D(-0.7, 0, -0.1), new SphereDisplayable(0.15f, 7, 15));
        this.featherThreeR = new Component(new Point3D(-0.6, 0, -0.1), new SphereDisplayable(0.15f, 7, 15));
        this.featherFourR = new Component(new Point3D(-0.5, 0, -0.1), new SphereDisplayable(0.15f, 7, 15));
        this.featherFiveR = new Component(new Point3D(-0.4, 0, -0.1), new SphereDisplayable(0.15f, 7, 15));
        this.featherSixR = new Component(new Point3D(-0.3, 0, -0.1), new SphereDisplayable(0.15f, 7, 15));
        this.featherSevenR = new Component(new Point3D(-0.2, 0, -0.1), new SphereDisplayable(0.15f, 7, 15));
        this.featherEightR = new Component(new Point3D(-0.1, 0, -0.1), new SphereDisplayable(0.15f, 7, 15));
        
        birdBody.setColor(new FloatColor(0.3f, 0.6f, 1f));
        birdHead.setColor(new FloatColor(0.3f, 0.6f, 1f));
        leftWing.setColor(new FloatColor(0.3f, 0.6f, 1f));
        rightWing.setColor(new FloatColor(0.3f, 0.6f, 1f));
        tailOne.setColor(new FloatColor(0.3f, 0.6f, 1f));
        tailTwo.setColor(new FloatColor(0.3f, 0.6f, 1f));
        tailThree.setColor(new FloatColor(0.3f, 0.6f, 1f));
        
        eyeOne.setColor(new FloatColor(1, 1, 1));
        eyeTwo.setColor(new FloatColor(1, 1, 1));
        pupilOne.setColor(new FloatColor(0, 0, 0));
        pupilTwo.setColor(new FloatColor(0, 0, 0));
        
        featherOneL.setColor(new FloatColor(0.3f, 0.6f, 1f));
        featherTwoL.setColor(new FloatColor(0.3f, 0.6f, 1f));
        featherThreeL.setColor(new FloatColor(0.3f, 0.6f, 1f));
        featherFourL.setColor(new FloatColor(0.3f, 0.6f, 1f));
        featherFiveL.setColor(new FloatColor(0.3f, 0.6f, 1f));
        featherSixL.setColor(new FloatColor(0.3f, 0.6f, 1f));
        featherSevenL.setColor(new FloatColor(0.3f, 0.6f, 1f));
        featherEightL.setColor(new FloatColor(0.3f, 0.6f, 1f));
        
        featherOneR.setColor(new FloatColor(0.3f, 0.6f, 1f));
        featherTwoR.setColor(new FloatColor(0.3f, 0.6f, 1f));
        featherThreeR.setColor(new FloatColor(0.3f, 0.6f, 1f));
        featherFourR.setColor(new FloatColor(0.3f, 0.6f, 1f));
        featherFiveR.setColor(new FloatColor(0.3f, 0.6f, 1f));
        featherSixR.setColor(new FloatColor(0.3f, 0.6f, 1f));
        featherSevenR.setColor(new FloatColor(0.3f, 0.6f, 1f));
        featherEightR.setColor(new FloatColor(0.3f, 0.6f, 1f));
        
        birdBody.rotate(Axis.Y, 90);
		this.addChild(birdBody);
		birdBody.addChild(birdHead);
		birdBody.addChild(leftWing);
		birdBody.addChild(rightWing);
		birdBody.addChild(tailOne);
		birdBody.addChild(tailTwo);
		birdBody.addChild(tailThree);
		
		birdHead.addChild(eyeOne);
        birdHead.addChild(eyeTwo);
        birdHead.addChild(beak);
        eyeOne.addChild(pupilOne);
        eyeTwo.addChild(pupilTwo);
		
		leftWing.addChild(featherOneL);
		leftWing.addChild(featherTwoL);
		leftWing.addChild(featherThreeL);
		leftWing.addChild(featherFourL);
		leftWing.addChild(featherFiveL);
		leftWing.addChild(featherSixL);
		leftWing.addChild(featherSevenL);
        leftWing.addChild(featherEightL);
        
        rightWing.addChild(featherOneR);
        rightWing.addChild(featherTwoR);
        rightWing.addChild(featherThreeR);
        rightWing.addChild(featherFourR);
        rightWing.addChild(featherFiveR);
        rightWing.addChild(featherSixR);
        rightWing.addChild(featherSevenR);
        rightWing.addChild(featherEightR);
		
		
		leftWing.setZNegativeExtent(-30);
		leftWing.setZPositiveExtent(30);
		rightWing.setZNegativeExtent(-30);
		rightWing.setZPositiveExtent(30);
		

	}
	

	@Override
	public void setModelStates(ArrayList<Configuration> config_list) {
		if (config_list.size() > 1) {
			this.setConfiguration(config_list.get(0));
		}
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
	
	// Method to determine movement of bird
	
	public void move() {
	  this.current = this.position();
	  Point3D cur_dist = new Point3D(0, 0, 0); 
	  Point3D prey_pot = new Point3D(0, 0, 0); 
	  
	  double minDistance = 10;
	  
	  double min = -2; double max = 2;  
	  
	  double rand_x = min + Math.random( ) * (max - min);
	  double rand_y = min + Math.random( ) * (max - min);
	  double rand_z = min + Math.random( ) * (max - min);
	  
	  Point3D rand = new Point3D(rand_x,rand_y,rand_z);    // Set up a random movement vector
      Point3D rand_pot = potential(current, rand, 0.1);    // Find the potential between the random vec and current vec
	  
	  if(vivarium.curBees != 0) {                          // Visibility determines if a bee is dead or alive
	    for(Bee object: vivarium.bees) {                   // So check to see there are still bees
	      if(object.visible == false) {
            continue;
          }
          cur_dist = object.current;                        // Loop through the list of all bees and find the one
          distance = distance(cur_dist, current);           // we are closest to, set this as our prey vector
          if(distance < minDistance) {
            minDistance = distance;
            prey = cur_dist;
            }
          else continue;
          }
	  if(collide(current, prey)) {                         // If there is a collision between the predator and prey
        for(Bee object: vivarium.bees) {                   // change the bee's visibility to dead
          if(object.current == prey) {
            object.visible = false;
            vivarium.curBees--;
          }
          else continue;
          }
        }
	    prey_pot = potential(current, prey, 0.7);          // Calculate potential between prey and predator
	    vx += prey_pot.x();                                // Set direction vectors based off prey potential if there is prey
	    vy += prey_pot.y();
	    vz += prey_pot.z();
	  }
	  else {
	    vx += rand_pot.x();                                // If there is no prey, set direction vectors to the random vec
	    vy += rand_pot.y();
	    vz += rand_pot.z();
	  }
	  
	  // Maintain the movement within bounds of our tank, flip direction and angle of vx/vy/vz if limit reached
	  
      if(current.x() - this.scale < -2.5  || current.x() + this.scale > 2.5) {
        vx = - vx; this.birdX = true;
      }
      if(current.y() - this.scale < -2 || current.y() + this.scale > 2) {
        vy = - vy; this.birdY = true;
      }
      if(current.z() - this.scale < -2.5  || current.z() + this.scale > 2.5) {
        vz = - vz; this.birdZ = true;
      }
      
      // Find the orientation and rotate the object based off of new points
      orientation();
      this.rotate(this.orientMatrix, this.angle);
      
      // Set the final position for the object
      this.setPosition(new Point3D(current.x()+vx*0.0002f, current.y()+vy*0.0002f, current.z()+vz*0.0002f));
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
	    // Find starting point and calculate movement for bird
	    original = new Point3D(current.x(), current.y(), current.z());
		move();
		
		// Set continual rotation for wings 
		if (leftWing.checkRotationReachedExtent(Axis.Z)) {
		    rotateSpeed = -rotateSpeed;
		}
		leftWing.rotate(Axis.Z, rotateSpeed);
		rightWing.rotate(Axis.Z,-rotateSpeed);

	}
	
	
}
