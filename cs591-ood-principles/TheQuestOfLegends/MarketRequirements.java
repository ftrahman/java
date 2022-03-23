import java.util.Scanner;

public abstract class MarketRequirements {
  protected static Hero currentHero;
  protected static boolean check = false;
  private static Scanner inputInt = new Scanner(System.in);
  
  
  
  protected static void buy(Item selection) {
    if(selection.minLevel <= currentHero.level) {
      if(currentHero.money - selection.price > 0) {
        currentHero.money = currentHero.money - selection.price;
        currentHero.curInventory.add(selection);
        check = false;
        System.out.println("\nPlease see your updated inventory below.\n");
        currentHero.curInventory.display();
      }
      else {
        System.out.println("You don't have enough money to buy that.\n");
      }
      
    }
    else
      System.out.println("You don't have enough experience to buy that.\n");
  }
  
  protected static void sell(Item selection) {
    currentHero.money = currentHero.money + (0.5 * selection.price);
    currentHero.curInventory.remove(selection);
    check = false;
    System.out.println("Please see your updated stats below.\n");
    System.out.println(currentHero);
    currentHero.curInventory.display();
  }
  
  protected static void menu(int i) {
    if(i == 1) {
      TextFiles.Armory();
      do {
        int input = inputInt.nextInt();
        if(input > 0 && input < 7) {
          Item choice = new Armor(input);
          buy(choice);
          check = true;
        }
      }while(!check);
    }
    if(i == 2) {
      TextFiles.Weaponry();
      do {
        int input = inputInt.nextInt();
        if(input > 0 && input < 8) {
          Item choice = new Weapon(input);
          buy(choice);
          check = true;
        }
      }while(!check);
    }
    if(i == 3) {
      TextFiles.Potions();
      do {
        int input = inputInt.nextInt();
        if(input > 0 && input < 7) {
          Item choice = new Potion(input);
          buy(choice);
          check = true;
        }
      }while(!check);
    }
    if(i == 4) {
      TextFiles.SpellChoice();
      int menuChoice = inputInt.nextInt();
      while(!check) {
        if(menuChoice < 0 || menuChoice > 4) {
          System.out.println("Please enter 1-3 to choose a spell.");
        }
        else check = true;
      }
      check = false;
      if(menuChoice == 1) {
        TextFiles.IceSpells();
        do {
          int input = inputInt.nextInt();
          if(input > 0 && input < 7) {
            Item choice = new IceSpell(input);
            buy(choice);
            check = true;
          }
        }while(!check);
      }
      if(menuChoice == 2) {
        TextFiles.FireSpells();
        do {
          int input = inputInt.nextInt();
          if(input > 0 && input < 7) {
            Item choice = new FireSpell(input);
            buy(choice);
            check = true;
          }
        }while(!check);
      }
      if(menuChoice == 3) {
        TextFiles.LightningSpells();
        do {
          int input = inputInt.nextInt();
          if(input > 0 && input < 7) {
            Item choice = new LightningSpell(input);
            buy(choice);
            check = true;
          }
        }while(!check);
      }
    }
}
}
