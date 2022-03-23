/**
 * The implementation for the entire body object.
 * 
 * Boiler Plate:
 * Farheen Rahman U67393642
 * Programming Assignment 2
 * 10/15/19
 * 
 * 
 */

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jogamp.opengl.util.gl2.GLUT;

public class Body extends Component implements Animate, Selection{
	/** The OpenGL utility toolkit object. */
	private final GLUT glut = new GLUT();
	
	
	private final Component head;
	private final Component thorax_one;
	private final Component thorax_two;
	private final Component abdomen;
	private final Component eye_one;
	private final Component eye_two;
	private final Component pupil_one;
    private final Component pupil_two;
	//private final Antenna[] antennas;
	private final Leg[] legs;
	

	/** The set of all components. */
	final List<Component> components;
	/** The set of components which are currently selected for rotation. */
	private final Set<Component> selectedComponents = new HashSet<Component>(18);
	
	/** The color for components which are selected for rotation. */
	public static final FloatColor ACTIVE_COLOR = FloatColor.RED;
  	/** The color for components which are not selected for rotation. */
	public static final FloatColor INACTIVE_COLOR = FloatColor.AUBURN;
	
	/** The radius of the head. */
    public static final double HEAD_RADIUS = 0.5;
    /** The radius of the iris. */
    public static final double EYE_RADIUS = 0.5;
    /** The radius of the pupil. */
    public static final double PUPIL_RADIUS = 0.2;
	/** The radius of the components which comprise the first part of the thorax. */
	public static final double THORAX_ONE_RADIUS = 0.45;
	/** The radius of the components which comprise the second part of the thorax. */
	public static final double THORAX_TWO_RADIUS = 0.4;
	/** The radius of the components which comprise the abdomen. */
	public static final double ABDOMEN_RADIUS = 0.5;
	/** The radius of each joint which comprises the legs. */
    public static final double LEG_RADIUS = 0.04;
	/** The height of the distal joint on each of the legs. */
	public static final double DISTAL_JOINT_HEIGHT = 0.9;
	/** The height of the middle joint on each of the legs. */
	public static final double MIDDLE_JOINT_HEIGHT = 0.4;
	/** The height of the palm joint on each of the legs. */
	public static final double PROX_JOINT_HEIGHT = 0.7;

	
	
	public static final String LEG_ONE_PROXIMAL_NAME = "leg one proximal";
	public static final String LEG_ONE_MIDDLE_NAME = "leg one middle";
  	public static final String LEG_ONE_DISTAL_NAME = "leg one distal";
    public static final String LEG_TWO_PROXIMAL_NAME = "leg two proximal";
    public static final String LEG_TWO_MIDDLE_NAME = "leg two middle";
    public static final String LEG_TWO_DISTAL_NAME = "leg two distal";
    public static final String LEG_THREE_PROXIMAL_NAME = "leg three proximal";
    public static final String LEG_THREE_MIDDLE_NAME = "leg three middle";
    public static final String LEG_THREE_DISTAL_NAME = "leg three distal";
    public static final String LEG_FOUR_PROXIMAL_NAME = "leg four proximal";
    public static final String LEG_FOUR_MIDDLE_NAME = "leg four middle";
    public static final String LEG_FOUR_DISTAL_NAME = "leg four distal";
    public static final String LEG_FIVE_PROXIMAL_NAME = "leg five proximal";
    public static final String LEG_FIVE_MIDDLE_NAME = "leg five middle";
    public static final String LEG_FIVE_DISTAL_NAME = "leg five distal";
    public static final String LEG_SIX_PROXIMAL_NAME = "leg six proximal";
    public static final String LEG_SIX_MIDDLE_NAME = "leg six middle";
    public static final String LEG_SIX_DISTAL_NAME = "leg six distal";
    public static final String RIGHT_ANTENNA_NAME = "right antenna";
    public static final String LEFT_ANTENNA_NAME = "left antenna";
  	public static final String HEAD_NAME = "head";
  	public static final String THORAX_ONE_NAME = "thorax one";
  	public static final String THORAX_TWO_NAME = "thorax two";
  	public static final String ABDOMEN_NAME = "abdomen";
  	public static final String EYE_ONE_NAME = "left eye";
  	public static final String EYE_TWO_NAME = "right eye";
  	public static final String PUPIL_ONE_NAME = "left pupil";
    public static final String PUPIL_TWO_NAME = "right pupil";
	
