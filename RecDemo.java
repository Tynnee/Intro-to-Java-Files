//Recursion demonstration, CSC 151

public class RecDemo
{
  
  public static int fact(int n) //fact: factorial, the following is the manual code for factorial
    {
    if (n == 0) {return 1;
    }
    else {return n*fact(n-1);     
    }
  public static void main(String[] args)
  {
  
   
  System.out.println(fact(5));
  }
}
}