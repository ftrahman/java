
/*
 * 
 * QuestGame: A class that implements the actual gameplay of The Quest.
 * 
 * public QuestGame(): Simply calls the setter method for the game.
 * void setUpGame(): Require all future games to have a set up procedure.
 * void startPlay(): Require all future games to have a start play method to begin the game.
 * boolean validMove(): Require all games to check for move validity.
 * public void assignTeamMembers() : Help to assign the team members for play.
 * public void gameInfo(String move): Shows the informational aspects of the game (stats/inventory). 
 * public static Team getTeamMembers(): Getter method for the team members.
 * public void setTeamMembers(int input): Setter method for the team members.
 * 
 * 
 */

import java.util.Random;
import java.util.Scanner;


public class QuestOfLegendsGame extends QuestGame implements GameRequirements, TeamsAllowed {
  

  private static boolean proceed = false;
  private boolean continuePlay = false;
  public boolean quitGame = false;
  private static int counter;
  public static Team teamMembers;
  public static MonsterTeam enemyMembers = new MonsterTeam(3);
  public static QuestOfLegendsBoard gameBoard;
  static Scanner inputInts = new Scanner(System.in);
  static Scanner inputEnter = new Scanner(System.in);
  private static boolean gameOver = false;
  public static int rounds = 0;
  
  public QuestOfLegendsGame() {
    setUpGame();
  }
  
  public void setUpGame() {
    TextFiles.Intro();
    while(!proceed) {
      if(!inputEnter.nextLine().isEmpty()) {
        System.out.println("Press ENTER to begin.");
      }
      else proceed = true;
    }
    proceed = false;
    TextFiles.FairyQL();
    if(!proceed) setTeamMembers(3);
    proceed = false;
    assignTeamMembers();
    System.out.println("\n");
    gameBoard = new QuestOfLegendsBoard(8,8);
    gameBoard.printBoard();
    System.out.println("\n");
    initializePosition();
    gameBoard.printBoard();
    System.out.println("\n");
    System.out.println("Great! Now we're ready to play!");
    TextFiles.WizardQL();
    System.out.println("         Press ENTER to begin.");
    while(!proceed) {
      if(!inputEnter.nextLine().isEmpty()) {
        System.out.println("         Press ENTER to begin.");
      }
      else 
        while(!gameOver)
          startPlay();
    }
    
  }
  
  
  public void startPlay() {
    enemyMembers.assignEnemies(0);
    gameBoard.printBoard();
    System.out.println("\n");
    System.out.println("Your enemies have spawned on the board! Press ENTER to continue."); //Press A/W/S/D to move. \n"
    boolean temp = false;
    while(!temp) {
      if(!inputEnter.nextLine().isEmpty()) {
        System.out.println("Press ENTER to begin.");
      }
      else temp = true;
    }
    System.out.println("\n");
    while(!continuePlay) {
      rounds++;
      if(rounds % 15 == 0) enemyMembers.respawn();
      enemyPlay(enemyMembers);
      if(quitGame) continuePlay = true;
      else {
        int i = 0;
        for(i = 0; i < teamMembers.length; i++) {
          Hero curHero = teamMembers.getPlayer(i);
          individualPlay(curHero);
          if(Nexus.isWin(curHero)) {
            continuePlay = true;
            TextFiles.Congratulations();
            break;
          }
          if(quitGame) {
            continuePlay = true;
            System.out.println("You have quit the Quest of Legends.");
            break;
          }
        }
      }
    }
    gameOver = true; 
  }
  
  private void enemyPlay(MonsterTeam enemyTeam) {
    String move = "S";
    for(int i = 0; i < enemyTeam.length; i++) {
      if(MonsterTeam.team[i].alive) {
        Cell heroCell = QuestOfLegendsBoard.findPlayer(MonsterTeam.team[i]);
        if(heroCell == null) {
          if(validMove(move, MonsterTeam.team[i])) { 
            QuestOfLegendsBoard.moveHero(move, MonsterTeam.team[i]);
            System.out.println("\nYour enemy " + AnsiColors.ANSI_YELLOW + MonsterTeam.team[i].name + AnsiColors.ANSI_RESET + " has advanced forward.\n");
            if(Nexus.isWin(MonsterTeam.team[i])) {
              this.quitGame = true;
              gameBoard.printBoard();
              TextFiles.Defeat();
              break;
              }
           }
        }
        else {
          IndividualFight.enemyAttack(heroCell.curHero, MonsterTeam.team[i]);
        }
      }
      if(i == enemyTeam.length - 1) gameBoard.printBoard();
    }
  }