  	private Component mapNum2Component(int componentNum) {
  		switch(componentNum) {
  			case  0: return this.head;
			case  1: return this.thorax_one;
			case  2: return this.thorax_two;
			case  3: return this.abdomen;
			case  4: return this.legs[0].proximalJoint();
			case  5: return this.legs[0].middleJoint();
			case  6: return this.legs[0].distalJoint();
			case  7: return this.legs[1].proximalJoint();
			case  8: return this.legs[1].middleJoint();
			case  9: return this.legs[1].distalJoint();
			case 10: return this.legs[2].proximalJoint();
			case 11: return this.legs[2].middleJoint();
			case 12: return this.legs[2].distalJoint();
			case 13: return this.legs[3].proximalJoint();
			case 14: return this.legs[3].middleJoint();
			case 15: return this.legs[3].distalJoint();
			case 16: return this.legs[4].proximalJoint();
			case 17: return this.legs[4].middleJoint();
			case 18: return this.legs[4].distalJoint();
			case 19: return this.legs[5].proximalJoint();
            case 20: return this.legs[5].middleJoint();
            case 21: return this.legs[5].distalJoint();
			default: throw new IllegalArgumentException("componentNum over index"); 
  		}
  	}
  	
  	private Component mapName2Component(String componentName) {
  		switch(componentName) {
	  		case  HEAD_NAME: return this.head;
			case  THORAX_ONE_NAME: return this.thorax_one;
			case  THORAX_TWO_NAME: return this.thorax_two;
			case  ABDOMEN_NAME: return this.abdomen;
			case  EYE_ONE_NAME: return this.eye_one;
			case  EYE_TWO_NAME: return this.eye_two;
			case  PUPIL_ONE_NAME: return this.pupil_one;
			case  PUPIL_TWO_NAME: return this.pupil_two;
			case  LEG_ONE_PROXIMAL_NAME: return this.legs[0].proximalJoint();
			case  LEG_ONE_MIDDLE_NAME: return this.legs[0].middleJoint();
			case  LEG_ONE_DISTAL_NAME: return this.legs[0].distalJoint();
			case  LEG_TWO_PROXIMAL_NAME: return this.legs[1].proximalJoint();
            case  LEG_TWO_MIDDLE_NAME: return this.legs[1].middleJoint();
            case  LEG_TWO_DISTAL_NAME: return this.legs[1].distalJoint();
            case  LEG_THREE_PROXIMAL_NAME: return this.legs[2].proximalJoint();
            case  LEG_THREE_MIDDLE_NAME: return this.legs[2].middleJoint();
            case  LEG_THREE_DISTAL_NAME: return this.legs[2].distalJoint();
            case  LEG_FOUR_PROXIMAL_NAME: return this.legs[3].proximalJoint();
            case  LEG_FOUR_MIDDLE_NAME: return this.legs[3].middleJoint();
            case  LEG_FOUR_DISTAL_NAME: return this.legs[3].distalJoint();
            case  LEG_FIVE_PROXIMAL_NAME: return this.legs[4].proximalJoint();
            case  LEG_FIVE_MIDDLE_NAME: return this.legs[4].middleJoint();
            case  LEG_FIVE_DISTAL_NAME: return this.legs[4].distalJoint();
            case  LEG_SIX_PROXIMAL_NAME: return this.legs[5].proximalJoint();
            case  LEG_SIX_MIDDLE_NAME: return this.legs[5].middleJoint();
            case  LEG_SIX_DISTAL_NAME: return this.legs[5].distalJoint();
            //case  RIGHT_ANTENNA_NAME: return this.legs[0].proximalJoint();
            //case  LEFT_ANTENNA_NAME: return this.legs[0].middleJoint();

			default: throw new IllegalArgumentException("componentName doesn't exist");
  		}
  	}
  	
  	public void setModelStates(final ArrayList<Configuration> config_list) {
  		for (int i = 0; i < config_list.size(); i++) {
  			if ( 0 <= i && i <= 22) {
  				mapNum2Component(i).setAngles(config_list.get(i));
  			}
  		}
  	}
  	
