public class MonteHallex {

   public static void main(String[] args) { 
      int N = Integer.parseInt(args[0]);  // # trials
      int wins = 0;                       // # times you win by switching

      // repeat experiment N times 
      for (int i = 0; i < N; i++) {

         // host hides prize behind 1 of 3 doors uniformly at random
         int prize  = (int) (3 * Math.random());

         // contestant selects 1 of 3 doors uniformly at random
         int choice = (int) (3 * Math.random());

         // at random, host reveals an unchosen door not containing prize
         int reveal;
         do {
            reveal = (int) (3 * Math.random());
         } while ((reveal == choice) || (reveal == prize));

         // hack to compute the remaining door which contestent switches to
         int other = 0 + 1 + 2 - reveal - choice;

         // switching leads to a win
         if (other == prize) wins++;
      } 

      // avoid integer division
      System.out.println("Fraction of games won = " + 1.0 * wins / N);
   }

}
