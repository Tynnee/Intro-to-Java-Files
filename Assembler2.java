import java.util.Scanner;
import java.io.PrintStream;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;


public class Assembler {

 public static void main(String[] args) throws IOException {
  // take the input file name as a command line argument
  String filename = args[0];
  
  
  // Hashtable can be used to store <key, value> pairs 
  Hashtable<String, String> destTable;
  destTable = new Hashtable<String, String>();
  destTable.put("0", "000");
  destTable.put("M", "001");
  destTable.put("MD", "011");
  destTable.put("D", "010");
  destTable.put("A", "100");
  destTable.put("AM", "101");
  destTable.put("AD", "110");
  destTable.put("AMD", "111");
  
  String dest = "M";
  System.out.println(dest + " = " + destTable.get(dest));
  
  Hashtable<String, String> cTable; //Control bits hash table
  cTable = new Hashtable<String, String>();
  cTable.put("0","0101010"); //Control bits when a=0
  cTable.put("1","0111111");
  cTable.put("-1","0111010");
  cTable.put("D","0001100");
  cTable.put("A","0110000");
  cTable.put("!D","0001101");
  cTable.put("!A","0110001");
  cTable.put("-D","0001111");
  cTable.put("-A","0110011");
  cTable.put("D+1","0011111");
  cTable.put("A+1","0110111");
  cTable.put("D-1","0001110");
  cTable.put("A-1","0110010");
  cTable.put("D+A","0000010");
  cTable.put("D-A","0010011");
  cTable.put("A-D","0000111");
  cTable.put("D&A","0000000");
  cTable.put("D|A","0010101");
  
  cTable.put("M","1110000");  //Control Bits when a=1
  cTable.put("!M","1110001");
  cTable.put("-M","1110011");
  cTable.put("M+1","1110010");
  cTable.put("M-1","1110010");
  cTable.put("D+M","1000010");
  cTable.put("D-M","1010011");
  cTable.put("M-D","1000111");
  cTable.put("D&M","1000000");
  cTable.put("D|M","1010101");
  
  Hashtable<String, String> jTable; //Jump bits hash table               
  jTable = new Hashtable<String, String>();
  jTable.put("null","000");
  jTable.put("JGT","001");
  jTable.put("JEQ","010");
  jTable.put("JGE","011");
  jTable.put("JLT","100");
  jTable.put("JNE","101");
  jTable.put("JLE","110");
  jTable.put("JMP","111");
  
    Hashtable<String, String> symTable; //Jump bits hash table               
  symTable = new Hashtable<String, String>();
  symTable.put("R0","0000000000000000");
  symTable.put("R1","0000000000000001");
  symTable.put("R2","0000000000000010");
  symTable.put("R3","0000000000000011");
  symTable.put("R4","0000000000000100");
  symTable.put("R5","0000000000000101");
  symTable.put("R6","0000000000000110");
  symTable.put("R7","0000000000000111");
  symTable.put("R8","0000000000001000");
  symTable.put("R9","0000000000001001");
  symTable.put("R10","0000000000001010");
  symTable.put("R11","0000000000001011");
  symTable.put("R12","0000000000001100");
  symTable.put("R13","0000000000001101");
  symTable.put("R14","0000000000001110");
  symTable.put("R15","0000000000001111");
  symTable.put("SCREEN","0100000000000000");
  symTable.put("SP","0000000000000000");
  symTable.put("LCL","0000000000000001");
  symTable.put("ARG","0000000000000010");
  symTable.put("THIS","0000000000000011");
  symTable.put("THAT","0000000000000100");
  

  Scanner in = new Scanner(new File(filename)); //reads a new file as an input file
  String outPrefix = filename.substring(0, filename.length()-4); //takes off .asm from the filename
  PrintStream out = new PrintStream(new File(outPrefix + ".hack")); //defines an output file of the same name .hack
 
  int i = 0;
  
  while (in.hasNextLine()) { 
   
   String line = in.nextLine(); 
   line = line.replaceAll("\\s", ""); // remove all whitespace -- regular expressions are cool <http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html#sum>
   String cment = "//";
   if(line.contains(cment)) {
     String[] comments = line.split("//");
     line = comments[0];
   } 
  
  int length = line.length();
  if(length>0) {
  i++;
  
    String instruct = line.substring(0,1);
    String par = "(";
    int compare = instruct.compareToIgnoreCase(par);
    String stringLength = line.substring(1, length - 1);
    if (compare = 0){
    i--;  
    
    if(symTable.containsKey(stringLength)){ //if the symbol is in symTable do nothing  
    }
  
    else{ 
    int binbin = i;
    String binReal = Integer.toBinaryString(binbin);
    symTable.put(StringLength, make16(binReal));  //puts into the symTable
    }
}

}
}


    Scanner in2 = new Scanner(new File(filename)); //reads a new file as an input file
    
    
     String instruct = line.substring(0,1);
    String at = "@";
    int compare = instruct.compareToIgnoreCase(at);
    String stringLength = line.substring(1, length);
  if(compare == 0) {
    
    if (line.contains("=")) {
    String[] splitsies = line.split("=");
    
    String juan = jTable.get(splitsies[0]);
    String atu = cTable.get(splitsies[1]);
    
    System.out.println("111" + atu + juan + "000");
    
    }
  if(line.contains(";")){
    String[] splitsies2 = line.split(";");
      
    String juan2 = cTable.get(splitsies2[0]);
    String atu2 = jTable.get(splitsies2[1]);
      
    System.out.println("111" + atu2 + "000" + juan2);
    }
  else {
   
  }  
 
  
  out.println(line);
  
 
  }
  
  in.close();
  out.close();
 }

public static String make16(String alpha){
  
  int lenny = alpha.length();
  String bravo = "0";
  String charlie = "";
  for(int i = 0; i < 16-lenny; i++) {
    charlie = charlie+bravo;
    }
  charlie = charlie+alpha;
  
  return charlie;
}  
}    