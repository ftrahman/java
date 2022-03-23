
/*
 * Fight: A class to represent a fight between Heroes and Monsters. 
 * 
 * public Fight(Team teamMembers): Constructs the fight from the Heroes team.
 * private void fightEntry(): Help control the fight loop.
 * private void startFight(): Help to initialize and gather opponents.
 * private void initializeArrays(): Initializes the arrays for dead/alive characters.
 * private void updateArrays(): Maintains that current state of dead/alive characters.
 * private void endGame(): End the fight.
 * private void organizeFight(): Helps to match opponent against each other.
 * private void individualFight(Hero hero, Monster enemy): Begins a fight between two individual characters
 *       and not their teams.
 * private void assignEnemies(): Initializes the enemy array with appropriate characters.
 * public int findLevel(Team team, int position): Determine the level of the heroes to match to the monsters.
 * private void regain(): Helps heroes regain some health following a round.
 * private void levelUp(): If the heroes win, and happen to level up during the fight, increase their stats.
 * 
 */

import java.util.Random;
import java.util.Scanner;

public class IndividualFight {
  static boolean heroWin = false;
  public Team heroes;
  public static boolean done = false;
  private static Scanner input = new Scanner(System.in);
  private final static int DODGE_CHANCE = 12;
  private static boolean fight = false;

  
 private static void endGameFail(Hero hero) {
   System.out.println("\nYou lost some money and but gained back half your health!\n");
     hero.alive = true;
     hero.money *= 0.5;
     hero.HP = hero.level * 50;
   
 }
 
 private static void endGameSuccess(Hero hero) {
     hero.money += 150;
     hero.currentXP += 2;
     System.out.println("\nYou gained some money (150) and XP (2)!\n");
     if(hero.currentXP >= hero.goalXP) {
         levelUp(hero);
         System.out.println("\nYou leveled up to level " + hero.level +  "!\n");
       }
     done = true;
     }
 
 static void individualFight(Hero hero, Monster enemy){
   System.out.println("\nYour hero " + AnsiColors.ANSI_CYAN + hero.name + AnsiColors.ANSI_RESET + " is fighting against " + AnsiColors.ANSI_YELLOW + enemy.name + AnsiColors.ANSI_RESET + ". Below are "
 + AnsiColors.ANSI_CYAN + "yours" + AnsiColors.ANSI_RESET +  " and " + AnsiColors.ANSI_YELLOW + "your enemy's" + AnsiColors.ANSI_RESET +  " current statistics.\n");
   System.out.print(hero +"\n");
   hero.currentString();
   System.out.printf("\n\n" + AnsiColors.ANSI_BLUE + "\t\t\t\t\t E N E M Y   S T A T S\n" + AnsiColors.ANSI_RESET); 
   System.out.print(enemy + "\n");
   System.out.println("\n");
   
   Random rand = new Random();
   fight = false;
   boolean dodge = rand.nextInt(100) < enemy.dodge*100;
   while(!fight) {
   
   System.out.println(AnsiColors.ANSI_PEACH + "FIGHT SELECTION: " +  AnsiColors.ANSI_CYAN + hero.name + "\n " + AnsiColors.ANSI_RESET +
 " + Press" + AnsiColors.ANSI_PEACH + " (R/r) " +  AnsiColors.ANSI_RESET + "to perform a regular attack.  \n " + 
 " + Press" + AnsiColors.ANSI_PEACH + " (C/c) " +  AnsiColors.ANSI_RESET + "to cast a spell. \n " +
 " + Press" + AnsiColors.ANSI_PEACH + " (P/p) " +  AnsiColors.ANSI_RESET + "to use a potion. \n " +
 " + Press" + AnsiColors.ANSI_PEACH + " (L/l) " +  AnsiColors.ANSI_RESET + "to see your stats.\n " + 
 " + Press" + AnsiColors.ANSI_PEACH + " (I/i) " +  AnsiColors.ANSI_RESET + "to see your inventory.\n " +
 " + Press" + AnsiColors.ANSI_PEACH + " (V/v) " +  AnsiColors.ANSI_RESET + "to see your currently equipped items.\n" );
   
   String user = input.nextLine();
   if(user.compareTo("R") == 0 || user.compareTo("r") == 0) {
     if(hero.currentWeapon != null) {
       if(!dodge) {
         enemy.HP -= hero.currentWeapon.damage + enemy.defense;
         System.out.println("\nYou attacked " + AnsiColors.ANSI_YELLOW + enemy.name + AnsiColors.ANSI_RESET + " with " + AnsiColors.ANSI_PURPLE + hero.currentWeapon.name + AnsiColors.ANSI_RESET + ".");
         fight = true;
       }
       else {
         System.out.println("\nYour enemy " + AnsiColors.ANSI_YELLOW + enemy.name + AnsiColors.ANSI_RESET + " has dodged your attack!\n");
         fight = true;
       }
     }
     else System.out.println("\nYou don't currently have a weapon equipped.\n");
   }
   else if(user.compareTo("C") == 0 || user.compareTo("c") == 0) {
     if(hero.currentSpell != null) { 
       if(!dodge) {
         System.out.println("\nYou attacked " + AnsiColors.ANSI_YELLOW + enemy.name + AnsiColors.ANSI_RESET + " with " + AnsiColors.ANSI_PURPLE + hero.currentSpell.name + AnsiColors.ANSI_RESET + ".");
         enemy.HP -= hero.currentSpell.calculateDamage(hero) + enemy.defense;
         if(hero.currentSpell instanceof IceSpell) {
           IceSpell tempSpell = (IceSpell) hero.currentSpell;
           tempSpell.additionalDamage(enemy);
           hero.currentMana =- tempSpell.manaCost;
         }
         else if(hero.currentSpell instanceof FireSpell) {
           FireSpell tempSpell = (FireSpell) hero.currentSpell;
           tempSpell.additionalDamage(enemy);
           hero.currentMana =- tempSpell.manaCost;
         }
         else if(hero.currentSpell instanceof LightningSpell) {
           LightningSpell tempSpell = (LightningSpell) hero.currentSpell;
           tempSpell.additionalDamage(enemy);
           hero.currentMana =- tempSpell.manaCost;
         }
         fight = true;
       }
       else {
         System.out.println("\nYour enemy " + AnsiColors.ANSI_YELLOW + enemy.name + AnsiColors.ANSI_RESET + " has dodged your attack!\n");
         fight = true;
       }
         
     }
     else System.out.println("\nYou don't currently have a spell equipped.\n");
   }
   else if(user.compareTo("P") == 0 || user.compareTo("p") == 0) {
     if(hero.currentPotion != null) {
       String[] ret = Potion.typeOfPotion(hero.currentPotion, hero);
       hero.curInventory.remove(hero.currentPotion);
       System.out.println("\n" + AnsiColors.ANSI_CYAN + hero.name + AnsiColors.ANSI_RESET + "'s " + ret[0] + " increased by " + ret[1] + " with " + AnsiColors.ANSI_PURPLE + hero.currentPotion.name + AnsiColors.ANSI_PURPLE + ".\n");
       fight = true;
     }
     else System.out.println("\nYou don't currently have a potion equipped.\n");
   }
   else if(user.compareTo("l") == 0 || user.compareTo("L") == 0) {
     System.out.println("\n");
     System.out.println(hero); 
     System.out.println("\n");
   }
   else if(user.compareTo("v") == 0 || user.compareTo("V") == 0) {
     hero.currentString();
     System.out.println("\n");
   }
   else if(user.compareTo("i") == 0 || user.compareTo("I") == 0) {
     System.out.println("\n");
     hero.curInventory.display();
     System.out.println("\n");
   }

     }
     if(enemy.HP <= 0) {
       enemy.alive = false;
       enemy.HP = 0;
       Cell remove =  QuestOfLegendsBoard.findRowColNumber(enemy.position);
       remove.curMonster = new Marker(" ");
       remove.curEnemy = null;
       remove.monsterHere = false;
       System.out.println("\nYour enemy " + AnsiColors.ANSI_YELLOW + enemy.name + AnsiColors.ANSI_RESET + " has been defeated!");
       endGameSuccess(hero);
     }
     
     System.out.println("\nYour stats for this round are:\n");
     System.out.println(hero); System.out.println(enemy);
     boolean proceed = false; 
     System.out.println("\nPress ENTER to continue.\n");
     while(!proceed) {
       if(!input.nextLine().isEmpty()) {
         System.out.println("\nPress ENTER to continue.\n");
       }
       else proceed = true;
     }
     System.out.println("---------------------------------------------------------------------------------------------------------------------\n"); 
 }