  	public void setModelStates(final Map<String, Configuration> state) {
  		for (Map.Entry<String, Configuration> entry: state.entrySet()) {
  			this.mapName2Component(entry.getKey()).setAngles(entry.getValue());
  		}
  	}
  	
  	/**
     * Prints the joints on the specified PrintStream.
     * 
     * @param printStream
     *          The stream on which to print each of the components.
     */
    public void printJoints(final PrintStream printStream) {
      for (final Component component : this.components) {
        printStream.println(component);
      }
    }

  	
  	public void toggleSelection(int selectionNum) {
  		if ( 0 <= selectionNum && selectionNum <= 22) {
  			Component component = mapNum2Component(selectionNum);
  			if ( this.selectedComponents.contains(component) ) {
  				this.selectedComponents.remove(component);
  				component.setColor(INACTIVE_COLOR);
  			}
  			else {
  		      this.selectedComponents.add(component);
  		      component.setColor(ACTIVE_COLOR);
  		    }
		}
  	}
  	
  	public void changeSelected(Configuration config) {
  		for(Component c: this.selectedComponents) {
  			c.changeConfiguration(config);
  		}
  	}
  	
	public Body(final Point3D position, final String name) {
		// Body object itself as a top level component, need initialization.
		super(position, name);
		
		// All the distal joints.
	    final Component distal1 = new Component(new Point3D(0, 0,
	        MIDDLE_JOINT_HEIGHT), new Limb(LEG_RADIUS,
	        DISTAL_JOINT_HEIGHT, 75, 5, this.glut), LEG_ONE_DISTAL_NAME);
	    final Component distal2 = new Component(new Point3D(0, 0,
	        MIDDLE_JOINT_HEIGHT), new Limb(LEG_RADIUS,
	        DISTAL_JOINT_HEIGHT, 60, 5, this.glut), LEG_TWO_DISTAL_NAME);
	    final Component distal3 = new Component(new Point3D(0, 0,
	        MIDDLE_JOINT_HEIGHT), new Limb(LEG_RADIUS,
	        DISTAL_JOINT_HEIGHT, 45, 5, this.glut), LEG_THREE_DISTAL_NAME);
	    final Component distal4 = new Component(new Point3D(0, 0,
	        MIDDLE_JOINT_HEIGHT), new Limb(LEG_RADIUS,
	        DISTAL_JOINT_HEIGHT, 45, 5, this.glut), LEG_FOUR_DISTAL_NAME);
	    final Component distal5 = new Component(new Point3D(0, 0,
	        MIDDLE_JOINT_HEIGHT), new Limb(LEG_RADIUS,
	        DISTAL_JOINT_HEIGHT, 60, 5, this.glut), LEG_FIVE_DISTAL_NAME);
	    final Component distal6 = new Component(new Point3D(0, 0,
            MIDDLE_JOINT_HEIGHT), new Limb(LEG_RADIUS,
            DISTAL_JOINT_HEIGHT, 75, 5, this.glut), LEG_SIX_DISTAL_NAME);

	    // All the middle joints.
	    final Component middle1 = new Component(new Point3D(0, 0,
	        PROX_JOINT_HEIGHT), new Limb(LEG_RADIUS,
	        MIDDLE_JOINT_HEIGHT, 105, 3, this.glut), LEG_ONE_MIDDLE_NAME);
	    final Component middle2 = new Component(new Point3D(0, 0,
	        PROX_JOINT_HEIGHT), new Limb(LEG_RADIUS,
	        MIDDLE_JOINT_HEIGHT, 60, 3, this.glut), LEG_TWO_MIDDLE_NAME);
	    final Component middle3 = new Component(new Point3D(0, 0,
	        PROX_JOINT_HEIGHT), new Limb(LEG_RADIUS,
	        MIDDLE_JOINT_HEIGHT, 45, 3, this.glut), LEG_THREE_MIDDLE_NAME);
	    final Component middle4 = new Component(new Point3D(0, 0,
	        PROX_JOINT_HEIGHT), new Limb(LEG_RADIUS,
	        MIDDLE_JOINT_HEIGHT, 45, 4, this.glut), LEG_FOUR_MIDDLE_NAME);
	    final Component middle5 = new Component(new Point3D(0, 0,
	        PROX_JOINT_HEIGHT), new Limb(LEG_RADIUS,
	        MIDDLE_JOINT_HEIGHT, 60, 4, this.glut), LEG_FIVE_MIDDLE_NAME);
	    final Component middle6 = new Component(new Point3D(0, 0,
            PROX_JOINT_HEIGHT), new Limb(LEG_RADIUS,
            MIDDLE_JOINT_HEIGHT, 105, 4, this.glut), LEG_SIX_MIDDLE_NAME);

	    // All the proximal joints, displaced by various amounts from the thorax.
	    final Component prox1 = new Component(new Point3D(-0.2, 0, -0.1),
	        new Limb(LEG_RADIUS, PROX_JOINT_HEIGHT, 105, 1, this.glut),
	        LEG_ONE_PROXIMAL_NAME);
	    final Component prox2 = new Component(new Point3D(-0.2, 0, 0.2),
	        new Limb(LEG_RADIUS, PROX_JOINT_HEIGHT, 60, 1, this.glut),
	        LEG_TWO_PROXIMAL_NAME);
	    final Component prox3 = new Component(new Point3D(-0.2, 0, 0.45),
	        new Limb(LEG_RADIUS, PROX_JOINT_HEIGHT, 45, 1, this.glut),
	        LEG_THREE_PROXIMAL_NAME);
	    final Component prox4 = new Component(new Point3D(0.2, 0, 0.45),
	        new Limb(LEG_RADIUS, PROX_JOINT_HEIGHT, 45, 2, this.glut),
	        LEG_FOUR_PROXIMAL_NAME);
	    final Component prox5 = new Component(new Point3D(0.2, 0, 0.2),
	        new Limb(LEG_RADIUS, PROX_JOINT_HEIGHT, 60, 2, this.glut),
	        LEG_FIVE_PROXIMAL_NAME);
	    final Component prox6 = new Component(new Point3D(0.2, 0, -0.1),
            new Limb(LEG_RADIUS, PROX_JOINT_HEIGHT, 105, 2, this.glut),
            LEG_SIX_PROXIMAL_NAME);

	    // Put together the legs for easier selection by keyboard input later on.
	    this.legs = new Leg[] { new Leg(prox1, middle1, distal1),
	        new Leg(prox2, middle2, distal2),
	        new Leg(prox3, middle3, distal3),
	        new Leg(prox4, middle4, distal4),
	        new Leg(prox5, middle5, distal5),
	        new Leg(prox6, middle6, distal6), };

	    
	    // The head, which models the neck joint.
	    this.head = new Component(new Point3D(0, 0, 0), new Head(
	        HEAD_RADIUS, this.glut), HEAD_NAME);
	    
	    // The left eye model.
	    this.eye_one = new Component(new Point3D(0.1, 0, -0.45), 
	        new Eye(EYE_RADIUS, this.glut), EYE_ONE_NAME);
	    
	    // The right eye model.
	    this.eye_two = new Component(new Point3D(-0.1, 0, -0.45), 
            new Eye(EYE_RADIUS, this.glut), EYE_TWO_NAME);
	    
	    // Setting the color of each eye to white.
	    this.eye_one.setColor(FloatColor.WHITE);
	    this.eye_two.setColor(FloatColor.WHITE);
	    
	    // The left pupil model.
	    this.pupil_one = new Component(new Point3D(0.02, 0, 0.2), 
            new Eye(PUPIL_RADIUS, this.glut), PUPIL_ONE_NAME);
        
	    // The right pupil model.
        this.pupil_two = new Component(new Point3D(-0.02, 0, 0.2), 
            new Eye(PUPIL_RADIUS, this.glut), PUPIL_TWO_NAME);
        
        // Setting the color of each pupil to black.
        this.pupil_one.setColor(FloatColor.BLACK);
        this.pupil_two.setColor(FloatColor.BLACK);
	    
        // The first thorax.
	    this.thorax_one = new Component(new Point3D(0, 0, HEAD_RADIUS * 1.75),
	        new Thorax(THORAX_ONE_RADIUS, this.glut), THORAX_ONE_NAME);
	    
	    // The second thorax, which models abdominal joint.
	    this.thorax_two = new Component(new Point3D(0, 0, THORAX_ONE_RADIUS),
            new Thorax(THORAX_TWO_RADIUS, this.glut), THORAX_TWO_NAME);

	    // The abdomen.
	    this.abdomen = new Component(new Point3D(0, 0, THORAX_TWO_RADIUS * 2), new Abdomen(
	        ABDOMEN_RADIUS, this.glut), ABDOMEN_NAME);

	    // The head is the parent of all other components.
	    this.addChild(this.head);
	    
	    // The children of the head are both eyes and the first thorax.
	    this.head.addChildren(this.eye_one, this.eye_two, this.thorax_one);
	    
	    // The child of each eye is its pupil.
	    this.eye_one.addChild(this.pupil_one);
	    this.eye_two.addChild(this.pupil_two);
	    
	    // The child of the first thorax is the second thorax. 
	    this.thorax_one.addChildren(this.thorax_two);
	    
	    // The children of the second thorax are all of the leg joints. 
	    this.thorax_two.addChildren(this.abdomen, prox1, prox2, prox3, prox4, prox5, prox6);
	    
	    // All proximal joints have the middle joint as the child
	    // and each middle joint has the distal joint as a child.
	    prox1.addChild(middle1);
	    prox2.addChild(middle2);
	    prox3.addChild(middle3);
	    prox4.addChild(middle4);
	    prox5.addChild(middle5);
	    prox6.addChild(middle6);
	    middle1.addChild(distal1);
	    middle2.addChild(distal2);
	    middle3.addChild(distal3);
	    middle4.addChild(distal4);
	    middle5.addChild(distal5);
	    middle6.addChild(distal6);
	    
	    // Turn the entire bug 180 degrees because I designed it backwards.
	    this.rotate(Axis.Y, 180);
	    
	    // Set rotation limits for the head.
        this.head.setXPositiveExtent(0);
        this.head.setXNegativeExtent(0);
        this.head.setYPositiveExtent(12);
        this.head.setYNegativeExtent(-12);
        this.head.setZPositiveExtent(0);
        this.head.setZNegativeExtent(0);

	    // Set rotation limits for the first thorax.
	    this.thorax_one.setXPositiveExtent(15);
	    this.thorax_one.setXNegativeExtent(-15);
	    this.thorax_one.setYPositiveExtent(20);
	    this.thorax_one.setYNegativeExtent(-20);
	    this.thorax_one.setZPositiveExtent(0);
	    this.thorax_one.setZNegativeExtent(0);
	    
	    // Set rotation limits for the second thorax.
        this.thorax_two.setXPositiveExtent(10);
        this.thorax_two.setXNegativeExtent(-10);
        this.thorax_two.setYPositiveExtent(10);
        this.thorax_two.setYNegativeExtent(-10);
        this.thorax_two.setZPositiveExtent(0);
        this.thorax_two.setZNegativeExtent(0);
        
        // Set rotation limits for the abdomen.
        this.abdomen.setXPositiveExtent(30);
        this.abdomen.setXNegativeExtent(-30);
        this.abdomen.setYPositiveExtent(30);
        this.abdomen.setYNegativeExtent(-30);
        this.abdomen.setZPositiveExtent(0);
        this.abdomen.setZNegativeExtent(-0);


	    // Set rotation limits for the left proximal joints of the legs.
	    for (final Component proximalJoint : Arrays.asList(prox1, prox2, prox3)) {
	      proximalJoint.setXPositiveExtent(10);
	      proximalJoint.setXNegativeExtent(-25);
	      proximalJoint.setYPositiveExtent(5);
	      proximalJoint.setYNegativeExtent(-20);
	      proximalJoint.setZPositiveExtent(10);
	      proximalJoint.setZNegativeExtent(-10);
	    }
	    
	    // Set rotation limits for the right proximal joints of the legs.
	    for (final Component proximalJoint : Arrays.asList(prox4, prox5, prox6)) {
          proximalJoint.setXPositiveExtent(10);
          proximalJoint.setXNegativeExtent(-25);
          proximalJoint.setYPositiveExtent(-10);
          proximalJoint.setYNegativeExtent(15);
          proximalJoint.setZPositiveExtent(-10);
          proximalJoint.setZNegativeExtent(10);
        }
	    
	    // Set rotation limits for the left middle joints of the legs.
	    for (final Component middleJoint : Arrays.asList(middle1, middle2, middle3)) {
	      middleJoint.setXPositiveExtent(45);
	      middleJoint.setXNegativeExtent(-45);
	      middleJoint.setYPositiveExtent(25);
	      middleJoint.setYNegativeExtent(-10);
	      middleJoint.setZPositiveExtent(80);
	      middleJoint.setZNegativeExtent(-20);
	    }
	    
	    // Set rotation limits for the right middle joints of the legs.
	    for (final Component middleJoint : Arrays.asList(middle4, middle5, middle6)) {
          middleJoint.setXPositiveExtent(45);
          middleJoint.setXNegativeExtent(-45);
          middleJoint.setYPositiveExtent(-25);
          middleJoint.setYNegativeExtent(10);
          middleJoint.setZPositiveExtent(-80);
          middleJoint.setZNegativeExtent(20);
        }
	    
	    // Set rotation limits for the left distal joints of the legs.
	    for (final Component distalJoint : Arrays.asList(distal1, distal2, distal3)) {
	      distalJoint.setXPositiveExtent(50);
	      distalJoint.setXNegativeExtent(0);
	      distalJoint.setYPositiveExtent(40);
	      distalJoint.setYNegativeExtent(-20);
	      distalJoint.setZPositiveExtent(80);
	      distalJoint.setZNegativeExtent(-20);
	    }
	    
	    // Set rotation limits for the right distal joints of the legs.
	    for (final Component distalJoint : Arrays.asList(distal4, distal5, distal6)) {
          distalJoint.setXPositiveExtent(50);
          distalJoint.setXNegativeExtent(0);
          distalJoint.setYPositiveExtent(-40);
          distalJoint.setYNegativeExtent(20);
          distalJoint.setZPositiveExtent(-80);
          distalJoint.setZNegativeExtent(20);
        }

	    // Create the list of all the components for debugging purposes.
	    this.components = Arrays.asList(prox1, middle1, distal1, prox2, middle2,
	        distal2, prox3, middle3, distal3, prox4, middle4, distal4, prox5,
	        middle5, distal5, prox6, middle6, distal6, this.head, 
	        this.thorax_one, this.thorax_two, this.abdomen, this.eye_one, this.eye_two,
	        this.pupil_one, this.pupil_two);
	}
	
