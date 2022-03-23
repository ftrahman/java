
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

public class TeamFight {
  private static MonsterTeam enemies;
  static boolean heroWin = false;
  private static boolean complete;
  private int length;
  public Team heroes;
  public static boolean done = false;
  private static Scanner input = new Scanner(System.in);
  private final int DODGE_CHANCE = 12;
  private boolean fight = false;
  
  
  
  public TeamFight(Team teamMembers) {
    enemies = new MonsterTeam(teamMembers.length);
    heroes = teamMembers;
    fightEntry();
  }
  
  private void fightEntry() {
    complete = false;
    if(!complete) {
      startFight();
    }
  }
  
 private void startFight() {
   System.out.println("\nOh no! You've encountered a Monster! Get ready to fight!\n");
   enemies.assignEnemies(0);
   done = false;
   do {
     matchUp();
   }while(!done); 
   complete = true;
 }
 
 private void matchUp() {
   while(enemies.checkVitality() && heroes.checkVitality()) {
       int i = 0;
       for(i = 0; i < enemies.length; i++) {
         if(Team.team[i].alive) {
           fight = false;
           Monster currentEnemy = enemies.findOpponent(Team.team[i], i);
           individualFight(Team.team[i], currentEnemy);
         }
         //if(i == enemies.length - 1) i = 0; 
       }
     }
   if(!enemies.checkVitality()) {
     System.out.println("HERE");
     endGameSuccess();
   }
   else {
     System.out.println("\nUnfortunately, you lost.\n");
     done = true; endGameFail();
     }
   }

 
 private void endGameFail() {
   for(int i = 0; i < length; i++) {
     Team.team[i].alive = true;
     Team.team[i].money *= 0.5;
   }
 }
 
