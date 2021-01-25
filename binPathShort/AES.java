package binPathShort;

import java.io.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;




	 public class AES {
		
		 public static char[] plainText = {'a','b','c','a','b','c','a','b','c'};
		 public static byte[] solved = {'a','b','c'};
		 
		 public static char[] str;
		 public static int idx;
		 
		 public static byte[] abc = { 0x61,0x61,0x61}; //0x61,0x61,0x61,0x61,0x61,0x61};
		 
		 
		 
		 public static String interval = "279841"; // (( 23 ^4))
		 
		 public static BigInteger min;
		 public static BigInteger max;
		 
		 public static boolean foundKey=false;
		 public static String plainTextsPath = "/home/andrec/workspace_3_8/binPathShort/plainTexts";
		 public static String logFilePath = "/home/andrec/workspace_3_8/binPathShort/limits";
		 public static ArrayList<char[]> allPlainTexts = new ArrayList();
		 
		 
		 public static void main(String[] args) {
		
			 testCombinations();
		 }
		 	 
		 
		 public static void add() {
			   // 0x7E is last printable character
			   if (str[idx]==0x7E) {
				   idx++;
				   // first printable character
				   str[idx]=0x20;

			   }
			   else
				   str[idx]++;
			   util.printArray("add", str);

		   }
			 
		 
		 public static void combinationRange() {

	 

			 
			 
			 while (min.compareTo(max) < 0) {
				 add();
				 if (Arrays.equals(abc, solved)) {
					 foundKey=true;
					 return;
				 }
				
				
				 min = min.add(BigInteger.ONE);
			 }
				 
				 
				 

				
					

				}
			 
			 
	 
	public static void testCombinations() {
			 
			BigInteger bigInterval = new BigInteger(interval);
			for (int it=0; it < 2 && foundKey==false; it++) {
				 
				 System.out.println("Min "+min.toString(10)+" Max "+max.toString(10));
			
				 combinationRange();
				 
				 min=max;
				 max =max.add(bigInterval);
			}
	}
		 public static void getPlainTextBlock() {
			 
			 // Can't use byte, because in java it doesnt have 255 values (only 128)
			 // 
				
				 String interval= "1572864";
				 min = new BigInteger("0");
				 max = new BigInteger(interval);
				 // o numero de elementos dum conjunto é o max valor
				 int interrupt=0;
				 long nanoSecs=-1;
				 long secs=-1;
				 long mill=-1;
				 long begin = System.nanoTime();
				 // if i = 125*
				 for (int it=0; it < 2 && foundKey==false; it++) {
					 
					 System.out.println("Min "+min.toString(10)+" Max "+max.toString(10));
				
					 combinationRange();
					 
					 min=max;
					 max =max.add(new BigInteger(interval));

					

					 
					 if (interrupt == 2000) {
						nanoSecs = System.nanoTime() - begin;
						secs = nanoSecs / 1000000000;
						mill = nanoSecs / 1000000;
						System.out.println("\nTime elapsed . Secs ("+secs+") mill ("+mill+") nano ("+nanoSecs+")");
						interrupt=0;
					//	allPlainTexts.clear();
				//		ReadAndWrite.writeFile(allPlainTexts);
				//		interrupt = 0;
					 }
					 interrupt++;
					}
					
				

				  nanoSecs = System.nanoTime() - begin;
				  secs = nanoSecs / 1000000000;
				  mill = nanoSecs / 1000000;
				 System.out.println("\nTime elapsed . Secs ("+secs+") mill ("+mill+") nano ("+nanoSecs+")");
				 
				/*  if (allPlainTexts != null && !allPlainTexts.isEmpty()) {
					  // write file lot faster than print it and use bash commands
					  ReadAndWrite.writeFile(allPlainTexts);
				  }
				 else
					 System.out.println("\n[getPlainTextBlock] No plaintext collected");
					 */
			
		 }
		 
			
		 
		
         
         	 
         
         
	 }