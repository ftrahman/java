/*
 * Hero: An abstract class, that is a subclass of Player, that contains the requirements for a Quest Hero.
 * 
 * public Hero(): Initialize all heroes using the super constructor.
 * void calculateMana(): Getter method to calculate mana.
 * void calculateDamage(): Getter method to calculate damage with a weapon.
 * void calculateDodge(): Getter method to calculate the dodge chance.
 * void calculateGoalXP(): Getter method for goal XP.
 * void setCurrentWeapon(Weapon current): Setter for weapon.
 * void setCurrentArmor(Armor current): Setter for armor.
 * public void changeArmor(): Allows to go through inventory and assign new weapons.
 * private String weaponString(): Helper method to get Weapon.
 * private String armorString(): Helper method to get Armor.
 * private String potionString(): Helper method to get Potion.
 * private String spellString(): Helper method to get Spell.
 * 
 */

import java.util.Scanner;

abstract class Hero extends Player {
  double strength; 
  double dexterity; 
  double agility; 
  double money;
  int currentXP;
  int goalXP;
  double damage;
  double dodge;
  double defense;
  double currentMana;
  Weapon currentWeapon;
  Spell currentSpell;
  Potion currentPotion;
  Armor currentArmor;
  Inventory curInventory = new Inventory();
  Scanner input = new Scanner(System.in);
  Scanner input2 = new Scanner(System.in);
  boolean replace = false;
  
  
  
  public Hero(){
    super();
    currentWeapon = null; currentArmor = null;
    currentPotion = null; currentSpell = null;
  }
  
  void calculateMana() {
    currentMana = currentMana + (currentMana*0.1);
  }
  
  void calculateDamage() {
    damage = (strength + currentWeapon.damage)*0.05;
  }
  
  void calculateDodge() {
    dodge = agility*0.02;
  }
  
  void calculateGoalXP() {
    goalXP = level * 10;
  }
  
  void setCurrentWeapon(Weapon current) {
    currentWeapon = current;
  }
  void setCurrentArmor(Armor current) {
    currentArmor = current;
  }

  
  public String weaponString() {
    if(currentWeapon == null) return "NONE";
    else return currentWeapon.name;
  }
  
  public String armorString() {
    if(currentArmor == null) return "NONE";
    else return currentArmor.name;
  }
  
  public String potionString() {
    if(currentPotion == null) return "NONE";
    else return currentPotion.name;
  }
  
  public String spellString() {
    if(currentSpell == null) return "NONE";
    else return currentSpell.name;
  }
  
 public void currentString() {
   String title = AnsiColors.ANSI_BLUE + "\n\t\t\t\t   C U R R E N T L Y   E Q U I P P E D \n";
   String currentItems = String.format("%-25s%-25s%-25s%-25s\n", "Armor", "Weapon", "Spell", "Potion");
   String currentVals = String.format("%-25s%-25s%-25s%-25s\n", armorString(), weaponString(), spellString(), potionString());
   System.out.println(title + AnsiColors.ANSI_GREEN +"\t\t" +currentItems + AnsiColors.ANSI_RESET + "\t\t" + currentVals);
 }

  
  public String toString() {
    System.out.printf(AnsiColors.ANSI_BLUE + "\t\t\t\t\t\tS T A T S\n" + AnsiColors.ANSI_RESET);
    String header = String.format("%-22s%-10s%-10s%-10s%-12s%-12s%-10s%-10s%-10s%-10s%-10s\n", "Name", "HP", "Level", "Mana", "Strength", "Dexterity", "Agility", "Money", "Damage", "Defense" ,"XP");
    String values = String.format("%-22s%-10d%-10d%-10d%-12d%-12d%-10d%-10d%-10d%-10d%-10d\n", name, (long)HP, level, (long)currentMana, (long)strength, (long)dexterity, (long)agility, (long)money, (long)damage, (long)defense,currentXP);
    return AnsiColors.ANSI_CYAN + header + AnsiColors.ANSI_RESET + values;
  }
  
  public boolean equip() {
    if(curInventory.size() == 0)  {
      System.out.println("\n");
      System.out.println("You don't have anything in your inventory.\n");
      return true;
    }
    replace = false;
    System.out.println("\n");
    System.out.println(AnsiColors.ANSI_PEACH + "EQUIPMENT SELECTION: " +  AnsiColors.ANSI_CYAN + nickname + "\n " + AnsiColors.ANSI_RESET +
        " + Press" + AnsiColors.ANSI_PEACH + " (Y/y) " +  AnsiColors.ANSI_RESET + "to equip a weapon.  \n " + 
        " + Press" + AnsiColors.ANSI_PEACH + " (Q/q) " +  AnsiColors.ANSI_RESET + "to quit. \n ");
    while(!replace) {
      String user = input.nextLine();
      if(user.compareTo("Y") == 0 || user.compareTo("y") == 0) {
          int selection = 0;
          System.out.println("\nPlease select an item from your inventory below (1 -" + curInventory.size() +"):\n");
          curInventory.display();
          System.out.println("");
          boolean check = false;
          while(!check) {
            selection = input.nextInt(); 
            if(selection - 1 > curInventory.size()) {
              System.out.println("\nThat was not a valid number. Try again.\n");
            }
            else check = true;
          } 
          Item temp = curInventory.get(selection - 1);
          if(temp instanceof Armor) {
            currentArmor =  (Armor) curInventory.get(selection - 1);
            defense = currentArmor.defense;
            System.out.println("\nYou changed your armor to " + currentArmor.name + ".");
          }
          if(temp instanceof Weapon) {
            currentWeapon =  (Weapon) curInventory.get(selection - 1);
            damage = currentWeapon.damage;
            System.out.println("\nYou changed your weapon to " + currentWeapon.name + ".");
            }
          if(temp instanceof Spell) {
            currentSpell =  (Spell) curInventory.get(selection - 1);
            damage = currentSpell.calculateDamage(this);
            System.out.println("\nYou changed your spell to " + currentSpell.name + ".");
            }
          if(temp instanceof Potion) {
            currentPotion =  (Potion) curInventory.get(selection - 1);
            System.out.println("\nYou changed your potion to " + currentPotion.name + ".");
            }
          System.out.println("\nDo you want to equip another item? Press (Y/y) to equip, and (Q/q) to exit.\n"); 
      }
      else if(user.compareTo("Q") == 0 || user.compareTo("q") == 0) {
        replace = true;
      }
    }
    return false;
  }
}
