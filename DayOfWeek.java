// Henry Weeks problem 1.2.29

public class DayOfWeek 
{
  public static void main(String[] args) 
  { 
    int m = Integer.parseInt(args[0]); //Declare integers for months days and year taken as command line
    int d = Integer.parseInt(args[1]); //arguments.
    int y = Integer.parseInt(args[2]);

    int year = y - (14 - m) / 12;                  //The formula for calculating day of the week on the
    int x = year + year/4 - year/100 + year/400;   //Gregorian Calender.
    int month = m + 12 * ((14 - m) / 12) - 2;
    int day = (d + x + (31*month)/12) % 7;
    
    if (day == 1) System.out.println("Monday");     //if statements for which day to print out when
    if (day == 2) System.out.println("Tuesday");
    if (day == 3) System.out.println("Wednesday");
    if (day == 4) System.out.println("Thursday");
    if (day == 5) System.out.println("Friday");
    if (day == 6) System.out.println("Saturday");
    if (day == 7) System.out.println("Sunday");
   }
}