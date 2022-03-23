
public class Nexus {
  //public int[] monsterNexus = null;
  public final static int[] heroNexus = {Lanes.LANE_ONE, Lanes.LANE_TWO, Lanes.LANE_THREE,
      Lanes.LANE_FOUR, Lanes.LANE_FIVE, Lanes.LANE_SIX};
  //heroNexus = new int[lanes];
  public final static int[] monsterNexus = {Lanes.LANE_ONE % 8, Lanes.LANE_TWO % 8, Lanes.LANE_THREE % 8,
      Lanes.LANE_FOUR % 8, Lanes.LANE_FIVE % 8, Lanes.LANE_SIX % 8};
  
  public static void findNexus(Hero curHero) {
    Cell origin = QuestOfLegendsBoard.findRowColNumber(curHero.startingPosition);
    Cell current =  QuestOfLegendsBoard.findRowColNumber(curHero.position);
    if(origin.heroHere) {
      for(int i = 0; i < heroNexus.length; i++) {
        Cell target = QuestOfLegendsBoard.findRowColNumber(heroNexus[i]);
        if(!target.heroHere) {
          current.curMarker = new Marker(" ");
          current.heroHere = false;
          curHero.position = heroNexus[i];
          curHero.alive = true;
          target.curMarker = new Marker(curHero.nickname);
          target.heroHere = true;
          break;
        }
      }
    }
    else {
      current.curMarker = new Marker(" ");
      current.heroHere = false;
      curHero.position = curHero.startingPosition;
      origin.curMarker = new Marker(curHero.nickname);
      origin.heroHere = true;
      curHero.alive = true;
    }
  }
  
  public static boolean isWin(Player inputPlayer) {
    for(int i = 0; i < heroNexus.length; i++) {
      if(inputPlayer instanceof Hero && inputPlayer.position == monsterNexus[i]) {
        return true;
      }
      else if (inputPlayer instanceof Monster && inputPlayer.position == heroNexus[i]) {
        return true;
      }
    }
    return false;
  }


  

}
