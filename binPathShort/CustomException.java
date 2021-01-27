package binPathShort;

public class CustomException extends Exception{

	/**
	 * @param args
	 */
	public CustomException(Object obj) {
		if (obj instanceof BadPaddingException)
		printStackTrace();
		
	}
        

}

