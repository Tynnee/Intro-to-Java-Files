public class Ramanujan
{
  public static void main(String[] args)
  {
    int N = Integer.parseInt(args[0]);  //Declare maximum integer
    
     for (int a = 1; a <= N; a++)     //Declare first base factor
     {
       int A = a * a * a;    //Declare it's cube
       if (A > N) break;     //Stop at N

     for (int b = a; b <= N; b++) //Declare second base factor
     {
       int B = b * b * b;    //Declare it's cube
       if (A + B > N) break;     //Stop at N

     for (int c = a + 1; c <= N; c++) //Declare third base factor
     {
       int C = c * c * c;    //Declare it's cube
       if (C > A + B) break;     //Stop at N


      for (int d = c; d <= N; d++) //Declare fourth base factor
      {
        int D = d * d * d;    //Declare it's cube
        if (C + D > A + B) break;     //If formula is successful, stop.

      if (C + D == A + B) 
      {
                     
         System.out.print((A + B) + " = ");           //Print out the numbers and all the factors in formula
         System.out.print(a + "^3 + " + b + "^3 = "); //and end the line after each formula.
         System.out.print(c + "^3 + " + d + "^3");
         System.out.println();
      }
     }
    }
   }
  }
 }
}