  private void individualPlay(Hero curHero) {
    boolean go = true;
    do {
    String move = "";
    System.out.println("\n");
    System.out.print("\nPLAYING AS HERO " + AnsiColors.ANSI_CYAN + curHero.nickname + ": " + curHero.name  + AnsiColors.ANSI_RESET + ".\n");
    if(Team.getPlayerCell(curHero.position).compareTo("N") == 0) {
      QuestOfLegendsMarket.marketplace(curHero);
      gameBoard.printBoard();
    }System.out.println("\n");
    System.out.println(AnsiColors.ANSI_PEACH + "PLAYER MENU: " +  AnsiColors.ANSI_CYAN + curHero.nickname + "\n " + AnsiColors.ANSI_RESET +
  " + Press" + AnsiColors.ANSI_PEACH + " (A/W/S/D) " +  AnsiColors.ANSI_RESET + "to move.  \n " + 
  " + Press" + AnsiColors.ANSI_PEACH + " (R/r) " +  AnsiColors.ANSI_RESET + "to attack the nearest enemy. \n " + 
  " + Press" + AnsiColors.ANSI_PEACH + " (B/b) " +  AnsiColors.ANSI_RESET + "to go back to the Nexus. \n " +
  " + Press" + AnsiColors.ANSI_PEACH + " (T/t) " +  AnsiColors.ANSI_RESET + "to teleport to a new cell. \n " + 
  " + Press" + AnsiColors.ANSI_PEACH + " (E/e) " +  AnsiColors.ANSI_RESET + "to equip your current hero. \n " + 
  " + Press" + AnsiColors.ANSI_PEACH + " (I/i) " +  AnsiColors.ANSI_RESET + "to see your inventory. \n " +
  " + Press" + AnsiColors.ANSI_PEACH + " (L/l) " +  AnsiColors.ANSI_RESET + "to see your stats.  \n " +
  " + Press" + AnsiColors.ANSI_PEACH + " (V/v) " +  AnsiColors.ANSI_RESET + "to see your currently equipped items. \n " + 
  " + Press" + AnsiColors.ANSI_PEACH + " (Q/q) " +  AnsiColors.ANSI_RESET + "to exit the game.");
    System.out.println("\n");
    move = inputEnter.next();
    if(validMove(move, curHero)) {
      QuestOfLegendsBoard.moveHero(move, curHero);
      gameBoard.printBoard();
      go = false;
    }
    else if(move.compareTo("T") == 0 || move.compareTo("t") == 0) {
      QuestOfLegendsBoard.teleport(curHero);
      gameBoard.printBoard();
      
      go = false;
    }
    else if(move.compareTo("Q") == 0 || move.compareTo("q") == 0) {
      this.quitGame  = true; go = false; 
    }
    else if(move.compareTo("B") == 0 || move.compareTo("b") == 0) {
      Nexus.findNexus(curHero); gameBoard.printBoard(); go = false;
    }
    else if(move.compareTo("i") == 0 || move.compareTo("I") == 0) {
      System.out.println("\n");
      curHero.curInventory.display(); 
      System.out.println("\n");
    }
    else if(move.compareTo("l") == 0 || move.compareTo("L") == 0) {
      System.out.println(curHero);  
    }
    else if(move.compareTo("e") == 0 || move.compareTo("E") == 0) {
      go = curHero.equip();
    }
    else if(move.compareTo("v") == 0 || move.compareTo("V") == 0) {
      curHero.currentString(); 
    }
    else if(move.compareTo("R") == 0 || move.compareTo("r") == 0) {
      Cell enemyCell = QuestOfLegendsBoard.findPlayer(curHero);
      if(enemyCell == null) System.out.println("\nThere are no monsters close enough to attack.\n");
      else {
        IndividualFight.individualFight(curHero, enemyCell.curEnemy);
        gameBoard.printBoard();
        go = false;
      }
      
    }
    else {
      gameBoard.printBoard();
      System.out.println("\n\nThat wasn't a valid move. \n");
    }
    }while(go);
  }
  
  public void assignTeamMembers() {
    while(counter != 0) {
      TextFiles.Heroes();
      int heroType = inputInts.nextInt();
      if(heroType == 1) {
        TextFiles.Paladins();
        getTeamMembers().assignPaladin(inputInts.nextInt());
        counter--;
      }
      else if(heroType == 2) {
        TextFiles.Warriors();
        getTeamMembers().assignWarrior(inputInts.nextInt());
        counter--;
      }
      else if(heroType == 3) {
        TextFiles.Sorcerers();
        getTeamMembers().assignSorcerer(inputInts.nextInt());
        counter--;
      }
      
    }
  }
  
  
  
  public void initializePosition() {
    System.out.println("Please choose a starting lane for your first hero (1/2).\n");
    int input = 0; boolean go = false;
    while(!go) {
      input = inputInts.nextInt();
      if (input == 1 || input == 2) {
        getTeamMembers().startingPosition(input);
        go = true;
      }
      else System.out.println("That was an invalid number. Try again. \n");
    }
    gameBoard.printBoard();
    System.out.println("Please choose a starting lane for your second hero (3/4).\n");
    go = false;
    while(!go) {
      input = inputInts.nextInt();
      if (input == 3 || input == 4) {
        getTeamMembers().startingPosition(input);
        go = true;
      }
      else System.out.println("That was an invalid number. Try again.\n");
    }
    gameBoard.printBoard();
    System.out.println("Please choose a starting lane for your second hero (5/6).\n");
    go = false;
    while(!go) {
      input = inputInts.nextInt();
      if (input == 5 || input == 6) {
        getTeamMembers().startingPosition(input);
        go = true;
      }
      else System.out.println("That was an invalid number. Try again.\n");
    }
  }
  
  public void setTeamMembers(int input) {
      proceed = true;
      teamMembers = new Team(3); counter = 3;
  }
  
  public boolean validMove(String move, Player curPlayer) {
    if(move.compareTo("W") == 0 || move.compareTo("w") == 0) {
      return QuestOfLegendsBoard.moveUp(curPlayer);
    }
    else if(move.compareTo("A") == 0 || move.compareTo("a") == 0) {
      return QuestOfLegendsBoard.moveLeft(curPlayer);
    }
    else if(move.compareTo("D") == 0 || move.compareTo("d") == 0) {
      return QuestOfLegendsBoard.moveRight(curPlayer);
    }
    else if(move.compareTo("S") == 0 || move.compareTo("s") == 0) {
      return QuestOfLegendsBoard.moveDown(curPlayer);
    }
    return false;
  }
  

  public static Team getTeamMembers() {
    return teamMembers;
  }




 
  
}
