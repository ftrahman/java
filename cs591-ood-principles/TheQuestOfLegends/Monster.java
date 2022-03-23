/*
 * Monster: An abstract class that extends Player, and defines a Monster object.
 * 
 * public Monster(): Call to the super constructor Player.
 * public String toString(): Provide a string representation of the Monster object.
 */


import java.util.ArrayList;
import java.util.List;


abstract class Monster extends Player {
  double damage;
  double defense;
  double dodge;
  
  Monster(){
    super();
  }
  
  public String toString() {
    String header = String.format("%-22s%-10s%-10s%-10s%-10s%-10s\n", "Name", "HP", "Level", "Damage", "Defense", "Dodge");
    String values = String.format("%-22s%-10d%-10d%-10d%-10d%-10d\n", name, (long)HP, level, (long)damage, (long)defense, (long)dodge);
    return AnsiColors.ANSI_YELLOW + header + AnsiColors.ANSI_RESET + values;
      }
  
}
