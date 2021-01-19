package binPathJava;

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
		 public static String plainTextsPath = "/home/andrec/workspace_3_8/binPathShort/plainTexts";
		 public static ArrayList allPlainTexts = new ArrayList();
		 // returns plaintext on [min,max] 
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
		 
		 public static void main(String[] args) {
			getPlainTextBlock();
		 }
		 
		 public static void getPlainTextBlock()  {
			 

				 
				 

				 String minKeyBigInteger = "0000000000000000";
				 String maxKeyBigInteger = "0000000000065535";
				 
				 BigInteger minKey = new BigInteger(minKeyBigInteger);
				 BigInteger maxKey = new BigInteger(maxKeyBigInteger);
				 
				 
				 long begin = System.nanoTime();
				 for (int it=0; it < 2; it++) {
					 
					 System.out.println("Min "+minKey.toString(10));
					 System.out.println("Max "+maxKey.toString(10));
				
					 decryptPlainTextBlock(minKey,maxKey,false);
					 
					  
					 minKey=maxKey;
					maxKey = maxKey.add(new BigInteger("65536"));
					 
				 }
					
				  long nanoSecs = System.nanoTime() - begin;
				   long secs = nanoSecs / 1000000000;
				   long mill = nanoSecs / 1000000;
				 System.out.println("\nTime elapsed . Secs ("+secs+") mill ("+mill+") nano ("+nanoSecs+")");
				 
				  if (allPlainTexts != null && !allPlainTexts.isEmpty()) {
					  // write file lot faster than print it and use bash commands
					  util.writeFile();
				  }
				 else
					 System.out.println("\n[getPlainTextBlock] No plaintext collected");
			
		 }
			
		 
		
         
         	 
         
         
	 }