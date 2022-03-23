/**
 *  Limb modeled by a solid cylinder with a rounded top used to model all legs joints of the ant.
 * 
 * Boiler Plate:
 * Farheen Rahman U67393642
 * Programming Assignment 2
 * 10/15/19
 * 
 */

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.gl2.GLUT;//for new version of gl

public class Limb extends Circular implements Displayable {
	/**
	 * The default number of slices to use when drawing the cylinder and the sphere.
	 */
	public static final int DEFAULT_SLICES = 36;
	/**
	 * The default number of stacks to use when drawing the cylinder and the sphere.
	 */
	public static final int DEFAULT_STACKS = 28;

	/**
	 * The OpenGL handle to the display list which contains all the components which
	 * comprise this cylinder.
	 */
	private int callListHandle;
	/** The height of this cylinder. */
	private final double height;
	
	private final double angle;
	
	private final int type;

	/**
	 * Instantiates this object with the specified radius and height of the
	 * cylinder, and the GLUT object to use for drawing the cylinder and the sphere
	 * at the top.
	 * 
	 * @param radius The radius of this cylinder.
	 * @param height The height of this cylinder.
	 * @param angle  The angle by which to rotate the cylinder.
	 * @param type   The type of rotation to apply to a given cylinder.
	 * @param glut   The OpenGL utility toolkit object to use to draw the cylinder
	 *               and the sphere at the top.
	 */
	public Limb(final double radius, final double height, final double angle, final int type, final GLUT glut) {
		super(radius, glut);
		this.height = height;
		this.angle = angle;
		this.type = type;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param gl {@inheritDoc}
	 * @see edu.bu.cs.cs480.Displayable#draw(javax.media.opengl.GL)
	 */
	@Override
	public void draw(final GL2 gl) {
		gl.glCallList(this.callListHandle);
	}

	/**
	 * {@inheritDoc}
	 * 
	 *  Type 1: Rotation for left proximal joint.
	 *  Type 2: Rotation for right proximal joint.
	 *  Type 3: Rotation for left middle joint.
	 *  Type 4: Rotation for right middle joint.
	 *  Type 5: Rotation for distal joints.
	 * 
	 * @param gl {@inheritDoc}
	 * @see edu.bu.cs.cs480.Displayable#initialize(javax.media.opengl.GL)
	 */
	@Override
	public void initialize(final GL2 gl) {
	  if(type == 1) {
	      this.callListHandle = gl.glGenLists(1);
	      gl.glNewList(this.callListHandle, GL2.GL_COMPILE);
	     
	      // First position the cylinder the given angle with specified rotation.
	      gl.glRotated(angle, 1, -1, 0);
	      this.glut().glutSolidCylinder(this.radius(), this.height, DEFAULT_SLICES, DEFAULT_STACKS);

	      // Then position the sphere to round out the cylinder at the given angle with specified rotation.
	      gl.glPushMatrix();
	      gl.glTranslated(0, 0, this.height);
	      gl.glRotated(angle, 1, -1, 0);
	      this.glut().glutSolidSphere(this.radius(), DEFAULT_SLICES, DEFAULT_STACKS);
	      gl.glPopMatrix();

	      gl.glEndList();
	      }
	  if(type == 2) {
        this.callListHandle = gl.glGenLists(1);
        gl.glNewList(this.callListHandle, GL2.GL_COMPILE);
       
        gl.glRotated(angle, 1, 1, 0);
        this.glut().glutSolidCylinder(this.radius(), this.height, DEFAULT_SLICES, DEFAULT_STACKS);

        gl.glPushMatrix();
        gl.glTranslated(0, 0, this.height);
        gl.glRotated(angle, 1, 1, 0);
        this.glut().glutSolidSphere(this.radius(), DEFAULT_SLICES, DEFAULT_STACKS);
        gl.glPopMatrix();

        gl.glEndList();
        }
	  if(type == 3) {
        this.callListHandle = gl.glGenLists(1);
        gl.glNewList(this.callListHandle, GL2.GL_COMPILE);
       
        gl.glRotated(angle, -1, -1, 0);
        this.glut().glutSolidCylinder(this.radius(), this.height, DEFAULT_SLICES, DEFAULT_STACKS);

        gl.glPushMatrix();
        gl.glTranslated(0, 0, this.height);
        gl.glRotated(angle, -1, -1, 0);
        this.glut().glutSolidSphere(this.radius(), DEFAULT_SLICES, DEFAULT_STACKS);
        gl.glPopMatrix();

        gl.glEndList();
        }
	  if(type == 4) {
        this.callListHandle = gl.glGenLists(1);
        gl.glNewList(this.callListHandle, GL2.GL_COMPILE);
       
        gl.glRotated(angle, -1, 1, 0);
        this.glut().glutSolidCylinder(this.radius(), this.height, DEFAULT_SLICES, DEFAULT_STACKS);

        gl.glPushMatrix();
        gl.glTranslated(0, 0, this.height);
        gl.glRotated(angle, -1, 1, 0);
        this.glut().glutSolidSphere(this.radius(), DEFAULT_SLICES, DEFAULT_STACKS);
        gl.glPopMatrix();

        gl.glEndList();
	    }
	  if(type == 5) {
        this.callListHandle = gl.glGenLists(1);
        gl.glNewList(this.callListHandle, GL2.GL_COMPILE);
       
        gl.glRotated(angle, 1, 0, 0);
        this.glut().glutSolidCylinder(this.radius(), this.height, DEFAULT_SLICES, DEFAULT_STACKS);

        gl.glPushMatrix();
        gl.glTranslated(0, 0, this.height);
        gl.glRotated(angle, 1, 0, 0);
        this.glut().glutSolidSphere(this.radius(), DEFAULT_SLICES, DEFAULT_STACKS);
        gl.glPopMatrix();

        gl.glEndList();
      }
    }
}
