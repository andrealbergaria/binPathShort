package binPathShort;

import javax.crypto.spec.SecretKeySpec;


public class SecretKeyValues extends SecretKeySpec {
		private byte[] key;
		private String algorithm;

	public SecretKeyValues(byte[] key, String algorithm) {
		super(key,algorithm);
       
        
		
		
        if (algorithm.equals("AES")) { 
        	if (key.length != 16 && key.length != 32 && key.length != 24 ) {
        		throw new IllegalArgumentException("AES keys must be 16,24 or 32 bytes");
        		
        	}
        }
        this.key = new byte[key.length];
        
        for (int i=0; i < key.length ; i++)
        	this.key[i] = (byte) (key[i] & 0xFF);
        
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
