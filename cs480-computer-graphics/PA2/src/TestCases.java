/**
 * Boiler Plate:
 * Farheen Rahman U67393642
 * Programming Assignment 2
 * 10/15/19
 */

import java.util.HashMap;
import java.util.Map;

public class TestCases extends CyclicIterator<Map<String, Configuration>> {

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

	Map<String, Configuration> rest() {
		return this.rest;
	}

	private final Map<String, Configuration> rest;

	@SuppressWarnings("unchecked")
	TestCases() {
		this.rest = new HashMap<String, Configuration>();
		final Map<String, Configuration> scared = new HashMap<String, Configuration>();
		final Map<String, Configuration> clenched = new HashMap<String, Configuration>();
		final Map<String, Configuration> turn = new HashMap<String, Configuration>();
		final Map<String, Configuration> spread = new HashMap<String, Configuration>();
		final Map<String, Configuration> attack = new HashMap<String, Configuration>();

		super.add(rest, scared, clenched, turn, spread, attack);

		// The head does not change through any of the test cases.
		rest.put(HEAD_NAME, new BaseConfiguration(0, 0, 0));
        scared.put(HEAD_NAME, new BaseConfiguration(0, 0, 0));
        clenched.put(HEAD_NAME, new BaseConfiguration(0, 0, 0));
        turn.put(HEAD_NAME, new BaseConfiguration(0, 0, 0));
        spread.put(HEAD_NAME, new BaseConfiguration(0, 0, 0));
        attack.put(HEAD_NAME, new BaseConfiguration(0, 0, 0));
        
        rest.put(THORAX_ONE_NAME, new BaseConfiguration(0, 0, 0));
        scared.put(THORAX_ONE_NAME, new BaseConfiguration(-1.0, 0, 0));
        clenched.put(THORAX_ONE_NAME, new BaseConfiguration(-1.0, 0, 0));
        turn.put(THORAX_ONE_NAME, new BaseConfiguration(0, 20.0, 0));
        spread.put(THORAX_ONE_NAME, new BaseConfiguration(0, 0, 0));
        attack.put(THORAX_ONE_NAME, new BaseConfiguration(15.0, 0, 0));
        
        rest.put(THORAX_TWO_NAME, new BaseConfiguration(0, 0, 0));
        scared.put(THORAX_TWO_NAME, new BaseConfiguration(4.0, 0, 0));
        clenched.put(THORAX_TWO_NAME, new BaseConfiguration(4.0, 0, 0));
        turn.put(THORAX_TWO_NAME, new BaseConfiguration(0, 10.0, 0));
        spread.put(THORAX_TWO_NAME, new BaseConfiguration(0, 0, 0));
        attack.put(THORAX_TWO_NAME, new BaseConfiguration(10.0, 0, 0));
		
		rest.put(ABDOMEN_NAME, new BaseConfiguration(0, 0, 0));
		scared.put(ABDOMEN_NAME, new BaseConfiguration(12.0, 0, 0));
		clenched.put(ABDOMEN_NAME, new BaseConfiguration(-22.0, -4.0, 0));
		turn.put(ABDOMEN_NAME, new BaseConfiguration(0, 26.0, 0));
		spread.put(ABDOMEN_NAME, new BaseConfiguration(0, 0, 0));
		attack.put(ABDOMEN_NAME, new BaseConfiguration(0, 0, 0));

		

		// The rest test case
		rest.put(LEG_ONE_DISTAL_NAME, new BaseConfiguration(0, 0, 0));
		rest.put(LEG_ONE_MIDDLE_NAME, new BaseConfiguration(0, 0, 0));
		rest.put(LEG_ONE_PROXIMAL_NAME, new BaseConfiguration(0, 0, 0));
		rest.put(LEG_TWO_DISTAL_NAME, new BaseConfiguration(0, 0, 0));
        rest.put(LEG_TWO_MIDDLE_NAME, new BaseConfiguration(0, 0, 0));
        rest.put(LEG_TWO_PROXIMAL_NAME, new BaseConfiguration(0, 0, 0));
        rest.put(LEG_THREE_DISTAL_NAME, new BaseConfiguration(0, 0, 0));
        rest.put(LEG_THREE_MIDDLE_NAME, new BaseConfiguration(0, 0, 0));
        rest.put(LEG_THREE_PROXIMAL_NAME, new BaseConfiguration(0, 0, 0));
        rest.put(LEG_FOUR_DISTAL_NAME, new BaseConfiguration(0, 0, 0));
        rest.put(LEG_FOUR_MIDDLE_NAME, new BaseConfiguration(0, 0, 0));
        rest.put(LEG_FOUR_PROXIMAL_NAME, new BaseConfiguration(0, 0, 0));
        rest.put(LEG_FIVE_DISTAL_NAME, new BaseConfiguration(0, 0, 0));
        rest.put(LEG_FIVE_MIDDLE_NAME, new BaseConfiguration(0, 0, 0));
        rest.put(LEG_FIVE_PROXIMAL_NAME, new BaseConfiguration(0, 0, 0));
        rest.put(LEG_SIX_DISTAL_NAME, new BaseConfiguration(0, 0, 0));
        rest.put(LEG_SIX_MIDDLE_NAME, new BaseConfiguration(0, 0, 0));
        rest.put(LEG_SIX_PROXIMAL_NAME, new BaseConfiguration(0, 0, 0));


		// The scared test case
        scared.put(LEG_ONE_DISTAL_NAME, new BaseConfiguration(0.0, -20.0, -2.0));
        scared.put(LEG_ONE_MIDDLE_NAME, new BaseConfiguration(-45.0, -10.0, -2.0));
        scared.put(LEG_ONE_PROXIMAL_NAME, new BaseConfiguration(-25.0, -20.0, 8.0));
        scared.put(LEG_TWO_DISTAL_NAME, new BaseConfiguration(0, 0, 22.0));
        scared.put(LEG_TWO_MIDDLE_NAME, new BaseConfiguration(0, 0, 22.0));
        scared.put(LEG_TWO_PROXIMAL_NAME, new BaseConfiguration(0.0, 0.0, 10.0));
        scared.put(LEG_THREE_DISTAL_NAME, new BaseConfiguration(0, 0, 34.0));
        scared.put(LEG_THREE_MIDDLE_NAME, new BaseConfiguration(0, 0, 34.0));
        scared.put(LEG_THREE_PROXIMAL_NAME, new BaseConfiguration(0, 0, 10.0));
        scared.put(LEG_FOUR_DISTAL_NAME, new BaseConfiguration(0, 0, -34.0));
        scared.put(LEG_FOUR_MIDDLE_NAME, new BaseConfiguration(0, 0, -34.0));
        scared.put(LEG_FOUR_PROXIMAL_NAME, new BaseConfiguration(0, 0, -10.0));
        scared.put(LEG_FIVE_DISTAL_NAME, new BaseConfiguration(0, 0, -22.0));
        scared.put(LEG_FIVE_MIDDLE_NAME, new BaseConfiguration(0, 0, -22.0));
        scared.put(LEG_FIVE_PROXIMAL_NAME, new BaseConfiguration(0, 0, -10.0));
        scared.put(LEG_SIX_DISTAL_NAME, new BaseConfiguration(0.0, 20.0, 2.0));
        scared.put(LEG_SIX_MIDDLE_NAME, new BaseConfiguration(-45.0, 10.0, 2.0));
        scared.put(LEG_SIX_PROXIMAL_NAME, new BaseConfiguration(-25.0, 15.0, -8.0));

        
        // The clenched test case
        clenched.put(LEG_ONE_DISTAL_NAME, new BaseConfiguration(44.0, -20.0, -2.0));
        clenched.put(LEG_ONE_MIDDLE_NAME, new BaseConfiguration( 21.0, -10.0, -2.0));
        clenched.put(LEG_ONE_PROXIMAL_NAME, new BaseConfiguration(4.0, -20.0, 8.0));
        clenched.put(LEG_TWO_DISTAL_NAME, new BaseConfiguration(10.0, 40.0, 28.0));
        clenched.put(LEG_TWO_MIDDLE_NAME, new BaseConfiguration(2.0, 25.0, 28.0));
        clenched.put(LEG_TWO_PROXIMAL_NAME, new BaseConfiguration(-8.0, 5.0, 2.0));
        clenched.put(LEG_THREE_DISTAL_NAME, new BaseConfiguration(8.0, 38.0, -2.0));
        clenched.put(LEG_THREE_MIDDLE_NAME, new BaseConfiguration(-2.0, 25.0, -2.0));
        clenched.put(LEG_THREE_PROXIMAL_NAME, new BaseConfiguration(-2.0, 5.0, -10.0));
        clenched.put(LEG_FOUR_DISTAL_NAME, new BaseConfiguration(8.0, -38.0, 2.0));
        clenched.put(LEG_FOUR_MIDDLE_NAME, new BaseConfiguration(-2.0, -25.0, 2.0));
        clenched.put(LEG_FOUR_PROXIMAL_NAME, new BaseConfiguration(-2.0, -10.0, 10.0));
        clenched.put(LEG_FIVE_DISTAL_NAME, new BaseConfiguration(10.0, -40.0, -28.0));
        clenched.put(LEG_FIVE_MIDDLE_NAME, new BaseConfiguration(2.0, -25.0, -28.0));
        clenched.put(LEG_FIVE_PROXIMAL_NAME, new BaseConfiguration(-8.0, -10.0, -2.0));
        clenched.put(LEG_SIX_DISTAL_NAME, new BaseConfiguration(44.0, 20.0, 2.0));
        clenched.put(LEG_SIX_MIDDLE_NAME, new BaseConfiguration(21.0, 10.0, 2.0));
        clenched.put(LEG_SIX_PROXIMAL_NAME, new BaseConfiguration(4.0, 15.0, -8.0));

        
        // The turn test case
        turn.put(LEG_ONE_DISTAL_NAME, new BaseConfiguration(30.0, 0, 0));
        turn.put(LEG_ONE_MIDDLE_NAME, new BaseConfiguration(30.0, 0, 0));
        turn.put(LEG_ONE_PROXIMAL_NAME, new BaseConfiguration(10.0, 0, 0));
        turn.put(LEG_TWO_DISTAL_NAME, new BaseConfiguration(30.0, 0, 0));
        turn.put(LEG_TWO_MIDDLE_NAME, new BaseConfiguration(30.0, 0, 0));
        turn.put(LEG_TWO_PROXIMAL_NAME, new BaseConfiguration(10.0, 0, 0));
        turn.put(LEG_THREE_DISTAL_NAME, new BaseConfiguration(30.0, 0, 0));
        turn.put(LEG_THREE_MIDDLE_NAME, new BaseConfiguration(30.0, 0, 0));
        turn.put(LEG_THREE_PROXIMAL_NAME, new BaseConfiguration(10.0, 0, 0));
        turn.put(LEG_FOUR_DISTAL_NAME, new BaseConfiguration(8.0, 16.0, 0));
        turn.put(LEG_FOUR_MIDDLE_NAME, new BaseConfiguration(8.0, 6.0, 0));
        turn.put(LEG_FOUR_PROXIMAL_NAME, new BaseConfiguration(8.0, 11.0, 0));
        turn.put(LEG_FIVE_DISTAL_NAME, new BaseConfiguration(8.0, 16.0, 0));
        turn.put(LEG_FIVE_MIDDLE_NAME, new BaseConfiguration(8.0, 6.0, 0));
        turn.put(LEG_FIVE_PROXIMAL_NAME, new BaseConfiguration(8.0, 11.0, 0));
        turn.put(LEG_SIX_DISTAL_NAME, new BaseConfiguration(8.0, 16.0, 0));
        turn.put(LEG_SIX_MIDDLE_NAME, new BaseConfiguration(8.0, 6.0, 0));
        turn.put(LEG_SIX_PROXIMAL_NAME, new BaseConfiguration(8.0, 11.0, 0));
        

        // The spread test case
        spread.put(LEG_ONE_DISTAL_NAME, new BaseConfiguration(0.0, 40.0, -20.0));
        spread.put(LEG_ONE_MIDDLE_NAME, new BaseConfiguration(-30.0, 25.0, -20.0));
        spread.put(LEG_ONE_PROXIMAL_NAME, new BaseConfiguration(-25.0, 5.0, -10.0));
        spread.put(LEG_TWO_DISTAL_NAME, new BaseConfiguration(0.0, 40.0, -20.0));
        spread.put(LEG_TWO_MIDDLE_NAME, new BaseConfiguration(-30.0, 25.0, -20.0));
        spread.put(LEG_TWO_PROXIMAL_NAME, new BaseConfiguration(-25.0, 5.0, -10.0));
        spread.put(LEG_THREE_DISTAL_NAME, new BaseConfiguration(0.0, 40.0, -20.0));
        spread.put(LEG_THREE_MIDDLE_NAME, new BaseConfiguration(-30.0, 25.0, -20.0));
        spread.put(LEG_THREE_PROXIMAL_NAME, new BaseConfiguration(-25.0, 5.0, -10.0));
        spread.put(LEG_FOUR_DISTAL_NAME, new BaseConfiguration(0.0, -40.0, 20.0));
        spread.put(LEG_FOUR_MIDDLE_NAME, new BaseConfiguration(-30.0, -25.0, 20.0));
        spread.put(LEG_FOUR_PROXIMAL_NAME, new BaseConfiguration(-25.0, -5.0, 10.0));
        spread.put(LEG_FIVE_DISTAL_NAME, new BaseConfiguration(0.0, -40.0, 20.0));
        spread.put(LEG_FIVE_MIDDLE_NAME, new BaseConfiguration(-30.0, -25.0, 20.0));
        spread.put(LEG_FIVE_PROXIMAL_NAME, new BaseConfiguration(-25.0, -5.0, 10.0));
        spread.put(LEG_SIX_DISTAL_NAME, new BaseConfiguration(0.0, -40.0, 20.0));
        spread.put(LEG_SIX_MIDDLE_NAME, new BaseConfiguration(-30.0, -25.0, 20.0));
        spread.put(LEG_SIX_PROXIMAL_NAME, new BaseConfiguration(-25.0, -5.0, 10.0));
        
        
        // The attack test case
        attack.put(LEG_ONE_DISTAL_NAME, new BaseConfiguration(0.0, -20.0, -20.0));
        attack.put(LEG_ONE_MIDDLE_NAME, new BaseConfiguration(0.0, -10.0, -20.0));
        attack.put(LEG_ONE_PROXIMAL_NAME, new BaseConfiguration(0.0, -20.0, -10.0));
        attack.put(LEG_TWO_DISTAL_NAME, new BaseConfiguration(24.0, 0.0, 0.0));
        attack.put(LEG_TWO_MIDDLE_NAME, new BaseConfiguration(22.0, 0.0, 0.0));
        attack.put(LEG_TWO_PROXIMAL_NAME, new BaseConfiguration(10.0, 0.0, 0.00));
        attack.put(LEG_THREE_DISTAL_NAME, new BaseConfiguration(20.0, 0.0, 0.0));
        attack.put(LEG_THREE_MIDDLE_NAME, new BaseConfiguration(20.0, 0.0, 0.0));
        attack.put(LEG_THREE_PROXIMAL_NAME, new BaseConfiguration(10.0, 0.0, 0.0));
        attack.put(LEG_FOUR_DISTAL_NAME, new BaseConfiguration(20.0, 0.0, 0.0));
        attack.put(LEG_FOUR_MIDDLE_NAME, new BaseConfiguration(20.0, 0.0, 0.0));
        attack.put(LEG_FOUR_PROXIMAL_NAME, new BaseConfiguration(10.0, 0.0, 0.0));
        attack.put(LEG_FIVE_DISTAL_NAME, new BaseConfiguration(24.0, 0.0, 0.0));
        attack.put(LEG_FIVE_MIDDLE_NAME, new BaseConfiguration(22.0, 0.0, 0.0));
        attack.put(LEG_FIVE_PROXIMAL_NAME, new BaseConfiguration(10.0, 0.0, 0.0));
        attack.put(LEG_SIX_DISTAL_NAME, new BaseConfiguration(0.0, 20.0, 20.0));
        attack.put(LEG_SIX_MIDDLE_NAME, new BaseConfiguration(0.0, 10.0, 20.0));
        attack.put(LEG_SIX_PROXIMAL_NAME, new BaseConfiguration(0.0, 15.0, 10.0));
        
        
	}
}
