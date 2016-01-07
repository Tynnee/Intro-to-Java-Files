//Henry Weeks Problem 1.5.32

public class Clock 
{ 

  public static void main(String[] args) 
  { 

    for (int t = 0; true; t++) 
    {

      double s = t % 60;            //define hands so that they all move once a second
      double m = (t / 60.0) % 60;  
      double h   = (t / 3600.0) % 12;

      StdDraw.clear(StdDraw.WHITE);    //refresh background every frame
      StdDraw.setPenRadius();

      StdDraw.setPenColor(StdDraw.GREEN);  //draw long skinny second hand
      StdDraw.setPenRadius(.01);     
      double a1 = Math.toRadians(6 * s);
      double r1 = 0.45;
      StdDraw.line(0.5, 0.5, 0.5 + r1 * Math.sin(a1), 0.5 + r1 * Math.cos(a1));

      StdDraw.setPenRadius(.02);         //draw shorter thicker minute hand
      StdDraw.setPenColor(StdDraw.BLACK);
      double a2 = Math.toRadians(6 * m);
      double r2 = 0.375;
      StdDraw.line(0.5, 0.5, 0.5 + r2 * Math.sin(a2), 0.5 + r2 * Math.cos(a2));

      StdDraw.setPenRadius(.03);         //draw shortest and thickest hour hand
      StdDraw.setPenColor(StdDraw.GRAY);
      double a3 = Math.toRadians(30 * h);
      double r3 = 0.25;
      StdDraw.line(0.5, 0.5, 0.5 + r3 * Math.sin(a3), 0.5 + r3 * Math.cos(a3));

      StdDraw.show(1000);   // change frames once a second
    }
  }
}