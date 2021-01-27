package binPathShort;

import java.security.InvalidKeyException;


public class WrongKeyException extends InvalidKeyException {

	/**
	 * 
	 */

	public WrongKeyException(byte[] key) {
		System.out.println("Key "+key+"INVALID!");
	}

}
