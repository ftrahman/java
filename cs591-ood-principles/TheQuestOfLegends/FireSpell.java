/*
 * FireSpell: A subclass class of the parent class Spell, that creates an instance of 1 of 6 types of Fire Spells.
 * 
 * public FireSpell(int selection): A constructor that takes in a menu selection and instantiates it.
 * private void setItem(int selection): A setter method to help initialize the new Fire Spell object.
 * public void toString(): A printable form of the Fire Spell object.
 * public void additionalDamage(Monster enemy): Interface method to apply specific attack.
 */

public class FireSpell extends Spell implements AdditionalDamage{

  public FireSpell(int selection) {
    super();
    setItem(selection);
  }
  
  private void setItem(int selection) {
    if(selection == 1) {
      name = "Flame Tornado"; minLevel = 4;
      manaCost = 300; price = 700; baseDamage = 850;
    }
    else if(selection == 2) {
      name = "Breath of Fire"; minLevel = 1;
      manaCost = 100; price = 350; baseDamage = 450;
    }
    else if(selection == 3) {
      name = "Heat Wave"; minLevel = 2;
      manaCost = 150; price = 450; baseDamage = 600;
    }
    else if(selection == 4) {
      name = "Lava Comet"; minLevel = 7;
      manaCost = 550; price = 800; baseDamage = 1000;
    }
    else if(selection == 5) {
      name = "Hell Fire"; minLevel = 3;
      manaCost = 200; price = 550; baseDamage = 500;
    }
    else if(selection == 6) {
      name = "Blaze Bullet"; minLevel = 1;
      manaCost = 100; price = 250; baseDamage = 450;
    }
  }
  
  
  
  public void additionalDamage(Monster enemy) {
    enemy.defense = enemy.defense * 0.9;
  }

}
