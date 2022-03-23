/*
 * Cell: A cell class to represent the board tiles.
 * 
 * public Cell(int row, int col): Requires a row, column identifier.
 * public static boolean validCellNumber(int cellNumber): Determine if a given cell number exists. 
 * public static boolean validMove(Cell cell): Determine if a given cell is a valid move.
 */


public class Cell {
  public int row;
  public int col;
  public boolean hero;
  Marker curMarker;
  Marker original;
  Marker temporaryHero = new Marker("H");
  public int id;
  
  public Cell(int row, int col) {
    this.row = row; this.col = col;
    hero = false; id = 0;
  }
  
  public static boolean validCellNumber(int cellNumber) {
    if(cellNumber <= QuestBoard.GAME_AREA && cellNumber > 0) return true;
    else return false;
  }
  
  public static boolean validMove(Cell cell) {
    if(!cell.hero) return true;
    else return false;
  }
  

}
