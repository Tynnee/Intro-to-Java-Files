//Henry Weeks Problem 1.4.30

public class Minesweeper 
{ 
   public static void main(String[] args) 
   { 
     int x1 = Integer.parseInt(args[0]);  //take in the 3 command line arguments for the board and the probability
     int Y = Integer.parseInt(args[1]);
     double p = Double.parseDouble(args[2]);
      
      
     boolean[][] b = new boolean[x1+2][Y+2]; //two dimensional boolean array for bombs with border
     for (int i = 1; i <= x1; i++)
     for (int j = 1; j <= Y; j++)
     b[i][j] = (Math.random() < p);

     System.out.println("Minesweeper!"); 
     for (int i = 1; i <= x1; i++) //print the game
     {
       for (int j = 1; j <= Y; j++)
       if (b[i][j]) System.out.print("* ");
       else  System.out.print("' ");
       System.out.println();
     }

     int[][] w = new int[x1+2][Y+2];  //create new array for the winning case
     for (int i = 1; i <= x1; i++)    //this figures out the number of bombs adjacent to each cell
     for (int j = 1; j <= Y; j++)
     for (int I = i - 1; I <= i + 1; I++)
     for (int J = j - 1; J <= j + 1; J++)
     if (b[I][J]) w[i][j]++;

     System.out.println();
     System.out.println("You win, bitch!");           //print the winning case
     for (int i = 1; i <= x1; i++) 
     {
       for (int j = 1; j <= Y; j++)
       if (b[i][j]) System.out.print("* ");
       else System.out.print(w[i][j] + " ");
       System.out.println();
     }
   }
}
