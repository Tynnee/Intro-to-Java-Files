//Henry Weeks problem 1.3.32

public class PrimeCounter
{
  public static void main(String[] args)
  {
    int N = Integer.parseInt(args[0]); // declaring command line argument
    boolean[] isPrime = new boolean[N+1];  // creating boolean array isPrime
    for (int i = 2; i <= N; i++) // array assumes all numbers 2 and up are prime
      isPrime[i] = true;
      
    for (int i = 2; i <= N/i; i++)  // goes through and examines each integer for factors less than N/i
    {
      if (isPrime [i])              
      {
        for (int j = i; j <= N/i; j++)  // N/i saves time because any factors greater than sqrt of N are repeats
          isPrime[i * j] = false;
      }
    }
    int Count = 0;  // count the primes in isPrime
    for (int i = 2; i <= N; i++)
      if(isPrime[i]) Count++;
    System.out.println(Count); // print the number of elements in isPrime
  }
}