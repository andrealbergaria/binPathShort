package binPathShort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

import javax.crypto.spec.SecretKeySpec;

public class binPathShort {

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
				File f = new File("/home/andrec/workspace_3_8/binPathShort/minmax");
				if (!f.exists() || f.length() ==0) {
					f.createNewFile();
				}
				BufferedReader br = new BufferedReader(new FileReader("/home/andrec/workspace_3_8/binPathShort/minmax"));
				String minmax = br.readLine();
				br.close();
				if (minmax == null) {
					System.out.println("\n[binPahtShort.java main()] ficheiro min max nao existe");
				}
				/*String minMaxArgs[]  = minmax.split(" ");
				String interval = String.valueOf(minMaxArgs[0]);
				BigInteger min = new BigInteger(minMaxArgs[1]);
				BigInteger max = new BigInteger(minMaxArgs[2]);
				*/
				
				
				
				
				long interval = 8388608; //65536*128
				long min = 0;
				long max = interval;
				AES.tryCorrectKey();
				//AES.getKey(min,max,interval);
				
			
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
		
		 
	}

}
