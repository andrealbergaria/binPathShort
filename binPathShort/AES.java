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
		
		 
		 
		 
		 public static BigInteger min;
		 public static BigInteger max;
			public static File cipherFile = new File("/home/andrec/workspace_3_8/binPathShort/files/ciphertext1");

		 public static boolean foundKey=false;
		 public static String plainTextsPath = "/home/andrec/workspace_3_8/binPathShort/plainTexts";
		 public static String logFilePath = "/home/andrec/workspace_3_8/binPathShort/limits";
		 public static ArrayList<char[]> allPlainTexts = new ArrayList();
		 public static char[] solved= { 'a' ,'b','c'};
		 
public static byte[] decrypt(byte[] cipherText,SecretKeySpec sks,boolean debug) {
        	 
	    	 try {
	        	if (debug==true)
	        	System.out.println("\n([decrypt] Decrypting...");
	        
	        byte[] iv = new byte[16];
	  		Cipher cipher;
	  		IvParameterSpec ivspec = new IvParameterSpec(iv);
	  		cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	  		
	        	 
	        cipher.init(Cipher.DECRYPT_MODE, sks, ivspec);
	        
		     
		      
	        byte[] decrypt =cipher.doFinal(cipherText);
	        	 return decrypt;	
	        	 	
        	}
	        catch(BadPaddingException e) {
	        	if (debug==true)
	        	System.out.println("\n[decrypt] Bad padding");
	        	
	        }
	  		catch (IllegalBlockSizeException e) {
	  			
	  			System.out.println("\nIllegal Block");
            } catch (NoSuchAlgorithmException e) {
				
				System.out.println("\n[Exception] No such alg");
				
				
				
			} catch (NoSuchPaddingException e) {
				
				System.out.println("\n[Exception] No such padding");
			} catch (InvalidKeyException e) {
				
				System.out.println("\n[Exception] Invalid Key");
				
			} catch (InvalidAlgorithmParameterException e) {
				
				
				System.out.println("\n[Exception] invalid alg");
			}
           	
	    	 return null;
		           
	          
	        
	        
	    }
		 
		 public static void tryKey()  {

		 char[] keyAsBytes;
		                                 
		                                 //char[] t2 = { 'a','b'};
		                                 while (min.compareTo(max) < 0) {
		                                         keyAsBytes = min.toString().toCharArray();
		                                         foundKey = Arrays.equals(keyAsBytes, solved);
		                                        
		                                         if (foundKey == true) 
		                                                 return;
		                                        min = min.add(BigInteger.ONE);
		                             
		                                 }
		 }

		 //https://stackoverflow.com/questions/8377050/how-do-i-determine-number-of-bytes-needed-to-represent-a-given-integer
		 public static int bytesSize(BigInteger val) {
			    int size = 0;
			    BigInteger comp = new BigInteger("0");
			    BigInteger shift;

			    while(val.compareTo(comp) > 0) {
			    	shift = val.shiftRight(8);
			        size++;
			    }
			    return size;
			}
		 

		 public static void getKey() {
//			 Integer max value 2147483647
//			 Short max value 32767
//			 Long max 9223372036854775807

			 	// two bytes *
			 String interval = "65536";
				long k = (long)Math.pow(1.407374884,14); //(k == (max_long / 65336))
				 String maxIterator = String.valueOf(k); // (65536*2)
				 BigInteger bigI = new BigInteger(maxIterator);
				 min = new BigInteger("0");
				 max = new BigInteger(interval);
				 
				 // o numero de elementos dum conjunto é o max valor
				 long nanoSecs=-1;
				 long secs=-1;
				 long mill=-1;
				 long begin = System.nanoTime();
				 
				 
				 // if i = 125*
				 //for (; k > 0 && foundKey==false; k++) {
				 for (int it=0; it <  65536; it++) {
					 tryKey();
					 int a = bytesSize(min);
					 int b = bytesSize(max);
					 System.out.println("Min "+min+" Bytes : "+a);
					 System.out.println("Max "+max+" Bytes : "+b);
					  
					 
					 min=max;
					 max =max.add(new BigInteger(interval));
					 
					 nanoSecs = System.nanoTime() - begin;
						secs = nanoSecs / 1000000000;
						mill = nanoSecs / 1000000;
						System.out.println("\nTime elapsed . Secs ("+secs+") mill ("+mill+") nano ("+nanoSecs+")");
					 

				 }
					
					 //System.out.println("Min "+min.toString(10)+" Max "+max.toString(10));
				
					 //tryKey();
					 
				//	 min=max;
			//		 max =max.add(new BigInteger(interval));

					/* if (interrupt == 2000) {
						nanoSecs = System.nanoTime() - begin;
						secs = nanoSecs / 1000000000;
						mill = nanoSecs / 1000000;
						System.out.println("\nTime elapsed . Secs ("+secs+") mill ("+mill+") nano ("+nanoSecs+")");
						interrupt=0;
					 }
					 interrupt++;
					}
					
				

				  nanoSecs = System.nanoTime() - begin;
				  secs = nanoSecs / 1000000000;
				  mill = nanoSecs / 1000000;
				 System.out.println("\nTime elapsed . Secs ("+secs+") mill ("+mill+") nano ("+nanoSecs+")");
				 */
				/*  if (allPlainTexts != null && !allPlainTexts.isEmpty()) {
					  // write file lot faster than print it and use bash commands
					  ReadAndWrite.writeFile(allPlainTexts);
				  }
				 else
					 System.out.println("\n[getPlainTextBlock] No plaintext collected");
					 */
			
		 }
	 }
		 
		 
			
		 
		
         
         	 
         
         
		 