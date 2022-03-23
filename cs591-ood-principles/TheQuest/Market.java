/*
 * Market: Represents the entire marketplace interaction.
 * 
 * private static void buy(Item selection): Allows hero to buy an object.
 * private static void sell(Item selection): Allows hero to sell an object.
 * public static void marketplace(): Entry method for the marketPlace;
 * private static void marketplaceHelper(): All of the interactions between user.
 * private static void separateTeam(Team team): Pulls apart the players so they can have their own inventories.
 * private static void menu(int i): Navigates the menu options. 
 */

import java.util.Scanner;

public class Market {
  private static boolean proceed = false;
  private static boolean check = false;
  private static boolean again = true;
  private static boolean control = false;
  private static Scanner inputEnter = new Scanner(System.in);
  private static Scanner inputInt = new Scanner(System.in);
  private static Scanner newScanner = new Scanner(System.in);

  
  public static Hero currentHero;
  
  private static void buy(Item selection) {
    if(selection.minLevel <= currentHero.level) {
      if(currentHero.money - selection.price > 0) {
        currentHero.money = currentHero.money - selection.price;
        currentHero.inventory.add(selection);
        if(currentHero.currentWeapon == null && selection instanceof Weapon) {
          currentHero.currentWeapon = (Weapon) selection;
        }
        if(currentHero.currentPotion == null && selection instanceof Potion) {
          currentHero.currentPotion = (Potion) selection;
        }
        if(currentHero.currentSpell == null && selection instanceof Spell) {
          currentHero.currentSpell = (Spell) selection;
        }
        if(currentHero.currentArmor == null && selection instanceof Armor) {
          currentHero.currentArmor = (Armor) selection;
          currentHero.defense = (double) currentHero.currentArmor.defense;
        }
        check = false;
        System.out.println("Please see your updated stats below.\n");
        System.out.println(currentHero);
        System.out.println("Please see your updated inventory below.\n");
        currentHero.inventory.display();
      }
      else {
        System.out.println("So sorry, you don't have enough money to buy that!");
      }
      
    }
    else
      System.out.println("So sorry, you don't have enough experience to buy that!");
  }
  
  private static void sell(Item selection) {
    currentHero.money = currentHero.money + (0.5 * selection.price);
    currentHero.inventory.remove(selection);
    check = false;
    System.out.println("Please see your updated stats below.\n");
    System.out.println(currentHero);
    System.out.println("Please see your updated inventory below.\n");
    currentHero.inventory.display();
    
  }
  
  public static void marketplace() {
    while(!control) {
      marketplaceHelper();
    }
  }
  
  private static void marketplaceHelper() {
    TextFiles.MarketIntro();
    while(!proceed) {
      if(!inputEnter.nextLine().isEmpty()) {
        System.out.println("Press ENTER to begin. Press E to exit.");
      }
      else proceed = true;
    }
    proceed = false;
    while(!proceed) {
      separateTeam(QuestGame.getTeamMembers());
      System.out.println("\n");
      System.out.println("Are you looking to (B/b)uy or (S/s)ell? Press (I/i) to see your inventory. Press (L/l) to see your stats."
          + "Press (Q/q)uit to exit the Market.");
      System.out.println("\n");
      if(newScanner.nextLine().compareTo("b") == 0 || newScanner.next().compareTo("B") == 0) {
        while(again) {
          TextFiles.Market();
          menu(inputInt.nextInt());
          System.out.println("\n");
          System.out.println("Would you like to buy another item for your Hero?");
          if(inputEnter.nextLine().compareTo("N") == 0 || inputEnter.nextLine().compareTo("n") == 0)
            again = false;
          if(inputEnter.nextLine().compareTo("Y") == 0 || inputEnter.nextLine().compareTo("y") == 0)
            again = true;
          else 
            System.out.println("Please enter Y/y or N/n.");
        }
      }
      if(newScanner.nextLine().compareTo("i") == 0 || newScanner.next().compareTo("I") == 0) {
        currentHero.inventory.display();
      }
      if(newScanner.nextLine().compareTo("l") == 0 || newScanner.next().compareTo("L") == 0) {
        System.out.println(currentHero);
      }
      if(newScanner.nextLine().compareTo("s") == 0 || newScanner.next().compareTo("S") == 0) {
        currentHero.inventory.display();
        System.out.println("Which item do you want to sell? Press the number corresponding to the list order.");
        int input = inputInt.nextInt();
        if(Inventory.check(input)) sell(Inventory.get(input));
        else System.out.println("Try entering another number.");
      }
      if(inputEnter.nextLine().compareTo("Q") == 0 || inputEnter.next().compareTo("q") == 0) {
       control = true;
      }
    }
  }
  
  private static void separateTeam(Team team) {
    currentHero = (Hero) team.choosePlayer();
  }
    
    private static void menu(int i) {
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
