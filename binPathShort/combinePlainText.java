package binPathShort;

import java.math.BigInteger;
import java.util.Arrays;

public class combinePlainText {

	public static BigInteger min;
	 public static BigInteger max;
	 public static String interval = "279841"; // (( 23 ^4))

	public static boolean foundKey = false;
	 public static byte[] solved = {'a','b','c'};
	 
	 
	 public static int idx;
	 
	 public static byte[] abc = { 0x61,0x61,0x61,0x61,0x61}; //0x61,0x61,0x61,0x61,0x61,0x61};
	
	 public static void combinationRange() {

		 while (min.compareTo(max) < 0) {
			 combinePlainText.add();
			 min = min.add(BigInteger.ONE);
		 }
	 }
			 public static void main(String[] args) {
				 testCombinations();
			 }
		 public static void testCombinations() {
			 min = new BigInteger("0");
			 max = new BigInteger(interval);
				BigInteger bigInterval = new BigInteger(interval);
				for (int it=0; it < 100 && foundKey==false; it++) {
					 
					 System.out.println("Min "+min.toString(10)+" Max "+max.toString(10));
				
					 combinationRange();
					 
					 min=max;
					 max =max.add(bigInterval);
				}
		}
	 
	public static void add() {
		 if (idx > abc.length-1) {
			 try {
				throw new IndexLargerThanArrayLengthException();
			} catch (IndexLargerThanArrayLengthException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		   // 0x7E is last printable character
		   if (abc[idx]==0x7E) {
			   idx++;
			   try {
					throw new IndexLargerThanArrayLengthException();
				} catch (IndexLargerThanArrayLengthException e) {
					// TODO Auto-generated catch block
					System.out.println("\nFinished testing. (number of sequences checked) "+idx);
				}
			   
			   // first printable character
			   abc[idx]=0x20;

		   }
		   else
			   abc[idx]++;
		   util.printArray("add", abc);

	   }
}
