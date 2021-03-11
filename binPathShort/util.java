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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.RandomAccess;

import javax.xml.crypto.dsig.keyinfo.KeyInfo;



import binPathShort.util;
import binPathShort.AES;






public class util {

	public static void writeLog(BigInteger min,BigInteger max,String id) {
		try {
		    
			
			String s = getDate();
			s+="\nID : "+id;
			s+="\n0x"+min.toString(16)+" ";
			s+="0x"+max.toString(16)+"\n";
			Files.write(Paths.get("log"), s.getBytes(), StandardOpenOption.APPEND);
			
			
			System.out.println("\nWrote log [0x"+min.toString(16)+",0x"+max.toString(16)+"]");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
     			 if ( b > 0x7e | b < 0x20) {
     							return false;
     				}
     		 }
     		
         return true;
   	 
     }
	 
	 public static boolean testCipherText(byte[][] cipherText) {
		 // use on console on dir files/ 
		 //od -t x --endian big cipherText32  | cut -f 2,3,4,5 -d " "
		 /*
		  * 2e9b58c0 cc5387bb 93fba95b 938b780a
			5a252af4 04c1a70e 921a1e1f d725317b
		  * 
		  */
		 return false;
	 }
	 
	// byte[][] because is uses two blocks of ciphertext
	 public static byte[][] readCipherText(File f,boolean debug) {
     	
     	 
     	
     	byte[][] cipherText = null;
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
      
      AES.numOfBlocksToDecipher = (int) f.length() / 16;
      cipherText = new byte[AES.numOfBlocksToDecipher][16];
      
      int numBytesRead=-1;
      for (int i=0 ; i < AES.numOfBlocksToDecipher ;i++) {
    	  numBytesRead = fis.read(cipherText[i]);
    	  if (numBytesRead  != 16) {
        	  	 System.err.println("[util.c readCipherText()] operation read() didn't read 16 bytes");
    	     	 System.exit(-1);
    	      }
    	  
      }  
            
     	 
     fis.close();
     // Reads correcly ciphertext (tested with od -t x --endian big cipherText32)
     //util.printArray("CipherText READ ", cipherText[0]);
     //util.printArray("CipherText READ ", cipherText[1]);
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

	 public static void writePossibleKey(byte[] key,byte[] plainText,String id) {
		 try {
			PrintWriter pw = new PrintWriter(new FileWriter(new File("possible_keys"),true));
			
			pw.println(util.getDate());
			pw.println("ID : "+id);
			pw.println("PlainText : "+Arrays.toString(plainText));
			pw.println("Key : "+Arrays.toString(key));
			pw.close();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }

	
	 public static void printArray(String msg,byte[] arr,boolean foundKey) {
	  		
 		  
		  
		  if (!foundKey)
			  System.out.println("\n[util.printArray] MSG:  "+msg);
		  String s1="",s2="";
		  for (int i=0; i < arr.length;i++) {
			    int temp = arr[i] & 0xFF;
			    s1 += (char) temp +",";
			    s2 += "0x"+String.format("%x",temp)+",";
			    }
		  if (foundKey)
			  System.out.println("KEY FOUND");
		    System.out.print("[ "+s1+"]\n[ "+s2+"]\n");  
	 	
		  
		  
		  
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
			File log = new File("log");
			
			if (log.length() ==0 || !log.exists()) {
					System.out.println("\nlog file doesn't exist or is empty..assuming default");
					// default
					binPathShort.min = new BigInteger("0");
			    	binPathShort.max = new BigInteger("524288"); // 524288 => 65536*8
			    	return;
			}
			else {
				
		     BufferedReader reader = new BufferedReader(new FileReader(log));
		     String line="",minMaxRead="";
			 int countLines =0;

			    while ((line = reader.readLine()) != null) 
			    {	countLines++;
			        minMaxRead = line;
			    }
			    
			    String[] minMax = minMaxRead.split(" ");
			    
			    if (countLines % 3 != 0 && countLines != 0 || minMax.length != 2) {
			    	System.out.println("\nWrong log format, assuming default [0,524288]");
			    	// default
			    	binPathShort.min = new BigInteger("0");
			    	binPathShort.max = new BigInteger("524288"); // 524288 => 65536*8
			    }
			    else {
 
			    binPathShort.min = new BigInteger(minMax[0].substring(2),16);
			    binPathShort.max = new BigInteger(minMax[1].substring(2),16);
			    
				System.out.println("\n[util.java readLog()]  Min "+binPathShort.min+" Max "+ binPathShort.max);
			    }
			    reader.close();
			}
		     	 // format :  "min max"
		    	 
		    	 
		     }
		    	 
		 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
