package binPathShort;

import javax.crypto.spec.SecretKeySpec;


public class SecretKeyValues extends SecretKeySpec {
		private byte[] key;
		private String algorithm;

	public SecretKeyValues(byte[] key, String algorithm) {
		super(key,algorithm);
       
        this.algorithm=algorithm;
		
		
        if (algorithm.equals("AES")) { 
        	if (key.length != 16 && key.length != 32 && key.length != 24 ) {
        		throw new IllegalArgumentException("AES keys must be 16,24 or 32 bytes");
        		
        	}
        }
        this.key = new byte[key.length];
        
        for (int i=0; i < key.length ; i++)
        	this.key[i] = (byte) (key[i] & 0xFF);
        
    }
	
	public String detailedString() {
		String r1 = "key algorithm = "+algorithm+"\n";
		String r2 = "key length "+key.length+"\n[";
		String r3 = new String();
		byte[] kArray = this.getEncoded();
		
		for (int i=0; i < kArray.length ; i++) {
			r3+=" "+key[i]+",";
		}
		String r4 = "]";
		String r= r1+r2+r3+r4;
		return r;
	}
	
	public String toString() {
		String retValue = new String();
		byte[] kArray = this.getEncoded();
		retValue = "[SecretKeyValues.toString()] size : "+kArray.length;
		retValue += "[ ";
		
		
		
		for (int i=0; i < kArray.length; i++) {
			
			if (i != kArray.length-1)
			retValue += kArray[i] + ",";
			else
			retValue += kArray[i];
		}
		retValue +=" ]\n";
		retValue += "[SecretKeyValues.toString()] end";
		return retValue;
	}
	 /**
     * Returns the key material of this secret key.
     *
     * @return the key material. Returns a new array
     * each time this method is called.
     */
    public byte[] getEncoded() {
        return this.key.clone();
    }


}
