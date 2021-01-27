package binPathShort;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class util {

	public static void printAllPlaintexts() {
	int index = 0;
	
	byte[] b;
	for (int i=0; i < AES.allPlainTexts.size(); i++) {
		System.out.println();
		// type of b (byte[])
		b = (byte[]) AES.allPlainTexts.get(i);
		System.out.println(Arrays.toString(b));
		index++;
	}
	System.out.println("\nNumber of plaintexts printed "+index);
		
	
	}

	
	// l  represents the long (key to be checked for ascii)
      public static boolean isAscii(byte[] keyToBeTested,boolean debug) {
    	   
    	  if (debug) 
    		  printArray("isAscii",keyToBeTested,true);
    		 
      		 for (byte b: keyToBeTested) {
      			 if ( b > 0x7f | b <=0x20) {
      					if (b!=0)
      						return false;
      				}
      		 }
      		
          return true;
    	 
      }
      
      
      
      public static String getPlaintext(byte[] bArr) {
    	  
    	  char [] temp = new char[bArr.length];
    	  
    	  for (int i=0; i < bArr.length; i++) {
    		  if (bArr[i] != 0)
    			  temp[i] = (char) bArr[i];
    		  else
    			  temp[i] = '0';
    	  }
    	  
    	  String s= new String(temp);
    	  
    	  return s;
    	  
      }
      	 	
public static boolean isAscii(Byte[] keyToBeTested,boolean debug) {
    	 
    	
    	  
    	  if (debug) {
    		  
    		  printArray("isAscii",keyToBeTested,true);
    	  }
    		 
      			for (byte b: keyToBeTested) {
      				
      				if ( b > 0x7f | b <=0x20) {
      					if (b!=0)
      						return false;
      				}
      			}
          return true;
    	 
      }
      
      public static void printArray(String arrayName,Byte[] arr,boolean printChars) {
  		
		  System.out.println("---begin array ("+arrayName+")---");
		  System.out.println("Array length : "+arr.length);
		  
		  char c; 
	     	for (byte b : arr) {
	     		
	     		if (printChars) {
	     				if (b == 0)
	     					c = '0';
	     				else
	     				c = (char) b;
	     				System.out.print(c + ",");
	     		}
	     		else {
	     			if (b==0)
	     				System.out.print("0"+",");
	     			else
	     				System.out.print(b + ",");
	     		}
	     	
	     	}
	     	System.out.println("\n---end array ---");
		
	}
		// showChars, instead of ascii number return character
	  // print values == true => print values instead of characters
	  
	public static void printArray(String arrayName,byte[] arr,boolean printChars) {
		
		  System.out.println("---begin array ("+arrayName+")---");
		  System.out.println("Array length : "+arr.length);
		  
		  char c; 
	     	for (byte b : arr) {
	     		
	     		if (printChars) {
	     				if (b == 0)
	     					c = '0';
	     				else
	     				c = (char) b;
	     				System.out.print(c + ",");
	     		}
	     		else {
	     			if (b==0)
	     				System.out.print("0"+",");
	     			else
	     				System.out.print(b + ",");
	     		}
	     	
	     	}
	     	System.out.println("\n---end array ---");
		
	}
	// somePlainTexts, not to save all into memory and then write it

	public static void writeFile(ArrayList somePlainTexts) {
		try {
			
			
			FileWriter fw = new FileWriter(AES.plainTextsPath,false);
 
			
		for (int i=0; i < somePlainTexts.size() ; i++) {
			byte[] bc = (byte[]) somePlainTexts.get(i);
				String str = getPlaintext(bc);
				fw.write(str+"\n");
			}
			
		
		
		System.out.println("[util.writeFile] Wrote plainTexts files");
		fw.close();
		
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}
