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
		
		 System.arraycopy
		 
		 
		 	public static File cipherFile = new File("/home/andrec/workspace_3_8/binPathShort/files/cipherText");


		 public static String plainTextsPath = "/home/andrec/workspace_3_8/binPathShort/plainTexts";
		 public static String logFilePath = "/home/andrec/workspace_3_8/binPathShort/limits";
		 public static ArrayList<char[]> allPlainTexts = new ArrayList();
		 public static byte[] solved= { 'a' ,'b','c'};
		 
		 
		 
public static byte[] decrypt(byte[] cipherText,SecretKeySpec sks,boolean debug) throws Exception {
        	 
	    	 try {
	       // 	util.printArray("KEY",sks.getEncoded());
	        
	        byte[] iv = new byte[16];
	  		Cipher cipher;
	  		IvParameterSpec ivspec = new IvParameterSpec(iv);
	  		cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	  		System.out.println("cypher len : "+cipherText.length);
	  		System.out.println("\nSize Key " +sks.getEncoded().length;
	  		
	        
	        cipher.init(Cipher.DECRYPT_MODE, sks, ivspec);
	        
		     System.out.println("\nCipherText len  "+cipherText.length);
	        byte[] decrypt =cipher.doFinal(cipherText);
	        	
	        	 return decrypt;
	        	 	
        	}
	        catch(BadPaddingException e) {
	        	throw new Exception(e);
	        	
	        }
	  		catch (IllegalBlockSizeException e) {
	  			
	  			throw new Exception(e);
            } catch (NoSuchAlgorithmException e) {
				
            	throw new Exception(e);
				
				
				
			} catch (NoSuchPaddingException e) {
				
				throw new Exception(e);
			} catch (InvalidKeyException e) {
				
				throw new Exception(e);
				
			} catch (InvalidAlgorithmParameterException e) {
				throw new Exception(e);	
				
				
			}
           	
	    	 		           
	          
	        
	        
	    }
		 
		 public static void tryKey(byte[] cipherText,BigInteger min,BigInteger max) throws Exception  {

			 							//byte[] keyAsBytes;
			 						byte[] keyAsBytes = new byte[32];
			 						Arrays.fill(keyAsBytes, 0,30,(byte)0);
			 						Arrays.fill(keyAsBytes,30,32,(byte)0x61);
			 						
			 						System.arraycopy(keyAsBytes,0,keyAsBytes,0,12);
			 						    
			 						SecretKeySpec sks;
		                                 byte[] tempPlainText;
		                                 //char[] t2 = { 'a','b'};
		                                 try {
		                                 while (min.compareTo(max) < 0) {
		                            //             keyAsBytes = min.toByteArray();
		                                         sks = new SecretKeySpec(keyAsBytes, "AES");
		                                         
		                                        tempPlainText = decrypt(cipherText,sks, false);
		                                        if ( util.isAscii(tempPlainText) == true)
		                                        		System.out.println(new String(tempPlainText));
		                                        min = min.add(BigInteger.ONE);
		                                 }
		                                 }
		                                 catch(Exception e) {
		                                	throw e;
		                                 }	 
		                                 
		                                 
		                            }
		                             

		 

		 public static void getKey(String interval,BigInteger minP,BigInteger maxP) {
//			 Integer max value 2147483647
//			 Short max value 32767
//			 Long max 9223372036854775807
			 FileOutputStream
			 try {
			 
			 byte[] cipherText = util.readCipherText(AES.cipherFile,false);
			 
			 	if (interval == null || minP == null || maxP == null) {
			 		throw new IllegalArgumentException();
			 	}
			 	// two bytes *
			 	long k = (long)Math.pow(1.407374884,14); //(k == (max_long / 65336))
				 
			    BigInteger min = minP;
				 BigInteger max = maxP;
				 
				 // o numero de elementos dum conjunto é o max valor
				 long nanoSecs=-1;
				 long secs=-1;
				 long mill=-1;
				 long begin = System.nanoTime();
				 
				 
				 // if i = 125*
				 //for (; k > 0 && foundKey==false; k++) {
				 for (int it=0; it <  1; it++) {
					 
					 tryKey(cipherText,min,max);
					 int a = bytesSize(min);
					 int b = bytesSize(max);
					 System.out.println("Interval " + interval+"\nMin "+min+" Bytes : "+a);
					 System.out.println("Max "+max+" Bytes : "+b);
					
						 
						 
							    
					  
					 
					 min = new BigInteger(max.toString());
					 max =max.add(new BigInteger(interval));
					 
					 nanoSecs = System.nanoTime() - begin;
						secs = nanoSecs / 1000000000;
						mill = nanoSecs / 1000000;
						System.out.println("\nTime elapsed . Secs ("+secs+") mill ("+mill+") nano ("+nanoSecs+")");
					 

				 }
			 }
				 catch(Exception e) {
					 e.printStackTrace();
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
		 
		 //https://stackoverflow.com/questions/8377050/how-do-i-determine-number-of-bytes-needed-to-represent-a-given-integer
		 public static int bytesSize(BigInteger val) {
			    int size = 0;
			    
			    BigInteger shift;

			    while(val.compareTo(BigInteger.ZERO) > 0) {
			    	shift = val.shiftRight(8);
			    	val = shift;
			        size++;
			    }
			    return size;
			}
	 }
		 
		 
			
		 
		
         
         	 
         
         
		 