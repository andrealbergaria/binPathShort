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
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.RandomAccess;
import java.util.StringTokenizer;

import javax.xml.crypto.dsig.keyinfo.KeyInfo;



import binPathShort.util;
import binPathShort.AES;






public class util {

	// timeElapsed-> time took to complete iteration
	public static void writeLog(long timeElapsed,BigInteger min,BigInteger max,String id) {
		try {
		    
			
			String s = getDate();
			s+="\nID : "+id+" ("+timeElapsed+") secs";
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

	public static short[] convertIntToShortArray(int i) {
		ByteBuffer bb = ByteBuffer.allocate(4);
		bb.putInt(i);
		return bb.asShortBuffer().array();
	}
	// 
	// 	FROM https://stackoverflow.com/questions/5625573/byte-array-to-short-array-and-back-again-in-java
	public static short[] convertStringToShortArray(String s) {
		byte[] t = s.getBytes();
		short[] shorts = new short[t.length/2];
		return ByteBuffer.wrap(t).asShortBuffer().put(shorts).array();

	}
	// iteration is 0 -> 0xffff
	 /*public static void getFFFF() {
		 
		 for (int i=0; i < 0xffff;i++) {
			 callFFFF();
		 }
		 
	 }
		*/ 
		 public static void callFFFF() {
			 System.out.print(" [ ");
			 for (int i=0; i < 0xffff; i++) 
				 System.out.print(i+",");
			 System.out.println(" ] ");
		 }
	
		// for (int i=0; i < 16; i++) {
	//		 callFFFF();
	//	 }

	//public it {
		//lenOfArray = 16 16 * callFFFF() = {0x -> 0xffff}
		//  
		// 
	
	 // adds one to number represented by the short array val1
		  
		 public static int getIndexNotFFFF(short[] arr) {
			 for (int i=0; i < arr.length; i++){
				 if (arr[i] ==0  || arr[i] < 0xffff)
					 return i;
			 }
			 return -1;
			 
		 }
		public static short[] add(short[] arr) {
			int idx = getIndexNotFFFF(arr);
			if (idx == -1) { 
				System.out.println("\n[util add()] idx -1");
				return null;
			}
			else
				arr[idx]++;
			return arr;
		}
		 
	// byte array used in key
	// https://stackoverflow.com/questions/10804852/how-to-convert-short-array-to-byte-array
	public static byte [] shortArrayToByteArray(short [] input)
	{
	  int short_index, byte_index;
	  int iterations = input.length;

	  byte [] buffer = new byte[input.length * 2];

	  short_index = byte_index = 0;

	  for(/*NOP*/; short_index != iterations; /*NOP*/)
	  {
	    buffer[byte_index]     = (byte) (input[short_index] & 0x00FF); 
	    buffer[byte_index + 1] = (byte) ((input[short_index] & 0xFF00) >> 8);

	    ++short_index; byte_index += 2;
	  }

	  return buffer;
	}
	public static void readLog() {
		try {
			
			
			Path path = FileSystems.getDefault().getPath("log");
			File f = path.toFile();
			
				
				List<String> allLines = 	Files.readAllLines(path, Charset.defaultCharset());
			 
			 
			    String lastIterator = allLines.get(allLines.size()-2);
			    String lastMinMax = allLines.get(allLines.size()-1);
			    
			    String[] minMax = lastMinMax.split(" ");
			    StringTokenizer toks = new StringTokenizer(lastIterator);
			    
			    if (allLines.size() % 3 != 0 || minMax.length != 2 || toks.countTokens() != 6 || f.length() ==0 || !f.exists()) {
			    	System.out.println("\nWrong log format, assuming default [0,0x80000], iterator = 0");
			    	// default
			    	binPathShort.max = convertIntToShortArray(0x80000); // default 524288 => 65536*8 => 0x80000
					binPathShort.min[0] = 0;
			    	binPathShort.iterator=0;
			    	
			    }
			    else {
			    	String itNumber = "";
			    	for (int i=0; i < 4;i++)
			    		itNumber = toks.nextToken();
			    	
			    binPathShort.min = convertStringToShortArray(minMax[0].substring(2));
			    binPathShort.max = convertStringToShortArray(minMax[1].substring(2));
			    binPathShort.iterator = Integer.parseInt(itNumber);
			    
				System.out.println("\n[util.java readLog()]  Min 0x"+Arrays.toString(binPathShort.min)+" Max 0x"+ Arrays.toString(binPathShort.max)+" Iterator "+itNumber);
				
			    
			    
			}
		     	 // 
		    	 
		    	 
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
