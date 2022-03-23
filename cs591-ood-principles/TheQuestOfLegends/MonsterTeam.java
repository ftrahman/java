import java.util.Random;

public class MonsterTeam {
  public static Monster[] team = null;
  public int length;
  public int totalAlive;
  
  public MonsterTeam(int length) {
    team = new Monster[length];
    this.length = length;
    totalAlive = length;
  }
  
  private Monster getPlayer(int index) {
    return team[index];
  }
  
  public void assignEnemies(int index) {
    Cell target = null;
    Random r = new Random();
    for(int i = index; i < length; i++) {
      if(team[i] == null && i == 0) {
        int selection = findLevel(0);
        team[i] = new Dragon(selection);
        team[i].nickname = "M" + i;
        team[i].position = r.nextInt(2) + 1;    
        team[i].startingPosition = team[i].position;
        target = QuestOfLegendsBoard.findRowColNumber(team[0].position);
      }
      else if(team[i] == null && i == 1) {
        int selection = findLevel(1);
        team[i] = new Exoskeleton(selection);
        team[i].nickname = "M" + i;
        team[i].position = r.nextInt(2) + 4;
        team[i].startingPosition = team[i].position;
        target = QuestOfLegendsBoard.findRowColNumber(team[1].position);
      }
      else if(team[i] == null && i == 2) {
        int selection = findLevel(2);
        team[i] = new Spirit(selection);
        team[i].nickname = "M" + i;
        team[i].position = r.nextInt(2) + 7;
        team[i].startingPosition = team[i].position;
        target = QuestOfLegendsBoard.findRowColNumber(team[2].position);
      }
      target.curEnemy = team[i];
      target.curMonster = new Marker(team[i].nickname);
      target.monsterHere = true;
    }
  }
  
  public boolean checkVitality() {
    for(int i = 0; i < length; i++) {
      if(!team[i].alive) {
        totalAlive--;
      }
      if (totalAlive == 0) {
        return false;
      }
    }
    return true;
  }
  
  public void respawn() {
    Monster[] temp = new Monster[totalAlive + length];
    for(int i = 0; i < length; i++) {
      if(team[i].alive) temp[i] = team[i];
    }
    team = temp;
    for(int i = length - 1; i < totalAlive+length; i++) {
      assignEnemies(totalAlive - 1);
    }
  }
  
  public Monster findOpponent(Hero player, int index) {
    for(int i = index; i < length; i++) {
      if(team[i].alive) {
        return team[i];
      }
      if(i == length - 1) {
        i = 0;
      }
    }
    return null;
  }
  
  private int findLevel(int position) {
    if(Team.team[position] != null) {
          return Team.team[position].level;
        }
    return 0;
  
  }
  
}

