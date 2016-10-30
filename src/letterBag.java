
public class letterBag {
	private final static String alphabet = "abcdefghijklmnopqrstuvwxyz";
	private static int[] letterFrequencies;

	public letterBag() {
		letterFrequencies = new int[26];
	}

	/***
	 * adds letters to the bag
	 * 
	 * @param letter
	 *            String that indicates what letter you want to put in the bag
	 */
	public void add(String letter) {
		String lower = letter.toLowerCase();
		int index = getIndexForLetter(lower);
		letterFrequencies[index]++;
	}

	/***
	 * gets the index of the letter in the alphabet
	 * 
	 * @param lower
	 *            the alphabet letter that you would like to know the index of
	 * @return returns the index of that certain letter
	 */
	private int getIndexForLetter(String lower) {
		return alphabet.indexOf(lower);
	}

	/***
	 * lets you know how many words are in the bag
	 * 
	 * @return returns total amount of words in the bag
	 */
	public static int getTotalWords() {
		int wordCount = 0;
		for (int i = 0; i < letterFrequencies.length; i++) {
			wordCount += letterFrequencies[i];
		}
		return wordCount;
	}

	/***
	 * lets you know how many different letters there are in the bag
	 * 
	 * @return returns total amount of different letters in the bag
	 */
	public static int getNumUniqueWords() {
		int uniqueWordCount = 0;
		for (int i = 0; i < letterFrequencies.length; i++) {
			if (letterFrequencies[i] != 0) {
				uniqueWordCount++;
			}
		}
		return uniqueWordCount;
	}

	/***
	 * lets you know how many times a certain letter appears in the bag
	 * 
	 * @param letter
	 *            indicates the letter that you would like know occurs how many
	 *            times
	 * @return returns the number of times that letter occurs
	 */
	public static int getNumOccurances(String letter) {
		int index = alphabet.indexOf(letter);
		return letterFrequencies[index];
	}

	/***
	 * tells you which letter occurs the most
	 * 
	 * @return returns the letter that appears the most
	 */
	public static String getMostFrequent() {
		int letterFrequency = 0;
		String mostFrequentLetter = "";
		for (int i = 0; i < letterFrequencies.length; i++) {
			if (letterFrequencies[i] > letterFrequency) {
				letterFrequency = letterFrequencies[i];
				mostFrequentLetter = alphabet.substring(i, i + 1);
			}
		}
		return mostFrequentLetter;
	}

	/***
	 * lets you know a certain number of most appearing letters
	 * 
	 * @param num
	 *            the number of letters you would like to see appear the most
	 *            frequently
	 * @return returns an array that starts with the most frequent letter and
	 *         will tell you in decreasing order the letters that next appear
	 *         the most frequent the num times you tell it to
	 */
	public static String[] getNMostFrequentStrings(int num) {
		String[] frequency = new String[num];
		int index = 1;
		int mostFrequent = letterFrequencies[alphabet.indexOf(getMostFrequent())];
		int nextMostFrequent = 0;
		String letter = "";
		frequency[0] = getMostFrequent();
		while (index < num) {
			for (int i = 0; i < letterFrequencies.length; i++) {
				if (letterFrequencies[i] < mostFrequent && letterFrequencies[i] > nextMostFrequent) {
					nextMostFrequent = letterFrequencies[i];
					letter = alphabet.substring(i, i + 1);
				}
			}
			mostFrequent = nextMostFrequent;
			nextMostFrequent = 0;
			frequency[index] = letter;
			index++;
		}

		return frequency;
	}

}
