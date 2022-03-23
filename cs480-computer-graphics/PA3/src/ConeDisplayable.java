/**
 * The implementation for a cone object.
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

public class ConeDisplayable implements Displayable{
  private int callListHandle;
  private double angle;
  private GLUquadric qd;
  private double radius;
  private double height;
  private double type; // Type determines shape modifications for specific body part
  // Type 0: Bird wings
  // Type 1: Beak
  
  
  public ConeDisplayable(final double radius, final double height, final double angle, final double type) {
      this.radius = radius;
      this.height = height;
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
        gl.glRotated(angle, 0, 1, 0);
        gl.glScalef(0.7f, 0.5f, 1);
        glut.glutSolidCone(radius, height, 36, 18);
      }
      if(type == 1) {
        gl.glRotated(angle, 0, 1, 0);
        gl.glScalef(0.1f, 0.1f, 0.1f);
        glut.glutSolidCone(radius, height, 36, 18);
      }
      gl.glPopMatrix();

      gl.glEndList();
  }

}