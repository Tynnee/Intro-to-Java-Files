import java.util.Scanner;
import java.io.PrintStream;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class Assembler {

 public static void main(String[] args) throws IOException {   // take the input file name as a command line argument

  String filename = args[0];
  

  Hashtable<String, String> destTable;       //table for destination bits
  destTable = new Hashtable<String, String>();
  destTable.put("0",   "000");
  destTable.put("M",  "001");
  destTable.put("MD", "011");
  destTable.put("D", "010");
  destTable.put("A", "100");
  destTable.put("AM", "101");
  destTable.put("AD", "110");
  destTable.put("AMD", "111");
  
  

  Hashtable<String, String> cTable;     //table for control bits
  cTable = new Hashtable<String, String>();
  cTable.put("0", "0101010");
  cTable.put("1", "0111111");
  cTable.put("-1", "0111010");
  cTable.put("D", "0001100");
  cTable.put("A", "0110000");
  cTable.put("!D", "0001101");
  cTable.put("!A", "0110001");
  cTable.put("-D", "0001111");
  cTable.put("-A", "0110011");
  cTable.put("D+1", "0011111");
  cTable.put("A+1", "0110111");
  cTable.put("D-1", "0001110");
  cTable.put("A-1", "0110010");
  cTable.put("D+A", "0000010");
  cTable.put("D-A", "0010011");
  cTable.put("A-D", "0000111");
  cTable.put("D&A", "0000000");
  cTable.put("D|A", "0010101");
  cTable.put("M", "1110000");
  cTable.put("!M", "1110001");
  cTable.put("-M", "1110011");
  cTable.put("M+1", "1110111");
  cTable.put("M-1", "1110010");
  cTable.put("D+M", "1000010");
  cTable.put("D-M", "1010011");
  cTable.put("M-D", "1000111");
  cTable.put("D&M", "1000000");
  cTable.put("D|M", "1010101");
  

  Hashtable<String, String> jTable;     //table for jump bits
  jTable = new Hashtable<String, String>();
  jTable.put("null", "000");
  jTable.put("JGT", "001");
  jTable.put("JEQ", "010");
  jTable.put("JGE", "011");
  jTable.put("JLT", "100");
  jTable.put("JNE", "101");
  jTable.put("JLE", "110");
  jTable.put("JMP", "111");
  

  Hashtable<String, String> symTable;     //table for symbols and labels
  symTable = new Hashtable<String, String>();
   symTable.put("R0", "0000000000000000");
   symTable.put("R1", "0000000000000001");
   symTable.put("R2", "0000000000000010");
   symTable.put("R3", "0000000000000011");
   symTable.put("R4", "0000000000000100");
   symTable.put("R5", "0000000000000101");
   symTable.put("R6", "0000000000000110");
   symTable.put("R7", "0000000000000111");
   symTable.put("R8", "0000000000001000");
   symTable.put("R9", "0000000000001001");
   symTable.put("R10", "0000000000001010");
   symTable.put("R11", "0000000000001011");
   symTable.put("R12", "0000000000001100");
   symTable.put("R13", "0000000000001101");
   symTable.put("R14", "0000000000001110");
   symTable.put("R15", "0000000000001111");
            
   symTable.put("SCREEN", "0100000000000000");              
   symTable.put("KBD", "0110000000000000");
   symTable.put("SP", "0000000000000000");
   symTable.put("LCL", "0000000000000001");
   symTable.put("ARG", "0000000000000010");
   symTable.put("THIS", "0000000000000011");
   symTable.put("THAT", "0000000000000100");
  
  // Scanner is handy for parsing input <http://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html>
  Scanner in = new Scanner(new File(filename));
  // the String class is your friend <http://docs.oracle.com/javase/7/docs/api/java/lang/String.html>
  String outPrefix = filename.substring(0, filename.length()-4);  // here we use substring to trim off the ".asm" extension
  PrintStream out = new PrintStream(new File(outPrefix + ".hack")); // PrintStream is used for output -- supports print and printf
  

  int i =0;    //counter for label location, i.e. the value of the line we jump to for the instruction
  

  while (in.hasNextLine())    // read an input file line by line and write it to an output file
  {
    String nextLine = in.nextLine();
    nextLine = nextLine.replaceAll("\\s", ""); // remove all whitespace -- regular expressions are cool <http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html#sum>
    String cment="//";
    if(nextLine.contains(cment))
    {
      String[] comments =nextLine.split("//");  //Splits the line around the commment symbol
      nextLine=comments[0];
    }
   
    int length = nextLine.length();  //Checks if there is something to read
    if (length>0)
    {
      i++;
      String lineType = nextLine.substring(0,1);
      String par = "(";
      int compare = lineType.compareToIgnoreCase(par);   //Checks the first character in the line to see if its a label
      String stringName = nextLine.substring(1, length-1);
      if(compare == 0)
      {
        i--;
    
        if(symTable.containsKey(stringName)){     //Does nothing if the string is already in symTable
        }
        else{
          int binbin = i;
          String binReal = Integer.toBinaryString(binbin);
          symTable.put(stringName, make16(binReal));  //Puts the string into symTable if not already there
        }
     }
     else 
     {
       String at2 = "@";
       int compare2 = lineType.compareToIgnoreCase(at2); //Checks for the "@" symbol
       if (compare2 == 0) 
       {
         i--;  //Decrement the line counter
      
         int binbin2 = i;
         String binReal2 = Integer.toBinaryString(binbin2);  //Convert the line number to binary
         symTable.put(stringName, make16(binReal2));
       }
     }
   } 
 }
  
 Scanner in2 = new Scanner(new File(filename));   // Reads an input file and writes it to an output file in hack binary

 while (in2.hasNextLine()) 
 {
   String nextLine = in2.nextLine();
   nextLine = nextLine.replaceAll("\\s", "");  //Remove whitespace and comments again
   String cment="//";
   if(nextLine.contains(cment))
   {
     String[] comments =nextLine.split("//");
     nextLine=comments[0];
   }

   int length = nextLine.length(); //Checks if the line has something to read
   if (length>0)
   {
    
     String lineType = nextLine.substring(0,1);  //Checks what kind of instruction it is
    
     String at = "@";
    
     int compare=lineType.compareToIgnoreCase(at);
     String stringName=nextLine.substring(1, length);  //Checks if its an A command
     if(compare==0)
     {
    
       if(symTable.containsKey(stringName))  //Does nothing if the string is already in symTable
       {
         System.out.println(symTable.get(stringName)); 
         out.println(symTable.get(stringName));
       }
       else
       {
         int binbin = Integer.parseInt(stringName);      //Converts the value of the command to binary
         String binReal=Integer.toBinaryString(binbin);
         
         System.out.println(make16(binReal));
         out.println(make16(binReal));
       }
     }
     else
     {
       if(nextLine.contains("="))  //Checks if its a C instruction
       {
         String[] splitsies =nextLine.split("=");
    
         String one = destTable.get(splitsies[0]);  //Splits the line around the equal sign
         String two = cTable.get(splitsies[1]);
    
    
         System.out.println("111" + two + one + "000");  //Prints out 111, control, destination, and then null jump
         out.println("111" + two + one + "000");
       }
       if(nextLine.contains(";"))  //Checks if its a jump instruction
       {
         String[] splitsies2 =nextLine.split(";"); 
    
         String one2 = cTable.get(splitsies2[0]);  //splits the line around the semicolon
         String two2 = jTable.get(splitsies2[1]);
    
         System.out.println("111" + one2 + "000" + two2);  //Prints out 111, control, null destination, and jump
         out.println("111" + one2 + "000" + two2);
       }
     }
   }
 }

 in.close();  //Closes out both respective scanners
 out.close();
}              //Close out the main argument
 
 
public static String make16(String binLength)
{
 
 int binCurrentLength=binLength.length();      //Variables and for loop to ensure all lines are 16 bits
 String addZero="0";
 String Empty="";
 for(int i=0; i< 16-binCurrentLength; i++)
 {
   Empty=Empty+addZero;
 }
 Empty=Empty+binLength;
 
 return Empty;
} 
} //Close out the Class