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
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.RandomAccess;



import binPathShort.util;
import binPathShort.AES;






public class util {

	public static void writeLog(BigInteger min,BigInteger max) {
		PrintWriter writer;
		try {
			writer = new PrintWriter("log", "UTF-8");
			writer.println(getDate());
			writer.print(min.toString());
			writer.println(max.toString());
			writer.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//https://stackoverflow.com/questions/4485128/how-do-i-convert-long-to-byte-and-back-in-java
	public static byte[] severallongsToBytes(long[] keys) {
	    byte[] result = new byte[keys.length*8];
	    int idx =0;
	    for (long l : keys) {
	    	for (int i = 0+idx; i <= 7+idx; i++)  {
	    		result[i] = (byte)(l & 0xFF);
	    		l >>= 8;
	    	}
	    	idx+=8;
	    }
	    return result;
	}
	
	public static byte[] longToBytes(long l) {
	    byte[] result = new byte[8];
	    for (int i = 7; i >= 0; i--) {
	        result[i] = (byte)(l & 0xFF);
	        l >>= 8;
	    }
	    return result;
	}
	
	 public static boolean isAscii(byte[] keyToBeTested) {
  	   
		 
     		 for (byte b: keyToBeTested) {
     			 if ( b > 0x7f | b <=0x20) {
     							return false;
     				}
     		 }
     		
         return true;
   	 
     }
	// byte[][] because is uses two blocks of ciphertext
	 public static byte[] readCipherText(File f,boolean debug) {
     	
     	byte[] cipherText = new byte[4]; // checking ints for speed. (4 bytes)
     	
     	
     	try {
    	 
      if (!f.exists()) {
     	 System.out.println("\n[util.c readCipherText() ] Can't read ciphertext "+f.getAbsolutePath()+", since file doesnt exists");
     	 
      	return null;
      }
        
    		
      FileInputStream fis = new FileInputStream(f);
      
      int fileSize = (int) f.length();
      
       
    		  // n = mk
      if (fileSize % 16 != 0) {
     	System.err.println("\n File block size is not a mulitple of 16");
      	System.exit(-1);
      }

      System.out.println("[util.readCypherText] filesize "+f.length());
      int numBytesRead=-1;
      for (int i=0 ; i < AES.numOfBlocksToDecipher ;i++) {
    	  numBytesRead = fis.read(cipherText);
    	  if (numBytesRead  != f.length()) {
        	  	 System.err.println("[util.c readCipherText()] read() != to size of file");
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

	 public static void printArray(String msg,byte[] arr) {
	  		
 		  
		  
		  
		  System.out.println("\n[util.printArray] MSG:  "+msg);
		  System.out.print("[ ");
		  for (int i=0; i < arr.length;i++) {
			    int temp = arr[i] & 0xFF;
			    if (i == arr.length-1)
			    		System.out.print(temp);
			    else
			    	System.out.print(temp + ",");
		  }  
		  System.out.println(" ]\n[util.printArray] Finished Array");
		  
		  
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

	public static void readLog() {
		try {
		     BufferedReader reader = new BufferedReader(new FileReader(new File("./log")));
		     String r = reader.readLine();
		     while (r!=null) {
		    	 // format :  "min max"
		    	 binPathShort.MinMaxValues.add(r);
		     }
		    	 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
