
import java.util.*;


public class QuestOfLegendsBoard extends BoardRequirements{
  
  private static double koulou = .1;
  private static double cave = .1;
  private static double bush = .1;
  private static double plain = .7;
  private static double PERMANENT_CELLS = 28;
  public static String newMarker;
  static Scanner input = new Scanner(System.in);
  
  
  
  public QuestOfLegendsBoard(int rows, int cols){
    super(rows, cols);
    setBoard();
  }
  
  public void setBoard() {
    List<String> tiles = new ArrayList<>();
    for(int i = 0; i < (GAME_AREA - PERMANENT_CELLS) * koulou; i++) {
      tiles.add("K");
    }
    for(int i = 0; i < (GAME_AREA - PERMANENT_CELLS) * cave; i++) {
      tiles.add("C");
    }
    for(int i = 0; i < (GAME_AREA - PERMANENT_CELLS) * bush; i++) {
      tiles.add("B");
    }
    for(int i = 0; i < (GAME_AREA - PERMANENT_CELLS) * plain; i++) {
      tiles.add("P");
    }
    int laneOne = 2; int laneTwo = 5;
    Collections.shuffle(tiles);
    for(int i = 0; i < GAME_ROWS; i++) {
      for(int j = 0; j < GAME_COLS; j++) {
        board[i][j].curMarker = new Marker(" ");
        board[i][j].original = new Marker(" ");
        if(board[i][j].id - 1 == laneOne) {
          board[i][j].type = "I"; laneOne += 8;
          board[i][j].curMarker = new Marker("X");
        }
        else if(board[i][j].id - 1 == laneTwo) {
          board[i][j].type = "I"; laneTwo += 8;
          board[i][j].curMarker = new Marker("X");
        }
        else if(i == 0) {
           board[i][j].type = "N";
           board[i][j].original = new Marker("N");
        }
        else if(i == 7) {
          board[i][j].type = "N";
          board[i][j].original = new Marker("N");
        }
        else {
          board[i][j].type = tiles.get(0);
          tiles.remove(0);
        }
      }
    }
  }
  
  // Allows players to play another game by clearing the board.
  public void reset() {
    setBoard();
  }
  
  public static String stringMultiplication(String s){
    StringBuilder ret = new StringBuilder();
    for(int i = 0; i < GAME_COLS; i++){
      ret.append(s);
    }
    return ret.toString();
}
  // Displays the board itself.
  public void printBoard(){
    String outerBoarder = stringMultiplication("------------");
    System.out.print(outerBoarder + "\n");
    for(int i = 0; i < GAME_ROWS; i++) {
      for(int j = 0; j < GAME_COLS; j++) {
        if(j == 0) {
          System.out.print(" |");
        }
        if(board[i][j].heroHere && board[i][j].monsterHere) {
          System.out.print(board[i][j].toString() + "|" + board[i][j].curMarker.toString() + "  " + board[i][j].curMonster.toString() + "|" + board[i][j].toString() + "|");  
        }
        else if(board[i][j].monsterHere) {
          System.out.print(board[i][j].toString() + "|  " + board[i][j].curMonster.toString() + "  |" + board[i][j].toString() + "|");  
        }
        else System.out.print(board[i][j].toString() + "|  " + board[i][j].curMarker.toString() + "  |" + board[i][j].toString() + "|");  
        if(j < 7) System.out.print("|");
      }
      System.out.print("\n");
      System.out.print(outerBoarder + " \n");

    }
    System.out.print(AnsiColors.ANSI_BRIGHTYELLOW + "      1            2                      3           4                      5           6\n"  + AnsiColors.ANSI_RESET);   
    System.out.println("\nLegend:");
    System.out.println(AnsiColors.ANSI_RED + "  I ~ Not Accessible" + AnsiColors.ANSI_RESET);
    System.out.println(AnsiColors.ANSI_BRIGHTBLUE + "  N ~ Nexus" + AnsiColors.ANSI_RESET);
    System.out.println(AnsiColors.ANSI_PLAINGREEN + "  P ~ Plain" + AnsiColors.ANSI_RESET);
    System.out.println(AnsiColors.ANSI_PRETTYPURPLE + "  K ~ Koulou" + AnsiColors.ANSI_RESET);
    System.out.println(AnsiColors.ANSI_PRETTYPURPLE + "  C ~ Cave" + AnsiColors.ANSI_RESET);
    System.out.println(AnsiColors.ANSI_PRETTYPURPLE + "  B ~ Bush" + AnsiColors.ANSI_RESET);
  }
  
