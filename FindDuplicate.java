public class FindDuplicate
{
  public static void main(String[] args)
  {
    boolean d = false;
    int[] A = {1,3,5,4,6,6,7,8};
    int i;
    int j;
  
    for (i = 0; i < A.length; i++)
    for (j = 0; j < A.length; j++)

      if (A[i] == A[j]) d = true;
  
      if (d == true) System.out.println("Duplicates!");
      if (d == false) System.out.println("No duplicates!");

  }
}