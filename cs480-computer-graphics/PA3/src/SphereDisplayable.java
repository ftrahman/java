/**
 * The implementation for a sphere object.
 * 
 * Boiler Plate:
 * Farheen Rahman U67393642
 * Programming Assignment 3
 * 11/12/19
 * 
 * 
 */

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import com.jogamp.opengl.util.gl2.GLUT;

public class SphereDisplayable implements Displayable{
  private int callListHandle;
  private double type;      // Type determines shape modifications we make for specific body parts
  private GLUquadric qd;
  private double radius;
  private double angle;
  // Type 0: bird body
  // Type 1: head
  // Type 2: tail
  // Type 3: bee body
  // Type 4: food
  // Type 5: stripes
  // Type 6: bee wings
  // Type 7: feathers
  
  public SphereDisplayable(final double radius, final double type, final double angle) {
      this.radius = radius;
      this.type = type;
      this.angle = angle;
  }
  
  /*
   * Method to be called for data retrieving
   * 
   * */
  @Override
  public void draw(GL2 gl) {
      gl.glCallList(this.callListHandle);
  }

  /*
   * Initialize our example model and store it in display list
   * 
   * */
  @Override
  public void initialize(GL2 gl) {
      this.callListHandle = gl.glGenLists(1);
      gl.glNewList(this.callListHandle, GL2.GL_COMPILE);
      
      GLU glu = new GLU();
      this.qd = glu.gluNewQuadric();
      GLUT glut = new GLUT();
      
      gl.glPushMatrix();
      if(type == 0) {
        gl.glScalef(0.4f, 0.4f, 0.9f);
        glut.glutSolidSphere(radius, 36, 18);
      }
      if(type == 1) {
        gl.glScalef(0.5f, 0.5f, 0.6f);
        glut.glutSolidSphere(radius, 36, 18);
      }
      if(type == 2) {
        gl.glRotated(angle, 0, 1, 0);
        gl.glScalef(0.35f, 0.2f, 0.7f);
        glut.glutSolidSphere(radius, 36, 18);
      }
      if(type == 3) {
        gl.glScalef(0.5f, 0.5f, 0.6f);
        glut.glutSolidSphere(radius, 36, 18);
      }
      if(type == 4) {
        gl.glScalef(0.2f, 0.2f, 0.2f);
        glut.glutSolidSphere(radius, 36, 18);
      }
      if(type == 5) {
        gl.glScalef(0.2f, 0.55f, 0.6f);
        glut.glutSolidSphere(radius, 36, 18);
      }
      if(type == 6) {
        gl.glRotated(angle, 1, 0, 0);
        gl.glScalef(0.2f, 0.3f, 0.1f);
        glut.glutSolidSphere(radius, 36, 18);
      }
      if(type == 7) {
        gl.glRotated(angle, 0, 1, 0);
        gl.glScalef(0.5f, 0.1f, 0.7f);
        glut.glutSolidSphere(radius, 36, 18);
      }

      gl.glPopMatrix();

      gl.glEndList();
  }

}