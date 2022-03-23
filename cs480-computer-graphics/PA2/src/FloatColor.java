/**
 * FloatColor.java - a Color which easily allows getting components as floats
 */

import java.awt.Color;

/**
 * A Color object which allows getting the component values as floats.
 * 
 * Boiler Plate:
 * Farheen Rahman U67393642
 * Programming Assignment 2
 * 10/15/19
 */
public class FloatColor extends Color {

	/** The color orange. */
	public static final FloatColor ORANGE = new FloatColor(0.8f, 0.5f, 0.2f);
	/** The color red. */
	public static final FloatColor RED = new FloatColor(1, 0, 0);
	// Color auburn of the ant.
	public static final FloatColor AUBURN = new FloatColor(0.33f, 0.09f, 0.08f);
	// Color for the iris.
	public static final FloatColor WHITE = new FloatColor(1, 1, 1);
	// Color for the pupil.
	public static final FloatColor BLACK = new FloatColor(0, 0, 0);
	/** Randomly generated serial version UID. */
	private static final long serialVersionUID = 6281360072954213961L;
	/** The value of the blue component of this color as a float between 0 and 1. */
	private final float blue;
	/**
	 * The value of the green component of this color as a float between 0 and 1.
	 */
	private final float green;
	/** The value of the red component of this color as a float between 0 and 1. */
	private final float red;

	/**
	 * Creates a FloatColor object with the same component values of the specified
	 * Color object.
	 * 
	 * @param color The color from which to get component values.
	 */
	private FloatColor(final Color color) {
		super(color.getRGB());
		final float[] components = new float[3];
		super.getColorComponents(components);
		this.red = components[0];
		this.green = components[1];
		this.blue = components[2];
	}

	/**
	 * Instantiates this color with the specified red, green, and blue values as
	 * {@code float}s between 0 and 1.
	 * 
	 * @param red   The value of the red component of this color as a float between
	 *              0 and 1.
	 * @param green The value of the green component of this color as a float
	 *              between 0 and 1.
	 * @param blue  The value of the blue component of this color as a float between
	 *              0 and 1.
	 */
	private FloatColor(final float red, final float green, final float blue) {
		this(new Color(red, green, blue));
	}

	/**
	 * Gets the value of the blue component of this color as a float between 0 and
	 * 1.
	 * 
	 * @return The value of the blue component of this color as a float between 0
	 *         and 1.
	 */
	public float blue() {
		return this.blue;
	}

	/**
	 * Gets the value of the green component of this color as a float between 0 and
	 * 1.
	 * 
	 * @return The value of the green component of this color as a float between 0
	 *         and 1.
	 */
	public float green() {
		return this.green;
	}

	/**
	 * Gets the value of the red component of this color as a float between 0 and 1.
	 * 
	 * @return The value of the red component of this color as a float between 0 and
	 *         1.
	 */
	public float red() {
		return this.red;
	}
}
