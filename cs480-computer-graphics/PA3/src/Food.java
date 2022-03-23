/**
 * The implementation for a Food object.
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

public class Food extends Component implements Animate{

    
    public Point3D current;
    public Point3D target;
    public Point3D prey;
    
    public Vivarium vivarium;
    private float speed;
    
    public final Component food;
    
    public boolean eaten; 
    public boolean visible;
    public Point3D initial;
    
    public Food(Point3D p, Vivarium v, boolean visible) {
        super(new Point3D(p));
        
        this.vivarium = v;
        this.current = p;
        this.initial = p;
        this.speed = 0.003f;
        this.eaten = false;
        this.visible = visible;
        
         this.food = new Component(new Point3D(0, 0, 0), new SphereDisplayable(0.1f, 4, 0));
   
         this.addChild(food); 
        
    }

    @Override
    public void setModelStates(ArrayList<Configuration> config_list) {
        if (config_list.size() > 1) {
            this.setConfiguration(config_list.get(0));
        }
    }
    
    
    
    @Override
    public void animationUpdate(GL2 gl) {
        // If food is not visible, set outside of view
        if(this.visible == false) {
          this.setPosition(new Point3D(initial.x(), initial.y(), initial.z()));
        }
        this.current = this.position();
        // Food stops moving once it hits the bottom of the tank
        if(current.y() > -1.95) {
          this.setPosition(new Point3D(current.x(), current.y()-speed, current.z()));
        }
        else {
          this.setPosition(new Point3D(current.x(), current.y(), current.z()));
        }
        
    }
    
}
