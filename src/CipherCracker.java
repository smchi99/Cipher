
public class CipherCracker {
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%-_;?-=:"
			+ '\n' + '\r';
	private static final String SIMPLE_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private final static String alphabet = "abcdefghijklmnopqrstuvwxyz";

	// Set this variable to the default alphabet you wish to use
	private static final String DEFAULT_ALPHABET = ALPHABET;

	public static String crackRotationCipher(String cipherText) {
		String decrypt = "";

		for (int i = 0; i < 100000; i++) {
			decrypt = Cipher.rotationCipherDecrypt(cipherText, i);
			if (Cipher.isEnglish(decrypt) == true)
				return decrypt;
		}
		return "";

	}

	public static String crackVigenereByFreq(String cipher, int length) {
		String letterCode = "";
		String message = "";
		for (int i = 0; i < length; i++) {
			letterCode += getLetterCode(cipher, i, length);
		}
		message = Cipher.vigenereCipherDecrypt(cipher, letterCode);
		if (Cipher.isEnglish(message) == true) {
			return message;
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

	public static String getLetterCode(String cipher, int startIndex, int length) {
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

}
