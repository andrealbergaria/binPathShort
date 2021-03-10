package binPathShort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

import javax.crypto.spec.SecretKeySpec;

public class binPathShort {

	public static ArrayList<String> MinMaxValues = new ArrayList<>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * System.out.println("\nPossible Combinations ");
		System.out.println("\nByte "+Byte.MAX_VALUE*2);
		System.out.println("Character " +Character.MAX_VALUE*2);
		System.out.println("Short " +Short.MAX_VALUE*2);
		System.out.println("Integer "+(long)Integer.MAX_VALUE*2);
		System.out.println("Long  "+Long.MAX_VALUE);
		
	*/
			
			try {
				File f = new File("/home/andrec/workspace_3_8/binPathShort/log");
				BufferedReader input = new BufferedReader(new FileReader(f));
			    String last="", line="";

			    while ((line = input.readLine()) != null) { 
			        last = line;
			    }
			    
			    String[] minMax = last.split(" ");
			    
			    BigInteger minFile = new BigInteger(minMax[0]);
				BigInteger maxFile = new BigInteger(minMax[1]);
				
			    
				
				BigInteger interval = new BigInteger("8388608"); //65536*128
				//long max = interval;
				AES.tryCorrectKey();
				//AES.getKey(min,max,interval);
				
			
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
		
		 
	}

}