  public static void printTeleportBoard(Player inputHero){
    ArrayList<Integer> avoidCells = Lanes.avoidCells();
    int avoidLane = inputHero.position % 8;
    String outerBoarder = stringMultiplication("------------");
    System.out.print(outerBoarder + "\n");
    for(int i = 0; i < GAME_ROWS; i++) {
      for(int j = 0; j < GAME_COLS; j++) {
        if(j == 0) {
          System.out.print(" |");
        }
        if(board[i][j].heroHere && board[i][j].monsterHere) {
          System.out.print(board[i][j].toString() + "|" + board[i][j].curMarker.toString() + "  " + board[i][j].curMonster.toString() + "|" + board[i][j].toString() + "|");  
        }
        else if(board[i][j].monsterHere) {
          System.out.print(board[i][j].toString() + "|  " + board[i][j].curMonster.toString() + "  |" + board[i][j].toString() + "|");  
        }
        else if(board[i][j].heroHere) {
          System.out.print(board[i][j].toString() + "|  " + board[i][j].curMarker.toString() + "  |" + board[i][j].toString() + "|");  
        }
        else if(board[i][j].id % 8 == avoidLane) {
          System.out.print(board[i][j].toString() + "|  " + board[i][j].curMarker.toString() + "  |" + board[i][j].toString() + "|");  
        }
        else if(board[i][j].curMarker.compareTo("X") == 0) {
          System.out.print(board[i][j].toString() + "|  " + board[i][j].curMarker.toString() + "  |" + board[i][j].toString() + "|");  
        }
        else if(avoidCells.contains(board[i][j].id)) {
          System.out.print(board[i][j].toString() + "|  " + board[i][j].curMarker.toString() + "  |" + board[i][j].toString() + "|");  
        }
        else if(board[i][j].id < 10) {
          System.out.print(board[i][j].toString() + "|  "  + AnsiColors.ANSI_WHITE + board[i][j].id +  AnsiColors.ANSI_RESET + "   |"  + board[i][j].toString() + "|");  
        }
        else if(board[i][j].id > 9)System.out.print(board[i][j].toString() + "|  "  + AnsiColors.ANSI_WHITE + board[i][j].id +  AnsiColors.ANSI_RESET + "  |" + board[i][j].toString() + "|");  
        if(j < 7) System.out.print("|");
      }
      System.out.print("\n");
      System.out.print(outerBoarder + " \n");
      }
    }
  
  
  
 public static boolean moveUp(Player inputHero) {
   ArrayList<Integer> avoidCells = Lanes.avoidCellsWithoutMonsters();
   int potentialMove = inputHero.position - GAME_ROWS;
   if(avoidCells.contains(potentialMove)) return false;
   if(potentialMove < 0 || potentialMove > GAME_AREA + 1) return false;
   Cell target = findRowColNumber(potentialMove);
   Cell current = findRowColNumber(inputHero.position);
   if(target.curMarker == null) return false;
   if(current.heroHere == true && current.monsterHere == true) return false;
   if(inputHero instanceof Hero && target.heroHere == true) return false;
   if(inputHero instanceof Monster && target.monsterHere == true) return false;
   if(target.curMarker.compareTo("X") == 0)return false;
   else return true; 
 }
 
 public static boolean moveDown(Player inputHero) {
   ArrayList<Integer> avoidCells = Lanes.avoidCellsWithoutMonsters();
   int potentialMove = inputHero.position + GAME_ROWS;
   if(avoidCells.contains(potentialMove)) return false;
   if(potentialMove < 0 || potentialMove > GAME_AREA + 1) return false;
   Cell target = findRowColNumber(potentialMove);
   Cell current = findRowColNumber(inputHero.position);
   if(target.curMarker == null) return false;
   if(current.heroHere == true && current.monsterHere == true) return false;
   if(inputHero instanceof Hero && target.heroHere == true) return false;
   if(inputHero instanceof Monster && target.monsterHere == true) return false;
   if(target.curMarker.compareTo("X") == 0) return false;
   else return true; 
 }
 
 public static boolean moveRight(Player inputHero) {
   ArrayList<Integer> avoidCells = Lanes.avoidCellsWithoutMonsters();
   int potentialMove = inputHero.position + 1;
   if(avoidCells.contains(potentialMove)) return false;
   if(potentialMove % GAME_ROWS == 1) return false;
   Cell target = findRowColNumber(potentialMove);
   Cell current = findRowColNumber(inputHero.position);
   if(target.curMarker == null) return false;
   if(current.heroHere == true && current.monsterHere == true) return false;
   if(inputHero instanceof Hero && target.heroHere == true) return false;
   if(inputHero instanceof Monster && target.monsterHere == true) return false;
   if(target.curMarker.compareTo("X") == 0) return false;
   else return true; 
 }
 
