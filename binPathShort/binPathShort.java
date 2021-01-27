package binPathShort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class binPathShort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("\nPossible Combinations ");
		System.out.println("\nByte "+Byte.MAX_VALUE*2);
		System.out.println("Character " +Character.MAX_VALUE*2);
		System.out.println("Short " +Short.MAX_VALUE*2);
		System.out.println("Integer "+(long)Integer.MAX_VALUE*2);
		System.out.println("Long  "+Long.MAX_VALUE*2);
		
		
			
			try {
				
				BufferedReader br = new BufferedReader(new FileReader("/home/andrec/workspace_3_8/binPathShort/minmax"));
				String minmax = br.readLine();
				br.close();
				if (minmax == null) {
					System.out.println("\nProblems on reading from file ");
					System.exit(-1);
				}
				/*String minMaxArgs[]  = minmax.split(" ");
				String interval = String.valueOf(minMaxArgs[0]);
				BigInteger min = new BigInteger(minMaxArgs[1]);
				BigInteger max = new BigInteger(minMaxArgs[2]);
				*/
				
				
				String interval = "8388608"; //65536*128
				BigInteger min = new BigInteger("0");
				BigInteger max = new BigInteger(interval);
				AES.getKey(interval,min,max);
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
		
		 
	}

}
