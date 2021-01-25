package binPathShort;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
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
