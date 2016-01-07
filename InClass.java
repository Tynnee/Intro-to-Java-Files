public class InClass
{
    public static void main(String[] args)
    {
      /*
 int b = 0;
 int c = 0;
 int d = b + c;
 int[] a = new int[10];
 for(int i = 0; i < 10; i++)
     {
  a[i] = 1;
     }
 System.out.println(a[0]);
 a = new int[10];
 System.out.println(a[0]);
 */
 //This is bad!
 /*
 int[] b = null;
        b = new int[10];
 for(int i = 0; i < 10; i++)
     {
  b[i] = 1;
     }
 System.out.println(b);
 for(int i = 0; i < 10; i++)
     {
  System.out.println(b[i]);
     }
    */
 /* //Dot Product
 double[] x = {1.0, 2.0, 3.0};
 double[] y = {3.5, 2.2, 1.5};
 double dotProd = 0.0;
 if(x.length == y.length) //This is known as a guard
     {
  for(int i = 0; i < x.length; i++)
      {
   dotProd += x[i] * y[i];
      }
  System.out.println(dotProd);
     }
 else
     {
  System.out.println("Dot product not defined");
     }
 */
 /*
  //Finding the maximum of an array
 int[] x = {3, 2, 1, 6, 4, 5, 7};
 int k = 0;
 for(int i = 0; i < x.length; i++)
     {
  if(x[i] > k)
      {
   k = x[i];
      }
     }
 System.out.println(k);
 */
 /* //Reversing and copying arrays
 int[] x = {3, 2, 1, 6, 4, 5, 7};
 for(int i = 0; i < x.length/2; i++)
     {
  int k = x[i];
  x[i] = x[x.length - 1 - i];
  x[x.length - i - 1] = k;
     }
 for(int i = 0; i < x.length; i++)
     {
  System.out.print(x[i] + " ");
     }
 System.out.println();
 int[] y;
 y = x;
 y[0] = 8;
 for(int i = 0; i < x.length; i++)
     {
  System.out.print(x[i] + " ");
     }
 System.out.println(); //This is bad as y is pointing to the same block of memory
 //We need to copy the array x into a NEW array
 int[] z;
 z = new int[x.length];
 for(int i = 0;  i < x.length; i++)
     {
  z[i] = x[i];
     }
 z[0] = 9;
 for(int i = 0; i < x.length; i++)
     {
  System.out.print(x[i] + " ");
     }
 System.out.println();
 for(int i = 0; i < z.length; i++)
     {
  System.out.print(z[i] + " ");
     }
 System.out.println();
 */
 String[] suit = {"Clubs", "Diamonds", "Hearts", "Spades"};
 String[] rank = {"2", "3", "4","5", "6", "7","8", "9", "10", "J", "Q", "K", "A"};
 //int i = (int) (Math.random() * rank.length);
 //int j = (int) (Math.random() * suit.length);
 //System.out.println(rank[i] + " of " + suit[j]);
 String[] deck = new String[suit.length * rank.length];
 for(int i = 0; i < suit.length; i++)
     for(int j = 0; j < rank.length; j++)
  deck[rank.length*i + j] = rank[j] + " of " + suit[i];
 for(int i = 0; i < deck.length; i++)
     System.out.println(deck[i]);
 System.out.println("Shuffled");
 //Swapping the first and eleventh card
 /*String temp = deck[0];
 deck[0] = deck[10];
 deck[10] = temp;
 for(int i = 0; i < deck.length; i++)
     System.out.println(deck[i]);
 */
 for(int i = 0; i < 2 * deck.length; i++)
     {
  int r1 = (int)(Math.random() * (deck.length - 1));
  int r2 = (int)(Math.random() * (deck.length - 1));
  String temp = deck[r1];
  deck[r1] = deck[r2];
  deck[r2] = temp;
     }
 for(int i = 0; i < deck.length; i++)
     System.out.println(deck[i]);

 
 
 
       
    }
}