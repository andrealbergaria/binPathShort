package binPathShort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import javax.crypto.spec.SecretKeySpec;

public class binPathShort {

	public static BigInteger min;
	public static BigInteger max;
	public static BigInteger interval = new BigInteger("2097152"); //65536*32
	
	
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
			    
				AES.correctKey[0] =0x61;
				AES.correctKey[1] = 0x61;
				
				//long max = interval;
				//AES.tryCorrectKey();
				AES.cycle(min,max,interval);
	}

}