	private class Leg {
	    /** The distal joint of this leg. */
	    private final Component distalJoint;
	    /** The middle joint of this leg. */
        private final Component middleJoint;
        /** The palm joint of this leg. */
        private final Component proximalJoint;
	    /** The list of all the joints in this leg. */
	    private final List<Component> joints;
	    

	    /**
	     * Instantiates this leg with the three specified joints.
	     * 
	     * @param proximalJoint
	     *          The proximal joint of this leg.
	     * @param middleJoint
	     *          The middle joint of this leg.
	     * @param distalJoint
	     *          The distal joint of this leg.
	     */
	    public Leg(final Component proximalJoint, final Component middleJoint,
	        final Component distalJoint) {
	      this.proximalJoint = proximalJoint;
	      this.middleJoint = middleJoint;
	      this.distalJoint = distalJoint;

	      this.joints = Collections.unmodifiableList(Arrays.asList(this.proximalJoint,
	          this.middleJoint, this.distalJoint));
	    }

	    /**
	     * Gets the distal joint of this leg.
	     * 
	     * @return The distal joint of this leg.
	     */
	    Component distalJoint() {
	      return this.distalJoint;
	    }

	    /**
	     * Gets an unmodifiable view of the list of the joints of this leg.
	     * 
	     * @return An unmodifiable view of the list of the joints of this leg.
	     */
	    List<Component> joints() {
	      return this.joints;
	    }

	    /**
	     * Gets the middle joint of this leg.
	     * 
	     * @return The middle joint of this leg.
	     */
	    Component middleJoint() {
	      return this.middleJoint;
	    }

	    /**
	     * Gets the palm joint of this leg.
	     * 
	     * @return The palm joint of this leg.
	     */
	    Component proximalJoint() {
	      return this.proximalJoint;
	    }
	}


}

