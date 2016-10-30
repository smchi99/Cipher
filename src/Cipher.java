import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Cipher {
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%-_;?-=:"
			+ '\n' + '\r';
	private static final String SIMPLE_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	// Set this variable to the default alphabet you wish to use
	private static final String DEFAULT_ALPHABET = ALPHABET;

	private static Dictionary dictionary = Dictionary.buildDictionary("words.txt");

	public static int shiftIndex(int index, int alphabetLength, int cipherLength) {
		while (index < 0) {
			index += alphabetLength;
		}
		if (index > cipherLength - 1) {
			index = index % alphabetLength;
		}
		return index;
	}

	/**
	 * Returns plaintext encrypted by the rotation cipher with a shift of
	 * movement.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param plain
	 *            the plain text to be encrypted.
	 * @param shift
	 *            the number of characters in ALPHABET to shift by.
	 * @return returns the encrypted plainText.
	 */

	public static String rotationCipherEncrypt(String plain, int shift, String alphabet) {
		String letter = "";
		String cipher = "";
		for (int i = 0; i < plain.length(); i++) {
			int index = 0;
			letter = plain.substring(i, i + 1);
			index = alphabet.indexOf(letter) + shift;
			index = shiftIndex(index, alphabet.length(), plain.length());
			cipher = cipher + alphabet.substring(index, index + 1);

		}
		return cipher;
	}

	public static String rotationCipherEncrypt(String plainText, int shiftAmount) {
		return rotationCipherEncrypt(plainText, shiftAmount, DEFAULT_ALPHABET);
	}

	/**
	 * Returns a the result of decrypting cipherText by shiftAmount using the
	 * rotation cipher.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param cipher
	 *            the encrypted text.
	 * @param shift
	 *            the key to decrypt the cipher.
	 * @return returns the decrypted cipherText.
	 */

	public static String rotationCipherDecrypt(String cipher, int shift, String alphabet) {
		String letter = "";
		String decode = "";
		for (int i = 0; i < cipher.length(); i++) {
			int index = 0;
			letter = cipher.substring(i, i + 1);
			index = alphabet.indexOf(letter) - shift;
			index = shiftIndex(index, alphabet.length(), cipher.length());
			decode = decode + alphabet.substring(index, index + 1);

		}
		return decode;
	}

	public static String rotationCipherDecrypt(String cipherText, int shiftAmount) {
		return rotationCipherDecrypt(cipherText, shiftAmount, DEFAULT_ALPHABET);
	}

	/**
	 * Returns plaintext encrypted by the vigenere cipher encoded with the
	 * String code
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param plain
	 *            the plain text to be encrypted.
	 * @param password
	 *            the code to use as the encryption key.
	 * @return returns the encrypted plainText.
	 */
	public static String vigenereCipherEncrypt(String plain, String password, String alphabet) {
		String encryptCode = "";
		int[] codeArr = new int[password.length()];
		int codeIndex = 0;
		for (int i = 0; i < codeArr.length; i++) {
			codeArr[i] = alphabet.indexOf(password.substring(i, i + 1));
		}
		for (int i = 0; i < plain.length(); i++) {
			if (codeIndex == password.length() - 1) {
				codeIndex = 0;
			}
			encryptCode += rotationCipherEncrypt(plain.substring(i, i + 1), codeArr[codeIndex]);
			codeIndex++;
		}
		return encryptCode;
	}

	public static String vigenereCipherEncrypt(String plainText, String code) {
		return vigenereCipherEncrypt(plainText, code, DEFAULT_ALPHABET);
	}

	/**
	 * Returns the result of decrypting cipherText with the key code.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param cipher
	 *            the encrypted text.
	 * @param password
	 *            the decryption key
	 * @return returns the decrypted cipherText.
	 */
	public static String vigenereCipherDecrypt(String cipher, String password, String alphabet) {
		String encryptCode = "";
		int[] codeArr = new int[password.length()];
		int codeIndex = 0;
		for (int i = 0; i < codeArr.length; i++) {
			codeArr[i] = (alphabet.indexOf(password.substring(i, i + 1))) * (-1);
		}
		for (int i = 0; i < cipher.length(); i++) {
			if (codeIndex == password.length() - 1) {
				codeIndex = 0;
			}
			encryptCode += rotationCipherEncrypt(cipher.substring(i, i + 1), codeArr[codeIndex]);
			codeIndex++;
		}
		return encryptCode;
	}

	public static String vigenereCipherDecrypt(String cipherText, String code) {
		return vigenereCipherDecrypt(cipherText, code, DEFAULT_ALPHABET);
	}

	/***
	 * returns the result of the substituted cipher
	 * 
	 * @param plaintext
	 *            String that will be substituted
	 * @param permutation
	 *            array that contains which letters in the alphabet will be
	 *            replaced with which index of the alphabet
	 * @param alphabet
	 *            String of alphabets to help with declaring what index to
	 *            substitute each letter in the alphabet with
	 * @return returns substituted cipher
	 */
	public String substitutionCipher(String plaintext, int[] permutation, String alphabet) {
		String letter = "";
		String cipher = "";
		for (int i = 0; i < plaintext.length(); i++) {
			int index = 0;
			letter = plaintext.substring(i, i + 1);
			index = alphabet.indexOf(letter);
			letter = alphabet.substring(permutation[index], permutation[index] + 1);
			cipher = cipher + letter;
		}
		return cipher;
	}

	/***
	 * returns boolean whether permutation array is valid
	 * 
	 * @param permutation
	 *            array with each letter of the alphabet permutated
	 * @return returns whether or not there is a valid permutation or not
	 */
	public static boolean ValidPermutation(int[] permutation) {
		for (int i = 0; i < permutation.length; i++) {
			for (int n = i + 1; n < permutation.length; n++) {
				if (permutation[i] == permutation[n])
					return false;
			}
		}
		return true;
	}

	/***
	 * returns an array filled with random indexes in the parameter of the
	 * length as a possible permutation
	 * 
	 * @param length
	 *            int that indicates how long the array will be
	 * @return returns randomly filled permutation array
	 */
	public static int[] randomPermutation(int length) {
		int[] permutation = new int[length];
		for (int i = 0; i < length; i++) {
			permutation[i] = (int) (Math.random() * DEFAULT_ALPHABET.length());
		}
		return permutation;
	}
	
	public static String rotationCipherCrack(String cipher, String alphabet) {
		String decrypt = "";

		for (int i = 0; i < 100000; i++) {
			decrypt = Cipher.rotationCipherDecrypt(cipher, i);
			if (Cipher.isEnglish(decrypt) == true)
				return decrypt;
		}
		return "";

	}
	
	public static String getLetterGroups(String cipher, int startIndex, int length) {
		String letterGroup = "";
		for (int i = startIndex; i < cipher.length(); i = i + length) {
			letterGroup += cipher.substring(i, i + 1);
		}
		return letterGroup;
	}
	
	public static String getLetterCode(String cipher, int startIndex, int length, String alphabet) {
		letterBag bag = new letterBag();
		String message = getLetterGroups(cipher, startIndex, length);
		for (int i = 0; i < alphabet.length(); i++) {
			message = Cipher.rotationCipherDecrypt(message, i);
			for (int n = 0; n < message.length(); n++) {
				bag.add(message.substring(n, n + 1));
			}
			if (letterBag.getMostFrequent() == " ") {
				return alphabet.substring(i, i + 1);
			}

		}
		return "";
	}
	
	public static String vigenereCipherCrackThreeLetter(String cipher, String alphabet){
		String letterCode = "";
		for(int i = 0; i < 3; i++){
			letterCode += getLetterCode(cipher, i, 3, alphabet);
		}
		return letterCode;
		
	}
	
	public static String vigenereCipherCrack(String cipher, int passwordLength, String alphabet){
		String letterCode = "";
		for(int i = 0; i < passwordLength; i++){
			letterCode += getLetterCode(cipher, i, passwordLength, alphabet);
		}
		return letterCode;
	}

	/**
	 * returns a copy of the input plaintext String with invalid characters
	 * stripped out.
	 * 
	 * @param plaintext
	 *            The plaintext string you wish to remove illegal characters
	 *            from
	 * @param alphabet
	 *            A string of all legal characters.
	 * @return String A copy of plain with all characters not in alphabet
	 *         removed.
	 */
	private static String stripInvalidChars(String plaintext, String alphabet) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < plaintext.length(); i++) { // loop through plaintext
			if (alphabet.indexOf(plaintext.charAt(i)) >= 0) // get index of char
				b.append(plaintext.charAt(i)); // if it exists, keep it
			else
				// otherwise skip it &
				System.out.println("Stripping letter: \"" + plaintext.charAt(i) // display
																				// a
																				// message
						+ "\"");
		}
		return b.toString();
	}

	public static int wordCount(String message) {
		message += " ";
		int wordCount = 0;
		int currentSpaceIndex = message.indexOf(" ");
		int prevSpace = 0;
		while (currentSpaceIndex != -1) {
			String word = message.substring(prevSpace + 1, currentSpaceIndex);
			if (!word.equals(" ")) {
				wordCount++;
			}
			prevSpace = currentSpaceIndex;
			currentSpaceIndex = message.indexOf(" ", prevSpace + 1);
		}
		return wordCount;
	}

	public static String[] getWords(String message) {
		message += " ";
		String[] output = new String[wordCount(message) - 1];
		int currentSpaceIndex = message.indexOf(" ");
		int prevSpace = 0;
		int nextFree = 0;
		while (currentSpaceIndex != -1) {
			String word = message.substring(prevSpace, currentSpaceIndex);
			if (!word.equals(" ")) {
				output[nextFree] = word.trim();
				nextFree++;
			}
			prevSpace = currentSpaceIndex;
			currentSpaceIndex = message.indexOf(" ", prevSpace + 1);
		}
		return output;
	}

	/**
	 * returns true if plaintext is valid English.
	 * 
	 * @param plaintext
	 *            the text you wish to test for whether it's valid English
	 * @return boolean returns true if plaintext is valid English.
	 */
	public static boolean isEnglish(String plaintext) {
		// Break plaintext into an array of words
		// Loop over all the words and use dictionary to see if they're English
		String[] words = getWords(plaintext);
		int wordCount = 0;

		for (int i = 0; i < words.length; i++) {
			if (dictionary.isWord(words[i]) == true) {
				wordCount++;
			}
		}
		if (((double) (wordCount)) / words.length >= 0.6) {
			return true;
		} else {
			return false;
		}
		// letterBag bag = new letterBag();
		// for (int i = 0; i < plaintext.length(); i++) {
		// bag.add(plaintext.substring(i, i + 1));
		// if (letterBag.getMostFrequent() == " ") {
		// return true;
		// }
		// }return false;

	}

}