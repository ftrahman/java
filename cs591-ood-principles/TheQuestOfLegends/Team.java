
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
  public String newMarker;
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
          team[i].nickname = "H" + i;
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
          team[i].nickname = "H" + i;
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
          team[i].nickname = "H" + i;
          setUp = true;
          System.out.println("Your #" + (i + 1) + " Hero is " + team[i].name); 
          break;
        }
      }
    }
  }
  
  public void startingPosition(int input) {
    Cell target = null;
    if(input == 1) {
      team[0].position = Lanes.LANE_ONE;
      team[0].startingPosition = Lanes.LANE_ONE;
      target = QuestOfLegendsBoard.findRowColNumber(team[0].position);
      target.curMarker = new Marker(team[0].nickname);
      target.curHero = team[0];
    }
    if(input == 2) {
      team[0].position = Lanes.LANE_TWO;
      team[0].startingPosition = Lanes.LANE_TWO;
      target = QuestOfLegendsBoard.findRowColNumber(team[0].position);
      target.curMarker = new Marker(team[0].nickname);
      target.curHero = team[0];
    }
    if(input == 3) {
      team[1].position = Lanes.LANE_THREE;
      team[1].startingPosition = Lanes.LANE_THREE;
      target = QuestOfLegendsBoard.findRowColNumber(team[1].position);
      target.curMarker = new Marker(team[1].nickname);
      target.curHero = team[1];
    }
    if(input == 4) {
      team[1].position = Lanes.LANE_FOUR;
      team[1].startingPosition = Lanes.LANE_FOUR;
      target = QuestOfLegendsBoard.findRowColNumber(team[1].position);
      target.curMarker = new Marker(team[1].nickname);
      target.curHero = team[1];
    }
    if(input == 5) {
      team[2].position = Lanes.LANE_FIVE;
      team[2].startingPosition = Lanes.LANE_FIVE;
      target = QuestOfLegendsBoard.findRowColNumber(team[2].position);
      target.curMarker = new Marker(team[2].nickname);
      target.curHero = team[2];
    }
    if(input == 6) {
      team[2].position = Lanes.LANE_SIX;
      team[2].startingPosition = Lanes.LANE_SIX;
      target = QuestOfLegendsBoard.findRowColNumber(team[2].position);
      target.curMarker = new Marker(team[2].nickname);
      target.curHero = team[2];
    }
    target.heroHere = true;
    
    newMarker = target.original.id;
  }
  
  public Hero getPlayer(int index) {
    return team[index];
  }
  
  public static Cell getPlayerCell(int position) {
    Cell locateHero = QuestOfLegendsBoard.findRowColNumber(position);
    return locateHero;
  }
  
  public Player choosePlayer() {
    if (length == 1) {
      return (Hero) getPlayer(0);
    }
    boolean temp = false;
    System.out.println("Please pick a hero to modify. Press the number corresponding to the list order.");
    toString();
    
    int input = inputEnter.nextInt();
    while(!temp) {
      if(input < 0 || input > 3) {
        System.out.println("Please enter a number between 1-3.");
      }
      else temp = true;
    }
    Hero chosen = getPlayer(input-1);
    return chosen;
  }
  
  public String toString() {
    for(int i = 0; i < team.length; i++) {
      if(team[i] != null)
        System.out.println("\n");
        System.out.println(team[i].toString());
        team[i].curInventory.display();
        team[i].currentString();
        System.out.println("---------------------------------------------------------------------------------------------------------------------"); }
    return null;
  }
  
  public boolean checkVitality() {
    int counter = 0;
    for(int i = 0; i < length; i++) {
      if(!team[i].alive) {
        counter++;
      }
      else if (counter == length - 1) {
        return false;
      }
    }
    return true;
  }
  
}
