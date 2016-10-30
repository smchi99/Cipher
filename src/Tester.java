import java.util.Arrays;

import fileIO.fileIO;

public class Tester {

	public static void main(String[] args) {
		Dictionary dictionary = Dictionary.buildDictionary("E:/java 2016/CipherBlankTemplate/words.txt");
//		String plainText = "Nima's pants are on fire!!!?!!";
//		String[] c = Cipher.getWords(plainText);
//		
//		String cipherText = Cipher.rotationCipherEncrypt(plainText, 3);
//		String a = Cipher.rotationCipherDecrypt(plainText, 3);
//		String vigenerePlainText = "help i am under attack";
//		String vigenereCipherTextEncrypt = Cipher.vigenereCipherEncrypt(vigenerePlainText, "code",
//				"abcdefghijklmnopqrstuvwxyz");
//		String vigenereCipherTextDecrypt = Cipher.vigenereCipherDecrypt(vigenereCipherTextEncrypt, "code");
//		System.out.println("Plaintext: " + plainText);
//		System.out.println("Cipertext: " + cipherText);
//		System.out.println(plainText.substring(0));
//		System.out.println("Encrypt: " + vigenereCipherTextEncrypt);
//		System.out.println("Decrypt: " + vigenereCipherTextDecrypt);
//		System.out.println(Arrays.toString(Cipher.getWords(plainText)));
//		System.out.println(Cipher.wordCount("a"));
//		System.out.println(Cipher.isEnglish("there hi"));
//		System.out.println(Arrays.toString(Cipher.getWords("a")));
		String vigenerePlainText = "help i am under attack";
		String vigenereCipherTextEncrypt = Cipher.vigenereCipherEncrypt(vigenerePlainText, "code",
				"abcdefghijklmnopqrstuvwxyz");
		String vigenereCipherTextDecrypt = Cipher.vigenereCipherDecrypt(vigenereCipherTextEncrypt, "code");
		System.out.println(vigenereCipherTextEncrypt);
		System.out.println(CipherCracker.crackVigenereByFreq(vigenereCipherTextEncrypt, 3));
		
		
	
		//		System.out.println(CipherCracker.crackRotationCipher("Qlpd[v!sdqwv!duh!rq!iluh///://"));
		
		
//		String message = CipherCracker.crackRotationCipher("E:/java 2016/CipherBlankTemplate/cipher1.txt");
//		fileIO.readFileAsString("E:/java 2016/file.txt", message);
		
		
		
	
	

	}

}