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
		
		 public static int numOfBlocksToDecipher;
		 
		 	public static File cipherFile32 = new File("/home/andrec/workspace_3_8/binPathShort/files/cipherText32");
		 	public static File cipherFile16 = new File("/home/andrec/workspace_3_8/binPathShort/files/cipherText16");

		 	public static File cipherFile = cipherFile32;

		 public static String plainTextPath = "/home/andrec/workspace_3_8/binPathShort/files/plainText";
		 public static String logFilePath = "/home/andrec/workspace_3_8/binPathShort/limits";
		 public static ArrayList<char[]> allPlainTexts = new ArrayList<>();
		 public static byte[] solved= { 'a' ,'b','c'};
		 
		 
		 /**https://stackoverflow.com/questions/15554296/initial-bytes-incorrect-after-java-aes-cbc-decryption
		     * Decrypts the given byte array
		     *
		     * @param cipherText The data to decrypt
		     */
		    public static byte[] decryptSite(byte[] cipherText,SecretKeySpec key) throws Exception
		    {
		        Cipher cipher = Cipher.getInstance("AES");
		        cipher.init(Cipher.DECRYPT_MODE, key);

		        return cipher.doFinal(cipherText);
		    }
		    
		    // END SITE
public static byte[][] decrypt(byte[][] cipherText,SecretKeySpec skv,byte[] iv)  {
        	 
		
			
		
			byte[][] buf = new byte[AES.numOfBlocksToDecipher][16];
	    	 try {
	    		 
	       // 	util.printArray("KEY",sks.getEncoded());
	        Cipher cipher;
	  		IvParameterSpec ivspec = new IvParameterSpec(iv);
	  		//cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	  		cipher = Cipher.getInstance("AES/CBC/NOPADDING");
	  		
	        cipher.init(Cipher.DECRYPT_MODE, skv,ivspec);
	        
	        
	        // Cipher Text correct (checked with od -t x --endian big cipherText32)

	        /*for (int i=0; i < AES.numOfBlocksToDecipher; i++) {
	        	buf[i] =cipher.doFinal(cipherText[i]);
	        	
	        }*/
	        buf[0] = cipher.doFinal(cipherText[0]);
	        return buf;
	    	 }
	        catch(BadPaddingException e) {
	        	System.out.println("[AES.decrypt(). BadPaddingException] Invalid key ");
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
			} catch (InvalidAlgorithmParameterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	    	 
	    	 		           
	    	 return buf;
	        
	        
	    }
		 public static void tryCorrectKey() {
			 try {
				 
				 byte[][] cipherText = util.readCipherText(AES.cipherFile, false);
				 
			 byte[] key = new byte[32];
             

			 key[0] =0x61;
			 key[1] = 0x61;
			 key[2] = 0x61;
			 
			 byte[] iv = new byte[16];
			 
			 iv[0] = 0x61;
			 iv[1] =0x61;
			 //iv[2] =0x61;
			 iv[2] = 0x61;
			 //iv[3] = 0x65;
			 
				SecretKeySpec sk;
				sk = new SecretKeySpec(key, "AES");
				
					// Cipher Text correct (checked with od -t x --endian big cipherText32)
				byte[][] temp= decrypt(cipherText, sk,iv);
				if (temp == null) {
					System.out.println("\n[AES.tryCorrectKey()] Key incorrect . decrypt returned null");
				}
				else {
					for (int i=0; i < AES.numOfBlocksToDecipher; i++) {
						if ( util.isAscii(temp[i]) == true) {
							util.printArray(null,temp[i],true);
							util.printArray(null,sk.getEncoded(),true);
						}
				 
						
						
					}	 
				}
				
			 }
			 catch(Exception e) {
				 e.printStackTrace();
			 }
			 
		 }
		 
		
          public static byte[] getIV() {
        	  byte[] iv = new byte[] {0x61,0x61,0x61,0};
        	  return iv;
          }

		 
		 public static void tryKey(byte[][] cipherText,BigInteger min,BigInteger max) throws Exception  {

			 /*
			  * 
			  * On CBC mode, we can't get block1 (because of IV), so we try our key with the 2block
			  * and then try to retrieve the key.
			  *long[] keys = new long[4]; This will be required on tsting 32 bytes 
			  *
			  */
			 						 
			 						    
			 						    SecretKeySpec sk;
		                                byte[][] temp = new byte[AES.numOfBlocksToDecipher][16];
		                                
		                                 //byte[] key = new byte[32];
		                                 
		                                 while (min.compareTo(max) < 0) {
		                                	 	 
		                                	 			 
		                                         sk = new SecretKeySpec(min.toByteArray(), "AES");
		                                       
		                                        
		                                         temp= decrypt(cipherText,sk,getIV());
		                                        if (temp == null) {
		                                        	System.out.println("\nNo suitable Key that decrypts ASCII values");
		                                        }
		                                        else {
		                                        	for (int block=0; block < AES.numOfBlocksToDecipher; block++) {
		                                        		if (util.isAscii(temp[block]) == true) {
	                            							util.printArray(null,temp[block],true);
	                            							util.printArray(null,sk.getEncoded(),true);
	                            					
		                                        		}
		                                        	}
		                                        }
		                            					
		                                        min.add(BigInteger.ONE);
		                                        
		                                        
		                                 }
		                                 
		                                 
		                            }
		                             

		 

		 public static void cycle(BigInteger min,BigInteger max,long interval) {
//			 Integer max value 2147483647
//			 Short max value 32767
//			 Long max 9223372036854775807
			 
			 try {
			 
			 byte[][] cipherText = util.readCipherText(AES.cipherFile,false);
			 
				 // o numero de elementos dum conjunto é o max valor
				 long nanoSecs=-1;
				 long secs=-1;
				 long mill=-1;
				 long begin = System.nanoTime();
				 
				 
				 // if i = 125*
				 //for (; k > 0 && foundKey==false; k++) {
				 for (int it=0; it <  10; it++) {
					 AES.tryKey(cipherText,min,max);
					 System.out.println("Iteration : "+it+"\nMin "+min+"\nMax "+max);
					 
					 BigInteger biInterval = new BigInteger("1048576");
					 if (min.remainder(biInterval) == BigInteger.ZERO) 
						 util.writeLog(min,max);
					 
					 min = max;
					 max.add(new BigInteger(String.valueOf(interval)));
				 }
					 nanoSecs = System.nanoTime() - begin;
						secs = nanoSecs / 1000000000;
						mill = nanoSecs / 1000000;
						System.out.println("\n[END] Time elapsed . Secs ("+secs+") mill ("+mill+") nano ("+nanoSecs+")");
					 

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
		 
		 
			
		 
		
         
         	 
         
         
		 