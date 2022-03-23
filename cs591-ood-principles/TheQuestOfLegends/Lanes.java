import java.util.ArrayList;
import java.util.List;

public class Lanes {
  public static final int LANE_ONE = 57;
  public static final int LANE_TWO = 58;
  public static final int LANE_THREE = 60;
  public static final int LANE_FOUR = 61;
  public static final int LANE_FIVE = 63;
  public static final int LANE_SIX = 64;
  
  public static int getLane(int position) {
    if(position % BoardRequirements.GAME_COLS == LANE_ONE % BoardRequirements.GAME_COLS) {
      return 1;
    }
    else if(position % BoardRequirements.GAME_COLS == LANE_TWO % BoardRequirements.GAME_COLS) {
      return 2;
    }
    else if(position % BoardRequirements.GAME_COLS == LANE_THREE % BoardRequirements.GAME_COLS) {
      return 4;
    }
    else if(position % BoardRequirements.GAME_COLS == LANE_FOUR % BoardRequirements.GAME_COLS) {
      return 5;
    }
    else if(position % BoardRequirements.GAME_COLS == LANE_FIVE % BoardRequirements.GAME_COLS) {
      return 7;
    }
    else if(position % BoardRequirements.GAME_COLS == LANE_SIX % BoardRequirements.GAME_COLS) {
      return 8;
    }
    else return 0;
  }
  
  public static ArrayList<Integer> avoidCells(){
    ArrayList<Integer> avoid = new ArrayList<Integer>();
    for(int i = 0; i < MonsterTeam.team.length; i++) {
      if(MonsterTeam.team[i].alive) {
        int currentPos = MonsterTeam.team[i].position;
        int lane = getLane(MonsterTeam.team[i].position);
        for(int j = lane; j <= currentPos; j += 8) {
          avoid.add(j);
          if(lane == 2 || lane == 5 || lane == 8) {
            if(j - 9 > 0) avoid.add(j - 9);
            }
          else if(lane == 1 || lane == 4 || lane == 7) {
            if(j - 7 > 0) avoid.add(j - 7);
            }
          }     
        }
    }
    return avoid;
    }
  
  public static ArrayList<Integer> avoidCellsWithoutMonsters(){
    ArrayList<Integer> avoid = new ArrayList<Integer>();
    for(int i = 0; i < MonsterTeam.team.length; i++) {
      if(MonsterTeam.team[i].alive) {
        int currentPos = MonsterTeam.team[i].position;
        int lane = getLane(MonsterTeam.team[i].position);
        for(int j = lane; j <= currentPos; j += 8) {
         // avoid.add(j);
          if(lane == 2 || lane == 5 || lane == 8) {
            if(j - 9 > 0) avoid.add(j - 9);
            }
          else if(lane == 1 || lane == 4 || lane == 7) {
            if(j - 7 > 0) avoid.add(j - 7);
            }
          }     
        }
    }
    return avoid;
    }
  
}
