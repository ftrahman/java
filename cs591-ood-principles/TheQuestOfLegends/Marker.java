
/*
 * Marker: A class that defines a Marker object.
 * 
 * public Marker(String type): A constructor to help assign markers. 
 * private void setMarker(String type): A setter method for defining the value of a Marker.
 * public String toString(): Provide a string representation of the Marker object.
 * public int compareTo(String string): Helps to make Marker object comparable.
 */

public class Marker {
  
  public String id;
  
  public Marker(String type) {
    setMarker(type);
  }
  
  private void setMarker(String type) {
    if(type.compareTo(" ") == 0) id = " ";
    else id = type;
   
  }
  

  public String toString() {
    String first = id.substring(0,1);
    if(first.compareTo("H") == 0){
      return AnsiColors.ANSI_CYAN + id + AnsiColors.ANSI_RESET;
    }
    else if(first.compareTo("M") == 0){
      return AnsiColors.ANSI_YELLOW + id + AnsiColors.ANSI_RESET;
    }
    else if(id.compareTo("X") == 0){
      return AnsiColors.ANSI_RED + "X" + AnsiColors.ANSI_RESET;
    }
    else if(ChooseGame.isQOLBoard) return "  ";
    else return " ";
  }

  public int compareTo(String string) {
    if(string.compareTo(id) == 0) {
      return 0;
    }
    return -1;
  }
}
