import java.util.Scanner;

public class ChooseGame {
  private static Scanner input = new Scanner(System.in);
  public static boolean isQOLBoard;
  public static boolean quitGames = false;
  
  public static void entry() {
    boolean playAgain = false;
    while (!playAgain) {
    System.out.println("\n\t\t        ,     \\    /      ,        \n" + 
        "\t\t       / \\    )\\__/(     / \\       \n" + 
        "\t\t      /   \\  (_\\  /_)   /   \\      \n" + 
        "\t\t ____/_____\\__\\@  @/___/_____\\____ \n" + 
        "\t\t|             |\\../|              |\n" + 
        "\t\t|              \\VV/               |\n" + 
        "\t\t|        ---THE  QUEST---         |\n" + 
        "\t\t|_________________________________|\n" + 
        "\t\t |    /\\ /      \\\\       \\ /\\    | \n" + 
        "\t\t |  /   V        ))       V   \\  | \n" + 
        "\t\t |/     `       //        '     \\| \n" + 
        "\t\t `              V                '\n");
    System.out.println("\nWhich version of The Quest would you like to play?\n");
    System.out.println("\nGAME SELECTION:\n" + 
                          " (1) The Quest\n" +
                          " (2) The Quest of Legends\n");
    boolean play = false;
    do {
      int user = input.nextInt();
      if(user == 1) {
        isQOLBoard = false;
        QuestGame playGame = new QuestGame();
        if(quitGames) play = true;
      }
      else if(user == 2) {
        isQOLBoard = true;
        QuestOfLegendsGame playGame = new QuestOfLegendsGame();
        if(quitGames) play = true;
      }
      else System.out.println("\nPlease enter a valid number.\n");
    }while(!play);
    System.out.println("\nWould you like to play again? Press Y/y for yes and any key for no.\n");
    String user = input.nextLine();
    if(user.compareTo("y") != 0 || user.compareTo("Y") != 0) {
      playAgain = true;
    }
  }
  }
  
}
