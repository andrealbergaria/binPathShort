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
		 	 
		 
		 public static void decryptPlainTextBlock(BigInteger minKey,BigInteger maxKey,boolean debug) {
			 
			 
			 
				//key is equal to characters
				
				 byte[] keyAsBytes;
				 
				 while (minKey.compareTo(maxKey) < 0) {
					
					keyAsBytes = minKey.toByteArray();
					
			     	if (util.isAscii(keyAsBytes,false)) {
			     		
			     		allPlainTexts.add(keyAsBytes);
			     		
			     	}
					minKey = minKey.add(BigInteger.ONE);
				 }
			 
			
		 }

		 public static void getPlainTextBlock() {
			 
				
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