 private void endGameSuccess() {
   System.out.println("\nYou have successfully defeated the monsters!\n");
     for(int i = 0; i < length; i++) {
       if(Team.team[i].alive) {
         Team.team[i].money += 150;
         Team.team[i].currentXP += 2;
       }
       if(!Team.team[i].alive) {
         Team.team[i].HP += 50;
         Team.team[i].alive = true;
       }
       if(Team.team[i].currentXP >= Team.team[i].goalXP) {
         levelUp();
       }
     }
     done = true;
 }
 
 
 private void individualFight(Hero hero, Monster enemy){
   System.out.println("\nYour hero " + AnsiColors.ANSI_CYAN + hero.name + AnsiColors.ANSI_RESET + " is fighting against " + AnsiColors.ANSI_YELLOW + enemy.name + AnsiColors.ANSI_RESET + ". Below are "
 + AnsiColors.ANSI_CYAN + "yours" + AnsiColors.ANSI_RESET +  " and " + AnsiColors.ANSI_YELLOW + "your enemy's" + AnsiColors.ANSI_RESET +  " current statistics.\n");
   System.out.print(hero +"\n");
   hero.currentString();
   System.out.printf("\n\n" + AnsiColors.ANSI_BLUE + "\t\t\t\t\t E N E M Y   S T A T S\n" + AnsiColors.ANSI_RESET); 
   System.out.print(enemy + "\n");
   System.out.println("\n");
   
   Random rand = new Random();
   boolean dodge = rand.nextInt(100) < enemy.dodge*100;
   while(!fight) {
   
   System.out.println(AnsiColors.ANSI_ORANGE + "FIGHT SELECTION \n " + AnsiColors.ANSI_RESET +
 " + Press" + AnsiColors.ANSI_ORANGE + " (R/r) " +  AnsiColors.ANSI_RESET + "to perform a regular attack.  \n " + 
 " + Press" + AnsiColors.ANSI_ORANGE + " (C/c) " +  AnsiColors.ANSI_RESET + "to cast a spell. \n " +
 " + Press" + AnsiColors.ANSI_ORANGE + " (P/p) " +  AnsiColors.ANSI_RESET + "to use a potion. \n " +
 " + Press" + AnsiColors.ANSI_ORANGE + " (E/e) " +  AnsiColors.ANSI_RESET + "to equip your current hero. \n " + 
 " + Press" + AnsiColors.ANSI_ORANGE + " (L/l) " +  AnsiColors.ANSI_RESET + "to see your stats.\n " + 
 " + Press" + AnsiColors.ANSI_ORANGE + " (I/i) " +  AnsiColors.ANSI_RESET + "to see your inventory.\n " +
 " + Press" + AnsiColors.ANSI_ORANGE + " (V/v) " +  AnsiColors.ANSI_RESET + "to see your currently equipped items.\n" );
   
   String user = input.nextLine();
   if(user.compareTo("R") == 0 || user.compareTo("r") == 0) {
     if(hero.currentWeapon != null) {
       if(!dodge) {
         enemy.HP -= hero.currentWeapon.damage + enemy.defense;
         System.out.println("\nYou attacked " + AnsiColors.ANSI_YELLOW + enemy.name + AnsiColors.ANSI_RESET + " with " + AnsiColors.ANSI_PURPLE + hero.currentWeapon.name + AnsiColors.ANSI_RESET + ".\n");
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
         System.out.println("\nYou attacked " + AnsiColors.ANSI_YELLOW + enemy.name + AnsiColors.ANSI_RESET + " with " + AnsiColors.ANSI_PURPLE + hero.currentSpell.name + AnsiColors.ANSI_RESET + ".\n");
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
     System.out.println(hero); 
   }
   else if(user.compareTo("v") == 0 || user.compareTo("V") == 0) {
     hero.currentString();
   }
   else if(user.compareTo("i") == 0 || user.compareTo("I") == 0) {
     System.out.println("\n");
     hero.curInventory.display();
     System.out.println("\n");
   }
   else if(user.compareTo("e") == 0 || user.compareTo("E") == 0) {
     hero.equip(); fight = true;
     }
  
     }
     if(enemy.HP <= 0) {
       enemy.alive = false;
       enemy.HP = 0;
       System.out.println("Your enemy " + AnsiColors.ANSI_YELLOW + enemy.name + AnsiColors.ANSI_RESET + " has been defeated!");
     }
     
     boolean userDodge = rand.nextInt(100) < DODGE_CHANCE;
     if(userDodge) {
       System.out.println("\nYour hero "  + AnsiColors.ANSI_CYAN + hero.name + AnsiColors.ANSI_RESET + " has dodged your enemy's attack!\n");
       
     }
     if(!userDodge) {
       hero.HP = hero.HP + hero.defense - enemy.damage;
       System.out.println("\nOh no! You've been hit by " + AnsiColors.ANSI_YELLOW + enemy.name + AnsiColors.ANSI_RESET + "!\n");
     }
     if(hero.HP <= 0) {
       hero.alive = false;
       hero.HP = 0;
       System.out.println("Oh no! Your hero " + AnsiColors.ANSI_CYAN + hero.name + AnsiColors.ANSI_RESET + " has been defeated!");
       
     }
     if(hero.HP > 0) regain(hero);
     
     System.out.println("\nYour stats for this round are:\n");
     System.out.println(hero); System.out.println(enemy);
     System.out.println("---------------------------------------------------------------------------------------------------------------------"); 
 }

 
 private void regain(Hero hero) {
     if(hero.alive) {
       System.out.println("\nYou gained back some health and mana!");
       hero.currentMana += (hero.currentMana * 0.05);
       hero.HP += (hero.HP * 0.05);
       }
  }
 
 private void levelUp() {
   for(int i = 0; i < length ; i++) {
     Team.team[i].calculateMana();
     Team.team[i].level++;
     Team.team[i].calculateGoalXP();
     Team.team[i].calculateHP();
     if(Team.team[i] instanceof Paladin) {
       Paladin temp = (Paladin)Team.team[i];
       temp.adjustSkills();
     }
     if(Team.team[i] instanceof Warrior) {
       Warrior temp = (Warrior)Team.team[i];
       temp.adjustSkills();
     }
     if(Team.team[i] instanceof Sorcerer) {
       Sorcerer temp = (Sorcerer)Team.team[i];
       temp.adjustSkills();
     }
     
   }
 }


 

  
  
  
  
}
