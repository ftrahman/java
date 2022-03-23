/**
 * The implementation for a vivarium object and its creatures.
 * 
 * Boiler Plate:
 * Farheen Rahman U67393642
 * Programming Assignment 3
 * 11/12/19
 * 
 * 
 */


import javax.media.opengl.*;
import com.jogamp.opengl.util.*;
import java.util.*;

public class Vivarium implements Displayable, Animate {
	private Tank tank;
	public Bee bee;
	public Bee bee2;
	public Bee bee3;
	public Bee bee4;
	public Bee bee5;
	public Bird bird;
	public Food food1; public Food food2; public Food food3; public Food food4; public Food food5;  
	public Food food6; public Food food7; public Food food8; public Food food9; public Food food10; 
	public Food food11; public Food food12; public Food food13; public Food food14; public Food food15; 
	public Food food16; public Food food17; public Food food18; public Food food19; public Food food20; 
	public boolean eaten;
	public boolean drawFood = false;
	public double foodLimit = 19; 
	public double curFood = 2; 
	public double curBees = 5; 

	
	// Compile lists of similar animals, as well as to add everything to vivarium
	public ArrayList<Component> vivarium = new ArrayList<Component>();
	public ArrayList<Bee> bees = new ArrayList<Bee>();
	public ArrayList<Food> foods = new ArrayList<Food>();

	public Vivarium() {
		tank = new Tank(4.0f, 4.0f, 4.0f);
		this.bird = new Bird(new Point3D(0, 0, 0), this, 0.75);
		this.bee = new Bee(new Point3D(1.5, 0, 1), this);
		this.bee2 = new Bee(new Point3D(1, 0, -1), this);
		this.bee3 = new Bee(new Point3D(-1, 0, 1), this);
		this.bee4 = new Bee(new Point3D(-1.5, 0, 1), this);
		this.bee5 = new Bee(new Point3D(-1, 0, 1.5), this);
		
		// Initialize all food objects
		this.food1 = new Food(new Point3D(0, 1.5, 0), this, true);
		this.food2 = new Food(new Point3D(0.2, 1.5, 0), this, true);
		this.food3 = new Food(new Point3D(-0.2, 1.5, 0), this, true);
		this.food4 = new Food(new Point3D(0.15, 1.5, 0), this, false);
		this.food5 = new Food(new Point3D(0.25, 1.5, 0), this, false);
		this.food6 = new Food(new Point3D(0, 1.5, 0), this, true);
        this.food7 = new Food(new Point3D(0.2, 1.5, 0), this, true);
        this.food8 = new Food(new Point3D(-0.2, 1.5, 0), this, true);
        this.food9 = new Food(new Point3D(0.15, 1.5, 0), this, false);
        this.food10 = new Food(new Point3D(0.25, 1.5, 0), this, false);
        this.food11 = new Food(new Point3D(0, 1.5, 0), this, true);
        this.food12 = new Food(new Point3D(0.2, 1.5, 0), this, true);
        this.food13 = new Food(new Point3D(-0.2, 1.5, 0), this, true);
        this.food14 = new Food(new Point3D(0.15, 1.5, 0), this, false);
        this.food15 = new Food(new Point3D(0.25, 1.5, 0), this, false);
        this.food16 = new Food(new Point3D(0, 1.5, 0), this, true);
        this.food17 = new Food(new Point3D(0.2, 1.5, 0), this, true);
        this.food18 = new Food(new Point3D(-0.2, 1.5, 0), this, true);
        this.food19 = new Food(new Point3D(0.15, 1.5, 0), this, false);
        this.food20 = new Food(new Point3D(0.25, 1.5, 0), this, false);
		
		vivarium.add(bird);
		vivarium.add(bee); vivarium.add(bee2); vivarium.add(bee3); vivarium.add(bee4); vivarium.add(bee5);
        vivarium.add(food1); vivarium.add(food2); vivarium.add(food3); vivarium.add(food4); vivarium.add(food5);
        vivarium.add(food6); vivarium.add(food7); vivarium.add(food8); vivarium.add(food9); vivarium.add(food10);
        vivarium.add(food11); vivarium.add(food12); vivarium.add(food13); vivarium.add(food14); vivarium.add(food15);
        vivarium.add(food16); vivarium.add(food17); vivarium.add(food18); vivarium.add(food19); vivarium.add(food20);
		bees.add(bee); bees.add(bee2); bees.add(bee3); bees.add(bee4); bees.add(bee5);
		foods.add(food1); foods.add(food2); foods.add(food3); foods.add(food4); foods.add(food5);
		foods.add(food6); foods.add(food7); foods.add(food8); foods.add(food9); foods.add(food10);
		foods.add(food11); foods.add(food12); foods.add(food13); foods.add(food14); foods.add(food15);
		foods.add(food16); foods.add(food17); foods.add(food18); foods.add(food19); foods.add(food20);
		
	}

	public void initialize(GL2 gl) {
		tank.initialize(gl);
		for (Component object : vivarium) {
			object.initialize(gl);
		}
	}

	public void update(GL2 gl) {
		tank.update(gl);
		for (Component object : vivarium) {
		  if(foods.contains(object) && !drawFood) {           // If drawFood not toggled, don't draw
		    continue;
		  }
		  else if(foods.indexOf(object) > curFood) {          // If food object index is past limit, don't draw
		    continue;
		  }
		  for (Food obj : foods) {                            // If food past limit, block visibility
		    if(foods.indexOf(obj) > curFood) {
		      obj.visible = false; 
		      }
		    else if (obj.eaten) {                             // If food eaten, change visibility, but reset so we can respawn
		      obj.visible = false;
              obj.eaten = false; curFood--;
              continue;
            }
		    else obj.visible = true; obj.eaten = false;      
	          }
		  for (Bee obj : bees) {                              // If bees are not visible, don't draw
            if(!obj.visible) {
              continue;
            }
            continue;
		  }
		  object.update(gl);                                  // Draw visible objects
		  }	
		
	}
	
	// Same exact routines as above in update apply below

	public void draw(GL2 gl) {
		tank.draw(gl);
		for (Component object : vivarium) {
		  if(foods.contains(object) && !drawFood) {
            continue;
          }
		  else if (foods.indexOf(object) > curFood) {
            continue;
          }
          else {
            for (Food obj : foods) {
              if(foods.indexOf(obj) > curFood) {
                obj.visible = false;
                }
              else if (obj.eaten) {
                obj.visible = false;
                obj.eaten = false; curFood--;
                continue;
              }
              else obj.visible = true; obj.eaten = false; 
              }
            object.draw(gl);
          } 
		}
	}
	


	@Override
	public void setModelStates(ArrayList<Configuration> config_list) {
		// assign configurations in config_list to all Components in here
	}

	@Override
	public void animationUpdate(GL2 gl) {
		for (Component example : vivarium) {
			if (example instanceof Animate) {
				((Animate) example).animationUpdate(gl);
			}
		}
	}
}
