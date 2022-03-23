/*
 * QuestOfLegendsMarket: Represents the entire marketplace interaction for QOL.
 * 
 * public static void marketplace(): Entry method for the marketPlace;
 * private static void marketplaceHelper(): All of the interactions between user.
 * private static void separateTeam(Team team): Pulls apart the players so they can have their own inventories.
 */

import java.util.Scanner;

public class QuestOfLegendsMarket extends MarketRequirements {
  private static boolean proceed = false;
  private static boolean enterMarket = true;
  private static boolean again = true;
  private static boolean control = false;
  private static Scanner inputEnter = new Scanner(System.in);
  private static Scanner newScan = new Scanner(System.in);
  

  
  public static void marketplace(Hero inputHero) {
    proceed = false; enterMarket = true; 
    currentHero = inputHero;
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
      do {
        System.out.println("\n");
        System.out.println(AnsiColors.ANSI_PEACH + "MARKET MENU: " +  AnsiColors.ANSI_CYAN + currentHero.nickname + "\n " + AnsiColors.ANSI_RESET +
      " + Press" + AnsiColors.ANSI_PEACH + " (B/b) " +  AnsiColors.ANSI_RESET + "to buy.  \n " + 
      " + Press" + AnsiColors.ANSI_PEACH + " (S/s) " +  AnsiColors.ANSI_RESET + "to sell. \n " +
      " + Press" + AnsiColors.ANSI_PEACH + " (I/i) " +  AnsiColors.ANSI_RESET + "to see your inventory. \n " +
      " + Press" + AnsiColors.ANSI_PEACH + " (L/l) " +  AnsiColors.ANSI_RESET + "to see your stats.  \n " +
      " + Press" + AnsiColors.ANSI_PEACH + " (V/v) " +  AnsiColors.ANSI_RESET + "to see your currently equipped items. \n " + 
      " + Press" + AnsiColors.ANSI_PEACH + " (E/e) " +  AnsiColors.ANSI_RESET + "to equip your current hero. \n " +  
      " + Press" + AnsiColors.ANSI_PEACH + " (Q/q) " +  AnsiColors.ANSI_RESET + "to exit the Market.");
        System.out.println("\n");
        String input = newScan.nextLine();
        if(input.compareTo("b") == 0 || input.compareTo("B") == 0) {
          again = true;
          while(again) {
            TextFiles.Market();
            int menuInput = inputEnter.nextInt();
            menu(menuInput);
            System.out.println("\n");
            System.out.println("Would you like to buy another item for your Hero? Press (Y/y) for yes, any key for no.\n");
            String user = newScan.nextLine();
            if(user.compareTo("Y") == 0 || user.compareTo("y") == 0)
              continue;
            else 
              again = false;
          }
        }
        else if(input.compareTo("i") == 0 || input.compareTo("I") == 0) {
          System.out.println("\n");
          currentHero.curInventory.display(); 
          System.out.println("\n");
        }
        else if(input.compareTo("l") == 0 || input.compareTo("L") == 0) {
          System.out.println(currentHero); 
        }
        else if(input.compareTo("e") == 0 || input.compareTo("E") == 0) {
          currentHero.equip(); 
        }
        else if(input.compareTo("v") == 0 || input.compareTo("V") == 0) {
          currentHero.currentString(); 
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
        else if(input.compareTo("Q") == 0 || input.compareTo("q") == 0) {
         proceed = true; control = true;
        }
      }while(!control);
    }
    if(proceed) {
      enterMarket = false;
    }
  }
  
    
    

}
