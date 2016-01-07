//Henry Weeks Problem 1.3.40

public class MonteHall
{
  public static void main(String[] args)
  {
    int r = Integer.parseInt(args[0]); //number of rounds
    int w = 0;  //wins after switching
    
    for(int i = 0; i < r; i++) //perform r amount of times
    {
      int p = (int)(3 * Math.random()); //put the prize behind a random door
      int c = (int)(3 * Math.random()); //choose a random door
      int v;
      
      do
      {
        v = (int)(3 * Math.random()); //view a door not containing the prize chosen by host at random
      }
      while((v == c) || (v == p));
      
      int s = 3 - v - c; //select remaining door that player switches to
      if(s == p) w++; //if the player wins, add 1 to wins
    }
  System.out.println((1.0 * w / r) * 100 + "% of games won by switching doors."); //print win ratio (NO INT DIVISION)
  
  }
}