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

	public static BigInteger[] minMaxValues = new BigInteger[2];
	
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
			
			
				util.readLog();
			    
				
				BigInteger interval = new BigInteger("8388608"); //65536*128
				//long max = interval;
				AES.tryCorrectKey();
				//AES.cycle(min,max,interval);
	}

}
