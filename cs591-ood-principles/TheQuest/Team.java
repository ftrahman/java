
/*
 * Team: Allows us to create a Team object out of a group of Players.
 * 
 * 
 * public void assignWarrior(int selection), public void assignSorcerer(int selection),
 *      public void assignPaladin(int selection): All team setter methods. 
 * public Player getPlayer(int index): Getter method.
 * public String toString(): String representation.
 * public Player choosePlayer(): Select a player. 
 */

import java.util.List;
import java.util.Scanner;

public class Team {
  public static Hero[] team = null;
  public int length;
  private boolean setUp = false;
  public boolean allDown = false;
  public static int position = 64;
  private static Scanner inputEnter = new Scanner(System.in);
  
  
  public Team(int length) {
    team = new Hero[length];
    this.length = length;
  }
  
  public void assignPaladin(int selection) {
    setUp = false;
    while(!setUp) {
      for(int i = 0; i < length; i++) {
        if(team[i] == null) {
          team[i] = new Paladin(selection);
          setUp = true;
          System.out.println("Your #" + (i + 1) + " Hero is " + team[i].name);
          break;
        }
      }
    }
  }
  
  public void assignWarrior(int selection) {
    setUp = false;
    while(!setUp) {
      for(int i = 0; i < length; i++) {
        if(team[i] == null) {
          team[i] = new Warrior(selection);
          setUp = true;
          System.out.println("Your #" + (i + 1) + " Hero is " + team[i].name);
          break;
        }
      }
    }
  }
  
  public void assignSorcerer(int selection) {
    setUp = false;
    while(!setUp) {
      for(int i = 0; i < length; i++) {
        if(team[i] == null) {
          team[i] = new Sorcerer(selection);
          setUp = true;
          System.out.println("Your #" + (i + 1) + " Hero is " + team[i].name); 
          break;
        }
      }
    }
  }
  
  public Player getPlayer(int index) {
    return (Hero) team[index];
  }
  
  
  public Player choosePlayer() {
    boolean temp = false;
    System.out.println("Which Hero would you like to modify? Press the number corresponding to the list order.");
    toString();
    int input = inputEnter.nextInt();
    while(!temp) {
      if(input < 0 || input > 3) {
        System.out.println("Please enter a number between 1-3.");
      }
      else temp = true;
    }
    Player chosen = getPlayer(input-1);
    return (Hero) chosen;
  }
  
  public String toString() {
    for(int i = 0; i < team.length; i++) {
      if(team[i] != null)
        System.out.println(team[i].toString());
    }
    return null;
  }
  
  
  
  
  
}
