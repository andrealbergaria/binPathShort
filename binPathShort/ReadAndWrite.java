package binPathShort;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class ReadAndWrite extends util {

	public static void writeFile(ArrayList somePlainTexts) {
		try {
			
			String dateS="";
			
			
			FileWriter fw = new FileWriter(AES.plainTextsPath);
		//	ByteArrayOutputStream fw = new ByteArrayOutputStream(AES.plainTextsPath); 

			// begin hhdr -> date -> AES.min -> AES.max -> plaintexts 
			// since AES.min is not known, how does java stores the data? namely,
			//separators
			dateS=getDate()+"\n";
			fw.write(dateS);
			fw.write("Min "+AES.min.toString()+" Max "+AES.max.toString()+"\n");
			
			
		for (int i=0; i < somePlainTexts.size() ; i++) {
			String s = somePlainTexts.get(i);
			char[] cArr = (char []) somePlainTexts.get(i);
			fw.write(somePlainTexts.get(i));
			fw.write(cArr);
			fw.write('\n');
		}
			
		
		
		
		System.out.println("[util.writeFile] Wrote plainTexts files");
		fw.close();
		
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
