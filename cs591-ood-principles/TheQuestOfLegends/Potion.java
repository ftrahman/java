
/*
 * Potion: A subclass class of the parent class Item, that creates an instance of 1 of 6 types of Potions.
 * 
 * public Potion(int selection): A constructor that takes in a menu selection and instantiates it
 * private void setItem(int selection): A setter method to help initialize the new Potion object.
 * public static void typeOfPotion(Potion curPot, Hero hero): Set up the correct increments for the Potions.
 * public void toString(): A printable form of the Potion object.
 */
public class Potion extends Item {
  int increase;
  String attribute;
  
  public Potion(int selection) {
    super();
    setItem(selection);
  }
  
  private void setItem(int selection) {
    if(selection == 1) {
      name = "Healing Potion"; minLevel = 1;
      increase = 100; price = 250;
    }
    else if(selection == 2) {
      name = "Strength Potion"; minLevel = 1;
      increase = 75; price = 200;
    }
    else if(selection == 3) {
      name = "Magic Potion"; minLevel = 2;
      increase = 100; price = 350;
    }
    else if(selection == 4) {
      name = "Luck Elixir"; minLevel = 4;
      increase = 65; price = 500;
    }
    else if(selection == 5) {
      name = "Mermaid Tears"; minLevel = 5;
      increase = 100; price = 850;
    }
    else if(selection == 6) {
      name = "Ambrosia"; minLevel = 8;
      increase = 150; price = 1000;
    }
  }
  
  public static String[] typeOfPotion(Potion curPot, Hero hero) {
    String[] info = new String[2];
    if(curPot.name.compareTo("Healing Potion") == 0) {
      hero.HP += 100;
      info[0] = "HP"; info[1] = "100";
    }
    if(curPot.name.compareTo("Strength Potion") == 0) {
      hero.strength += 75;
      info[0] = "strength"; info[1] = "75";
    }
    if(curPot.name.compareTo("Magic Potion") == 0) {
      hero.dexterity += 100;
      info[0] = "dexterity"; info[1] = "100";
    }
    if(curPot.name.compareTo("Luck Elixir") == 0) {
      hero.agility += 65;
      info[0] = "agility"; info[1] = "65";
    }
    if(curPot.name.compareTo("Mermaid Tears") == 0) {
      hero.money += 100;
      info[0] = "money"; info[1] = "100";
    }
    if(curPot.name.compareTo("Luck Elixir") == 0) {
      hero.currentMana += 150;
      info[0] = "mana"; info[1] = "150";
    }
    return info;
  }
  
  
  
  public String toString() {
    String values = String.format("%-15s%-15s%-15s%-25s", "-" ,"-", "-" , AnsiColors.ANSI_BLUE + increase + AnsiColors.ANSI_RESET);
    return super.toString() + values;
  }
  
}
