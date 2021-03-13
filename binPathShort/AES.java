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
		 public static boolean debug=false;
		public static byte[] IV = {0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0x61,0x61};
		public static byte[] correctKey = new byte[32];
		 public static int numOfBlocksToDecipher;
		 
		 	public static File cipherFile32 = new File("/home/andrec/workspace_3_8/binPathShort/files/cipherText32");
		 	public static File cipherFile16 = new File("/home/andrec/workspace_3_8/binPathShort/files/cipherText16");

		 	public static File cipherFile = cipherFile32;

		 
		    
		    // END SITE
public static byte[][] decrypt(byte[][] cipherText,SecretKeySpec skv)  {
        	 
		
			
		
			byte[][] buf = new byte[AES.numOfBlocksToDecipher][16];
	    	 try {
	    		 
	       // 	util.printArray("KEY",sks.getEncoded());
	        Cipher cipher;
	  		IvParameterSpec ivspec = new IvParameterSpec(AES.IV);
	  		//cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	  		cipher = Cipher.getInstance("AES/CBC/NOPADDING");
	  		
	        cipher.init(Cipher.DECRYPT_MODE, skv,ivspec);
	        
	        
	        // Cipher Text correct (checked with od -t x --endian big cipherText32)

	        for (int i=0; i < AES.numOfBlocksToDecipher; i++) {
	        	buf[i] =cipher.doFinal(cipherText[i]);
	        	
	        }
	        
	        
	        return buf;
	    	 }
	        catch(BadPaddingException e) {
	        	if (AES.debug)
	        	util.printArray("Invalid Key" ,skv.getEncoded(),false);
	        }
	        	
	        catch(InvalidKeyException e) {
	        	if (AES.debug)
	        	util.printArray("Invalid Key" ,skv.getEncoded(),false);
	    		 
	    	 }
	        
	  		catch (IllegalBlockSizeException e) {
	        	if (AES.debug)

	  			util.printArray("Invalid Key" ,skv.getEncoded(),false);
            } catch (NoSuchAlgorithmException e) {
	        	if (AES.debug)

            	util.printArray("Invalid Key" ,skv.getEncoded(),false);
				
				
				
			} catch (NoSuchPaddingException e) {
	        	if (AES.debug)

				util.printArray("Invalid Key" ,skv.getEncoded(),false);
			} catch (InvalidAlgorithmParameterException e) {
	        	if (AES.debug)

				// TODO Auto-generated catch block
				util.printArray("Invalid Key" ,skv.getEncoded(),false);
			} 
	    	 
	    	 		           
	    	 return buf;
	        
	        
	    }
		 public static void tryCorrectKey() {
			 try {
				 
				 byte[][] cipherText = util.readCipherText(AES.cipherFile, false);
				 
			 byte[] key = new byte[32];
             

			 key[0] =0x61;
			 key[1] = 0x61;
			 
			 
			 byte[] iv = new byte[16];
			 
			 iv[0] = 0x61;
			 iv[1] =0x61;
			 //iv[2] =0x61;
			 //iv[2] =0x61;
		
			 //iv[3] = 0x65;
			 
				SecretKeySpec sk;
				sk = new SecretKeySpec(key, "AES");
				
					// Cipher Text correct (checked with od -t x --endian big cipherText32)
				byte[][] temp= decrypt(cipherText, sk);
					for (int i=0; i < AES.numOfBlocksToDecipher; i++) {
						if ( util.isAscii(temp[i]) == true) {
							System.out.println("PlainText : "+new String(temp[i]));
							util.printArray(null,sk.getEncoded(),true);
						}
				}
				
			 }
			 catch(Exception e) {
				 e.printStackTrace();
			 }
			 
		 }
		 
		
          
		 public static void tryKey(byte[][] cipherText) throws Exception  {

			 /*
			  * 
			  * On CBC mode, we can't get block1 (because of IV), so we try our key with the 2block
			  * and then try to retrieve the key.
			  *long[] keys = new long[4]; This will be required on tsting 32 bytes 
			  *
			  */
			 
			 						 
			 						    
			 						    SecretKeySpec sk;
		                                byte[][] temp = new byte[AES.numOfBlocksToDecipher][16];
		                                byte[] tempArray;
		                                 
		                                 byte[] key = new byte[32];
		                                 
		                                 // o numero de elementos dum conjunto é o max valor
		                				 
		                                  while (!Arrays.equals(min,max) {
		                                	  key = util.shortArrayToByteArray(binPathShort.min);
		                                	     sk = new SecretKeySpec(key, "AES");
		                                       
		                                        // IV is static (must be initialized on running)
		                                         temp= decrypt(cipherText,sk);
		                                       
		                                        	for (int block=0; block < AES.numOfBlocksToDecipher; block++) {
		                                        		if (util.isAscii(temp[block]) == true) {
		                                        		 util.writePossibleKey(key,temp[block],"0x"+min.toString(16)); // key in bytes and write id on file test in this case
		                                        		 if (AES.debug)
		   		                 						 System.out.println("\nFound Key "+Arrays.toString(key));
		   		                 						 
		   		                 						 
		   		                                	
		                                        		}
		                                        	}
		                 					  
		                                        min = min.add(BigInteger.ONE);	
		                                        
		                                        
		                                 }
		                                 
		                                 
		                            }
		                             

		 

		 public static void cycle(BigInteger min,BigInteger max,BigInteger interval) {
//			 Integer max value 2147483647
//			 Short max value 32767
//			 Long max 9223372036854775807
			 
			 try {
			 
			 byte[][] cipherText = util.readCipherText(AES.cipherFile,false);
			 
				 // o numero de elementos dum conjunto é o max valor
				 long nanoSecs=0;
				 long secs=0;
				 long mill=0;
				 long begin = System.nanoTime();
				 
				 long nanoIt=0;
				 long secsIt=0;
				 long millIt=0;
				 long nanoTemp=0;
				  
				 BigInteger maxInt = new BigInteger("2147483647");
				 while(min.compareTo(maxInt) < 0) {
					 
					 nanoSecs = System.nanoTime()-begin;
					 nanoTemp=System.nanoTime();
					 secs = nanoSecs / 1000000000;
					 mill = nanoSecs / 1000000;
					 if (debug)
					 System.out.println("\nTotal Time elapsed . Secs ("+secs+") mill ("+mill+") nano ("+nanoSecs+")");
					 if (debug)
					 System.out.println("\n[ITERATION "+binPathShort.iterator+"] Secs ("+secsIt+") mill ("+millIt+") nano ("+nanoIt+")");;
					 //System.out.print(" Min 0x"+min.toString(16));
					// System.out.print(" Max 0x"+max.toString(16));
					 
					 AES.tryKey(cipherText,min,max);
					 
					 util.writeLog(secsIt,min,max,"Iteration "+binPathShort.iterator);
					 
					 min = max;
					 max=max.add(interval);
					 
					 nanoIt = System.nanoTime()-nanoTemp;
					 secsIt = nanoIt / 1000000000;
					 millIt = nanoIt / 1000000;
					 binPathShort.iterator++;

				 }
				 if (debug)
				 System.out.println("\n[END TOTAL ITERATIONS] Time elapsed . Secs ("+secs+") mill ("+mill+") nano ("+nanoSecs+")");
					 

				 }
			 
				 catch(Exception e) {
					 e.printStackTrace();
				 }
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
		 
		 
			
		 
		
         
         	 
         
         
		 