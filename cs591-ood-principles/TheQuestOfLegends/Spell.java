/*
 * Spell: Parent abstract class for other types of spells.
 * 
 * public double calculateDamage(Hero currentHero): Find the damage of the spell.
 * public String toString(): String representation.
 */
abstract class Spell extends Item {
  static double baseDamage;
  double manaCost;
  double finalDamage;
  
  public Spell() {
    super();
  }
  
  
  public double calculateDamage(Hero currentHero) {
    finalDamage = (baseDamage * (currentHero.dexterity/1000)) + baseDamage;
    return finalDamage;
  }
  
 
  public String toString() {
    String values = String.format("%-1s%-15d%-15d%-20s%-24s", AnsiColors.ANSI_BLUE, (long)manaCost, (long)baseDamage , AnsiColors.ANSI_RESET + "-" , "-");
    return super.toString() + values;
  }
  
}