 public static boolean moveLeft(Player inputHero) {
   ArrayList<Integer> avoidCells = Lanes.avoidCellsWithoutMonsters();
   int potentialMove = inputHero.position - 1;
   if(avoidCells.contains(potentialMove)) return false;
   if(potentialMove % GAME_ROWS == 0) return false;
   Cell target = findRowColNumber(potentialMove); 
   Cell current = findRowColNumber(inputHero.position);
   if(target.curMarker == null) return false;
   if(current.heroHere == true && current.monsterHere == true) return false;
   if(inputHero instanceof Hero && target.heroHere == true) return false;
   if(inputHero instanceof Monster && target.monsterHere == true) return false;
   if(target.curMarker.compareTo("X") == 0) return false;
   else return true; 
 }
 

 public static void teleport(Player inputPlayer) {
   ArrayList<Integer> avoidCells = Lanes.avoidCells();
   System.out.println("\n");
   printTeleportBoard(inputPlayer);
   boolean success = false;
   do {
     System.out.println("\nPlease select a cell number from the board to teleport to.\n");
     int cell = input.nextInt();
     if(!avoidCells.contains(cell)) { 
       Cell target = findRowColNumber(cell); 
       if(target.id % 8 == inputPlayer.position % 8) {
         System.out.println("\nYou can not teleport to a cell in your lane.\n");
       }
       else if(target.curMarker.compareTo("X") == 0) {
         System.out.println("\nYou can not teleport to an inaccessible.\n");
       }
       else if(target.heroHere) {
         System.out.println("\nThis cell is occupied by another hero.\n");
       }
       else {
         Cell revert = findRowColNumber(inputPlayer.position);
         revert.curMarker = new Marker(" ");
         revert.curHero = null;
         revert.heroHere = false;
         inputPlayer.position = cell;
         target.curMarker = new Marker(inputPlayer.nickname);
         target.heroHere = true;
         target.curHero = (Hero) inputPlayer;
         success = true;
       }
     }
   }while(!success);
   System.out.println("\n");
 }
 
 public static Cell findPlayer(Player curPlayer) {
   // Only allow monster to fight if hero is in the cell or the left/right but not facing
   int tempPosition = curPlayer.position;
   Cell target = findRowColNumber(tempPosition);
   if(curPlayer instanceof Monster) {
     if(target.heroHere)  return target;
     tempPosition -= 1;
     target = findRowColNumber(tempPosition);
     if(moveLeft(curPlayer)) {
       if(target.heroHere) return target;
     }
     tempPosition += 2;
     target = findRowColNumber(tempPosition);
     if(moveRight(curPlayer)) {
       if(target.heroHere) return target;
     }
   }  
   else if (curPlayer instanceof Hero){
     tempPosition = curPlayer.position;
     target = findRowColNumber(tempPosition);
     if(target.monsterHere)  {
       return target;
     }
     tempPosition -= 1;
     target = findRowColNumber(tempPosition);
     if(moveLeft(curPlayer)) {
       if(target.monsterHere) {
         return target;
       }
     }
     tempPosition += 2;
     target = findRowColNumber(tempPosition);
     if(moveRight(curPlayer)) {
       if(target.monsterHere) {
         return target;
       }
     }
   }
  return null;
 }
 



 public static void moveHero(String move, Player curPlayer) {
   Cell revert = findRowColNumber(curPlayer.position);
   if(curPlayer instanceof Hero) {
     revert.heroHere = false;
     revert.curMarker = new Marker(" ");
     revert.curHero = null;
   }
   if(curPlayer instanceof Monster)  {
     revert.monsterHere = false;
     revert.curMonster = new Marker(" ");
     revert.curEnemy = null;
   }
   if(move.compareTo("W") == 0 || move.compareTo("w") == 0) {
     curPlayer.position -= GAME_ROWS; 
   }
   if(move.compareTo("A") == 0 || move.compareTo("a") == 0) {
     curPlayer.position -= 1;
   }
   if(move.compareTo("D") == 0 || move.compareTo("d") == 0) {
     curPlayer.position += 1;
   }
   if(move.compareTo("S") == 0 || move.compareTo("s") == 0) {
     curPlayer.position += GAME_ROWS;
   }

   Cell target = findRowColNumber(curPlayer.position);
   if(curPlayer instanceof Hero) {
     target.cellAttribute((Hero)curPlayer);
     target.heroHere = true;
     target.curMarker = new Marker(curPlayer.nickname);
     target.curHero = (Hero) curPlayer;
   }
   else if (curPlayer instanceof Monster){
     target.monsterHere = true;
     target.curMonster = new Marker(curPlayer.nickname);
     target.curEnemy = (Monster) curPlayer;
   }
   
 }
}


