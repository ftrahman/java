

/*
 * Weapon: A subclass class of the parent class Item, that creates an instance of 1 of 7 types of Weapon.
 * 
 * public Weapon(int selection): A constructor that takes in a menu selection and instantiates it
 * private void setItem(int selection): A setter method to help initialize the new Weapon object.
 * public void toString(): A printable form of the Weapon object.
 */
public class Weapon extends Item {
  int damage;
  boolean bothHands;
  
  public Weapon(int selection) {
    super();
    setItem(selection);
  }
  
  private void setItem(int selection) {
    if(selection == 1) {
      name = "Sword"; minLevel = 1;
      damage = 800; price = 500; bothHands = false;
    }
    else if(selection == 2) {
      name = "Bow"; minLevel = 2;
      damage = 500; price = 300; bothHands = true;
    }
    else if(selection == 3) {
      name = "Scythe"; minLevel = 6;
      damage = 1100; price = 1000; bothHands = true;
    }
    else if(selection == 4) {
      name = "Axe"; minLevel = 5;
      damage = 850; price = 550; bothHands = false;
    }
    else if(selection == 5) {
      name = "Shield"; minLevel = 1; 
      damage = 100; price = 400; bothHands = false;
    }
    else if(selection == 6) {
      name = "TSwords"; minLevel = 8; 
      damage = 1600; price = 1400; bothHands = true;
    }
    else if(selection == 7) {
      name = "Dagger"; minLevel = 1; 
      damage = 250; price = 200; bothHands = false;
    }
  }
  
  public String toString() {
    String values = String.format("%-20s%-15d%-19s%-24s", "-" + AnsiColors.ANSI_BLUE,  (long)damage, AnsiColors.ANSI_RESET + "-",  "-");
    return super.toString() + values + AnsiColors.ANSI_RESET ;
  }
  
}