 public static void enemyAttack(Hero hero, Monster enemy) {
   Random rand = new Random();
   boolean userDodge = rand.nextInt(100) < DODGE_CHANCE;
   if(userDodge) {
     System.out.println("\nYour hero "  + AnsiColors.ANSI_CYAN + hero.nickname + AnsiColors.ANSI_RESET + " has dodged your enemy's attack!\n");
   }
   if(!userDodge) {
     hero.HP = hero.HP + hero.defense - enemy.damage;
     System.out.println("\nOh no! " + hero.nickname + " has been hit by " + AnsiColors.ANSI_YELLOW + enemy.nickname + AnsiColors.ANSI_RESET + "!\n");
   }
   if(hero.HP <= 0) {
     endGameFail(hero);
     System.out.println("\nOh no! Your hero " + AnsiColors.ANSI_CYAN + hero.nickname + AnsiColors.ANSI_RESET + " has been defeated!\n");
     Nexus.findNexus(hero);
     System.out.println("\nYour stats for this round are:\n");
     System.out.println(hero); System.out.println(enemy);
     boolean proceed = false; 
     System.out.println("\nPress ENTER to continue.\n");
     while(!proceed) {
       if(!input.nextLine().isEmpty()) {
         System.out.println("\nPress ENTER to continue.\n");
       }
       else proceed = true;
     }
     System.out.println("---------------------------------------------------------------------------------------------------------------------\n"); 
 
   }
   if(hero.HP > 0) regain(hero);
 }
 
 private static void regain(Hero hero) {
     if(hero.alive) {
       System.out.println("\nYou gained back some health and mana from your enemy's attack!\n");
       hero.currentMana += (hero.currentMana * 0.05);
       hero.HP += (hero.HP * 0.05);
       }
  }
 
 private static void levelUp(Hero hero) {
     hero.calculateMana();
     hero.level++;
     hero.calculateGoalXP();
     hero.calculateHP();
     if(hero instanceof Paladin) {
       Paladin temp = (Paladin) hero;
       temp.adjustSkills();
     }
     if(hero instanceof Warrior) {
       Warrior temp = (Warrior) hero;
       temp.adjustSkills();
     }
     if(hero instanceof Sorcerer) {
       Sorcerer temp = (Sorcerer) hero;
       temp.adjustSkills();
     
   }
 }


 

  
  
  
  
}
