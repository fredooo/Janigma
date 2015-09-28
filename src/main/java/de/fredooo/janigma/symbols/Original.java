package de.fredooo.janigma.symbols;

import java.util.Arrays;

/**
 * This class transforms the 0-25 number representation into actual 
 * characters and supports lower and upper case letters.
 * @author Frederik Dennig
 * @since 2011-06-09
 * @version 0.0.3 (last revised 2015-09-23)
 */
public final class Original {

	public static final char[] ORIGINAL =
		{'A','B','C','D','E','F','G','H','I','J','K','L','M',
			'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

	public static final char[] ORIGINAL_LOWER_CASE =
		{'a','b','c','d','e','f','g','h','i','j','k','l','m',
			'n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	/**
	 * Transforms an int between 0 and 25 to the corresponding character. 
	 * @param number An int between 0 and 25.
	 * @return The resulting character.
	 */
	public static char toChar(int number) {
		return ORIGINAL[number];
	}
	
	/**
	 * Transforms a simple character (a-z, A-Z) to an int from 0 to 25.
	 * @param character A given character (a-z, A-Z).
	 * @return The resulting int (0-25).
	 * @throws NoSuchSymbolException
	 */
	public static int toInt(char character) throws NoSuchSymbolException {
		int i = Arrays.binarySearch(ORIGINAL, character);
		if (i >= 0) { return i; }
		i = Arrays.binarySearch(ORIGINAL_LOWER_CASE, character);
		if (i >= 0) { return i; }
		throw new NoSuchSymbolException(character);
	}
	
	/**
	 * Checks if a character is valid for translation.
	 * @param character The character, you want to validate.
	 * @return The result as boolean.
	 */
	public static boolean isValidChar(char character) {
		int i = Arrays.binarySearch(ORIGINAL, character);
		if (i >= 0) { return true; }
		i = Arrays.binarySearch(ORIGINAL_LOWER_CASE, character);
		if (i >= 0) { return true; }
		return false;
	}
	
	/**
	 * Checks if a whole string is valid for translation.
	 * @param string The string, you want to validate.
	 * @return The result as boolean.
	 */
	public static boolean isValidString(String string) {
		for(int i = 0; i < string.length(); i++) {
			if(Original.isValidChar(string.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}
	
}
	
