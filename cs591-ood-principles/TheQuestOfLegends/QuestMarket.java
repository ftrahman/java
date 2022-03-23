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

public class QuestMarket extends MarketRequirements{
  private static boolean proceed = false;
  private static boolean enterMarket = true;
  private static boolean again = true;
  private static boolean control = false;
  private static Scanner inputEnter = new Scanner(System.in);

  
  private static Hero currentHero;
  
  public static void marketplace() {
    proceed = false; enterMarket = true;
    while(enterMarket) {
      marketplaceHelper();
    }
  }
  
  private static void marketplaceHelper() {
    TextFiles.MarketIntro();
    while(!proceed) {
      if(!inputEnter.nextLine().isEmpty()) {
        System.out.println("Press ENTER to begin.");
      }
      else proceed = true;
    }
    proceed = false;
    while(!proceed) {
      if(currentHero == null) {
        separateTeam(QuestGame.getTeamMembers());
      }
      do {
        System.out.println("\n");
        System.out.println(AnsiColors.ANSI_ORANGE + "MAIN MENU \n " + AnsiColors.ANSI_RESET +
      " + Press" + AnsiColors.ANSI_ORANGE + " (B/b) " +  AnsiColors.ANSI_RESET + "to buy.  \n " + 
      " + Press" + AnsiColors.ANSI_ORANGE + " (S/s) " +  AnsiColors.ANSI_RESET + "to sell. \n " +
      " + Press" + AnsiColors.ANSI_ORANGE + " (I/i) " +  AnsiColors.ANSI_RESET + "to see your inventory. \n " +
      " + Press" + AnsiColors.ANSI_ORANGE + " (L/l) " +  AnsiColors.ANSI_RESET + "to see your stats.  \n " +
      " + Press" + AnsiColors.ANSI_ORANGE + " (V/v) " +  AnsiColors.ANSI_RESET + "to see your currently equipped items. \n " + 
      " + Press" + AnsiColors.ANSI_ORANGE + " (E/e) " +  AnsiColors.ANSI_RESET + "to equip your current hero. \n " + 
      " + Press" + AnsiColors.ANSI_ORANGE + " (C/c) " +  AnsiColors.ANSI_RESET + "to change heroes. \n " + 
      " + Press" + AnsiColors.ANSI_ORANGE + " (Q/q) " +  AnsiColors.ANSI_RESET + "to exit the Market.");
        System.out.println("\n");
        String input = inputEnter.nextLine();
        if(input.compareTo("b") == 0 || input.compareTo("B") == 0) {
          while(again) {
            TextFiles.Market();
            int menuInput = inputEnter.nextInt();
            menu(menuInput);
            System.out.println("\n");
            System.out.println("Would you like to buy another item for your Hero? Press (Y/y) for yes, any key for no.\n");
            String user = inputEnter.nextLine();
            if(user.compareTo("Y") == 0 || user.compareTo("y") == 0)
              continue;
            else 
              again = false;
          }
        }
        else if(input.compareTo("i") == 0 || input.compareTo("I") == 0) {
          System.out.println("\n");
          currentHero.curInventory.display(); again = true;
          System.out.println("\n");
        }
        else if(input.compareTo("l") == 0 || input.compareTo("L") == 0) {
          System.out.println(currentHero);  again = true;
        }
        else if(input.compareTo("e") == 0 || input.compareTo("E") == 0) {
          currentHero.equip(); again = true;
        }
        else if(input.compareTo("v") == 0 || input.compareTo("V") == 0) {
          currentHero.currentString(); again = true;
        }
        else if(input.compareTo("s") == 0 || input.compareTo("S") == 0) {
          if(currentHero.curInventory.size() == 0) {
            System.out.println("\n You have no items to sell. \n"); again = true;
          }
          else {
            boolean valid = false;
            while(!valid) {
              currentHero.curInventory.display();
              System.out.println("\n Pick an item to sell. Press the number corresponding to the list order.");
              int sell = inputEnter.nextInt() - 1;
              if(currentHero.curInventory.check(sell)) {
                sell(currentHero.curInventory.get(sell)); 
                valid = true;
              }
              else System.out.println("Try entering another number.");
            }
          }
        }
        else if(input.compareTo("C") == 0 || input.compareTo("c") == 0) {
          if(QuestGame.getTeamMembers().length == 1) {
            System.out.println("You only have one Hero in your team.");
            System.out.println("\n" + currentHero);
          }
          else {
            if(changeMember() == true) {
              separateTeam(QuestGame.getTeamMembers());
              again = true;
            }
          }
        }
        else if(input.compareTo("Q") == 0 || input.compareTo("q") == 0) {
         proceed = true; control = true;
        }
        again = true;
      }while(!control);
    }
    if(proceed) {
      enterMarket = false;
    }
  }
  
  private static boolean changeMember() {
    if(QuestGame.getTeamMembers().length == 1) return false;
    System.out.println("Would you like to modify another Hero?");
    System.out.println("\n");
    String input = inputEnter.nextLine();
    if(input.compareTo("Y") == 0 || input.compareTo("y") == 0) {
      return true;
    }
    else
      return false;
  }
  
  private static void separateTeam(Team team) {
    currentHero = (Hero) team.choosePlayer();
  }
    

}
