package binPathShort;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.RandomAccess;

import com.sun.corba.se.impl.ior.ByteBuffer;

import binPathShort.util;
import binPathShort.AES;






public class util {

	 public static byte[] readCipherText(File f,boolean debug) {
     	int len = (int) f.length();
     	byte[] cipherText = new byte[len];
     	
     	try {
    	 
      if (!f.exists()) {
     	 System.out.println("\nCan't read ciphertext "+f.getAbsolutePath()+", since file doesnt exists");
     	 
      	return null;
      }
      FileInputStream fis = new FileInputStream(f);
      
      int fileSize = (int) f.length();
      
      System.out.println("\nSize of cipher text file "+fileSize);
      if (256 % fileSize != 0) {
     	System.err.println("\n File size is not a mulitple of 256");
      	System.exit(-1);
      }
      
      int numBytesRead = 0;
      
      numBytesRead = fis.read(cipherText);
      
      if (numBytesRead  <= 0) {
     	 System.err.println("Read returned error or zero");
     	 System.exit(-1);
      }
      
      else {

     	 System.out.println("(readCypherText) Read "+numBytesRead+" from "+f.getAbsolutePath()+" fileSize "+f.length());
      		if (256 % cipherText.length != 0) {
      			System.err.println("\n Ciphertext not size of block");
      			System.exit(-1);
      		}
      		if(numBytesRead % 2 != 0 ) {
      			System.err.println("\n Didnt read the number of bytes from ciphertext");
      			System.exit(-1);
      		}
      }
      		fis.close();
      		return cipherText;
      }
      catch(FileNotFoundException e) {
      		e.printStackTrace();
     }
     	catch(IOException e) {
     		e.printStackTrace();
     	}
     	return cipherText;
     }
	public static void printAllPlaintexts() {
	
	int index = 0;

	char[] b;
	for (int i=0; i < AES.allPlainTexts.size(); i++) {
		System.out.println();
		// type of b (byte[])
		b = (char[]) AES.allPlainTexts.get(i);
		System.out.println(Arrays.toString(b));
		index++;
	}
	System.out.println("\nNumber of plaintexts printed "+index);
		
	
	}

	 public static void printArray(String arrayName,byte[] arr) {
	  		
 		  
		  System.out.println("---begin array ("+arrayName+")---");
		  System.out.println("Array length : "+arr.length);

		  System.out.println("[ ");
		  for (int i=0; i < arr.length;i++) {
			    byte temp = arr[i];
			    	if (temp< 0x20 || temp > 0x7e)
			    		System.out.print("!");
			    	else
			    		System.out.print(temp);
			 
		  }  
		  System.out.print("\n]");
		  
	     	System.out.println("\n---end array ---");
	}
	
	
	  public static void printArray(String arrayName,char[] arr) {
  		
  		  
		  System.out.println("---begin array ("+arrayName+")---");
		  System.out.println("Array length : "+arr.length);

		  System.out.println("[ ");
		  for (int i=0; i < arr.length;i++) {
			    char temp = arr[i];
				  System.out.print(","+temp);
			 
		  }  
		  System.out.print("\n]");
		  
	     	System.out.println("\n---end array ---");
	}
		// showChars, instead of ascii number return character
	  // print values == true => print values instead of characters
	  
	// somePlainTexts, not to save all into memory and then write it

	
	public static String getDate() {
		 
		 
		  
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS");
		LocalDateTime ldt = LocalDateTime.now();
		String dateString = ldt.format(df);
		return dateString;
	}

}
