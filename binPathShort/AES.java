package binPathShort;

import java.io.*;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
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
		
		 
		 
		 
		 	public static File cipherFile = new File("/home/andrec/workspace_3_8/binPathShort/files/cipherText");


		 public static String plainTextsPath = "/home/andrec/workspace_3_8/binPathShort/plainTexts";
		 public static String logFilePath = "/home/andrec/workspace_3_8/binPathShort/limits";
		 public static ArrayList<char[]> allPlainTexts = new ArrayList();
		 public static byte[] solved= { 'a' ,'b','c'};
		 
		 
		 
public static byte[] decrypt(byte[] cipherText,SecretKeySpec skv,byte[] iv)  {
        	 
		
			
		
			byte[] buf = null;
	    	 try {
	    		 
	       // 	util.printArray("KEY",sks.getEncoded());
	        Cipher cipher;
	  		IvParameterSpec ivspec = new IvParameterSpec(iv);
	  		cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	  		
	        
	        cipher.init(Cipher.DECRYPT_MODE, skv);
	        
	        buf =cipher.doFinal(cipherText);
	    	 }
	        catch(BadPaddingException e) {
	        	System.out.println("[AES.BadPaddingException] Invalid key ");
	        }
	        	
	        catch(InvalidKeyException e) {
	    		 e.printStackTrace();
	    		 
	    	 }
	        
	  		catch (IllegalBlockSizeException e) {
	  			
	  			e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
				
            	e.printStackTrace();
				
				
				
			} catch (NoSuchPaddingException e) {
				
				e.printStackTrace();
			} 
	    	 catch (InvalidAlgorithmParameterException e) {
	    		 e.printStackTrace();	
			}
           	
	    	 		           
	    	 return buf;
	        
	        
	    }
		 public static void tryCorrectKey() {
			 try {
				 
				 byte[] cipherText = util.readCipherText(AES.cipherFile, false);
				 
			 byte[] key = new byte[32];
             

			 key[0] =0x61;
			 key[1] = 0x61;
			 key[2] = 0x61;
			 
			 byte[] iv = new byte[16];
			 
			 iv[0] = 0x61;
			 iv[1] =0x61;
			 iv[2] = 0x61;
			 
				SecretKeySpec sk;
				sk = new SecretKeySpec(key, "AES");
				byte[] temp = decrypt(cipherText, sk,iv);
				
				 if ( util.isAscii(temp) == true)
                 		System.out.println(new String(temp));
				util.printArray("[AES.tryCorrectKey]","Decrypt correct", key);
			 }
			 catch(Exception e) {
				 e.printStackTrace();
			 }
			 
		 }
		 
		
          public static byte[] getIV() {
        	  byte[] iv = new byte[] {0x61,0x61,0x61,0};
        	  return iv;
          }

		 
		 public static void tryKey(byte[] secondBlockCipherText,long min,long max) throws Exception  {

			 /*
			  * 
			  * On CBC mode, we can't get block1 (because of IV), so we try our key with the 2block
			  * and then try to retrieve the key.
			  *long[] keys = new long[4]; This will be required on tsting 32 bytes 
			  *
			  */
			 						 
			 						    
			 						    SecretKeySpec sk;
		                                byte[] tempPlainText;
		                                
		                                 byte[] key = new byte[8];
		                                 
		                                 while (min <= max) {
		                                	 	 key = util.longToBytes(min);
		                                         sk = new SecretKeySpec(key, "AES");
		                                         util.printArray("[AES.trykey]","KeySpec",key);
		                                       
		                                         // Return BadPaddingException
		                                   //     tempPlainText = decrypt(secondBlockCipherText,sk);
		                                        if (tempPlainText == null) {
		                                        	System.out.println("\nKey doesnt decrypt cipherText");
		                                        }
		                                        else
		                                        	if ( util.isAscii(tempPlainText) == true)
		                                        		System.out.println(new String(tempPlainText));
		                                        min++;
		                                        
		                                        
		                                 }
		                                 
		                                 
		                            }
		                             

		 

		 public static void getKey(long min,long max,long interval) {
//			 Integer max value 2147483647
//			 Short max value 32767
//			 Long max 9223372036854775807
			 
			 try {
			 
			 byte[] cipherText = util.readCipherText(AES.cipherFile,false);
			 
			 	// two bytes *
			 	long k = (long)Math.pow(1.407374884,14); //(k == (max_long / 65336))
				 
			     if (interval % 2 != 0) {
			    	 System.err.println("\nInterval not multiple of "+interval+"... exiting");
			     	System.exit(-1);
			     }
			     
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
					
						 
						 
							    
					  
					 
					 min = max;
					 max += interval;
					 
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
		 public static int bytesSize(long val) {
			    int size = 0;
			    
			    

			    while(val > 0) {
			    	val >>= 8;
			    	size++;
			    }
			    return size;
			}
	 }
		 
		 
			
		 
		
         
         	 
         
         
		 