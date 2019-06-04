package com.revature.eval.java.core;

import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		String[] arr = string.split("(?!^)");
		String output = "";
		for (int i = arr.length - 1; i >= 0; i--) {
			output += arr[i];
		}
		return output;
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		String[] acr = phrase.split("[-\\s]");
		String output = "";

		for (int i = 0; i < acr.length; i++) {
			char firstChar = acr[i].charAt(0);
			output += firstChar;
		}
		return output.toUpperCase();
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			if (getSideOne() == getSideTwo() && (getSideOne() == getSideThree())) {
				return true;
			}
			return false;
		}

		public boolean isIsosceles() {
			if (getSideOne() == getSideTwo() || getSideOne() == getSideThree() || getSideTwo() == getSideThree())
				return true;
			return false;
		}

		public boolean isScalene() {
			if (getSideOne() != getSideTwo() && getSideOne() != getSideThree() && getSideTwo() != getSideThree())
				return true;
			return false;
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		String[] input = string.toUpperCase().split("");
		int count = 0;
		Set<String> onePoint = new HashSet<String>(Arrays.asList("A", "E", "I", "O", "U", "L", "N", "R", "S", "T"));
		Set<String> threePoints = new HashSet<String>(Arrays.asList("B", "C", "M", "P"));
		Set<String> fourPoints = new HashSet<String>(Arrays.asList("F", "H", "V", "W", "Y"));

		for (int i = 0; i < input.length; i++) {
			if (onePoint.contains(input[i]))
				count += 1;
			else if (threePoints.contains(input[i]))
				count += 3;
			else if (fourPoints.contains(input[i]))
				count += 4;
			else if ("G".equals(input[i]))
				count += 2;
			else if ("K".equals(input[i]))
				count += 5;
			else if ("J".equals(input[i]) || "X".equals(input[i]))
				count += 8;
			else if ("Q".equals(input[i]) || "Z".equals(input[i]))
				count += 10;
		}
		return count;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		string = string.replaceAll("[()\\s.-]", "");
		if (!string.matches("^[0-9]+$")) {
			throw new IllegalArgumentException("Phone numbers need to be numeric");
		} else if (string.length() > 11)
			throw new IllegalArgumentException("Phone numbers can't be longer than 11");
		return string;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		Map<String, Integer> output = new HashMap<String, Integer>();
		String[] input = string.split("[-\\s,]+");

		for (int i = 0; i < input.length; i++) {
			String key = input[i];
			// never see this character, let's add to the pool to count
			if (!output.containsKey(key)) {
				output.put(key, 1);
			} else { // we already saw this character and it's in the pool for counting
				output.put(key, output.get(key) + 1);
			}
		}
		return output;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;

		public int indexOf(T t) {
			int low = 0;
			int high = sortedList.size() - 1;
			int index = Integer.MAX_VALUE;

			while (low <= high) {
				int mid = (low + high) / 2;
				if (t.equals(sortedList.get(mid))) {
					index = sortedList.indexOf(t);
					break;
				}
				// value is not at the middle
				if (sortedList.indexOf(t) < mid) {
					high = mid - 1;
				} else if (sortedList.indexOf(t) > mid) {
					low = mid + 1;
				}
			}
			return index;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		int total = 0;
		String str = String.valueOf(input);
		String[] inputArr = str.split("");

		for (int i = 0; i < inputArr.length; i++) {
			total += Math.pow(Double.parseDouble(inputArr[i]), inputArr.length);
		}

		if (total == input)
			return true;
		return false;
	}

	/**
	 * 9. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		if (string == null)
			return false;
		string = string.toUpperCase();
		Set<String> alphabet = new HashSet<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
				"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));
		string = string.replaceAll("\\s", "");
		String[] inputArr = string.split("");

		for (int i = 0; i < inputArr.length; i++) {
			if (alphabet.contains(inputArr[i])) {
				alphabet.remove(inputArr[i]);
			}
		}
		if (alphabet.isEmpty())
			return true;
		return false;
	}

	/**
	 * 10. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			StringBuilder sb = new StringBuilder();
			String[] inputString = string.split("");
			int shiftAmmount = 0;
			for (int i = 0; i < inputString.length; i++) {
				char c = inputString[i].charAt(0);
				if ((int) c >= 65 && (int) c <= 90) { // uppercase
					shiftAmmount = c + key;
					if (shiftAmmount > 90) {
						shiftAmmount = shiftAmmount - 90;
						sb.append((char) ('A' + shiftAmmount - 1));
					} else {
						sb.append((char) shiftAmmount);
					}
				} else if ((int) c >= 97 && (int) c <= 122) { // lowercase
					shiftAmmount = c + key;
					if (shiftAmmount > 122) {
						shiftAmmount = shiftAmmount - 122;
						sb.append((char) ('a' + shiftAmmount - 1));
					} else {
						sb.append((char) shiftAmmount);
					}

				} else {
					sb.append(c);
				}

			}
			return sb.toString();
		}
	}

	/**
	 * 11 & 12. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {
		// map for encoding and decoding, contains mapping of characters
		private static HashMap<Character, Character> map() {
			HashMap<Character, Character> map = new HashMap<>();
			char c = 'a';

			for (char x = 'z'; x >= 'a'; x--) {
				map.put(c++, x);
			}
			return map;
		}

		private static boolean isInteger(char c) {
			try {
				Integer.parseInt(Character.toString(c));
				return true;
			} catch (NumberFormatException nfe) {
				return false;
			}
		}

		/**
		 * Question 11
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			string = string.toLowerCase().replaceAll("[\\s,.+]", "");
			String output = "";
			int countChar = 0;

			for (int i = 0; i < string.length(); i++) {
				char c = string.charAt(i);

				if (countChar != 0 && countChar % 5 == 0) {
					output += " ";
					countChar = 0;
				}
				if (isInteger(c)) {
					output += c;
					countChar++;
				} else if (map().containsKey(c)) {
					output += Character.toString(map().get(c));
					countChar++;
				}
			}
			return output;
		}

		/**
		 * Question 12
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			string = string.replaceAll("[\\s]", "");
			String output = "";

			for (int i = 0; i < string.length(); i++) {
				char c = string.charAt(i);
				if (isInteger(c))
					output += c;
				else if (map().containsKey(c)) {
					output += Character.toString(map().get(c));
				}

			}
			return output;
		}
	}

	/**
	 * 13. (Optional) The ISBN-10 verification process is used to validate book
	 * identification numbers. These normally contain dashes and look like:
	 * 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		string = string.replaceAll("-", "");
		if (string.length() > 10)
			return false;
		if (!Character.toString(string.charAt(string.length() - 1)).matches("^[0-9]+$")
				&& string.charAt(string.length() - 1) != 'X')
			return false;
		int count = 10;
		int total = 0;
		for (int i = 0; i < string.length() - 1; i++) {
			total += Character.getNumericValue(string.charAt(i)) * count;
			count--;
		}
		if ((string.charAt(string.length() - 1)) == 'X')
			total += 10;
		else
			total += Character.getNumericValue(string.charAt(string.length() - 1));
		if (total % 11 == 0)
			return true;
		return false;
	}

	/**
	 * 14. (Optional) Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		return null;
	}

	/**
	 * 15. (Optional) Parse and evaluate simple math word problems returning the
	 * answer as an integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		// TODO Write an implementation for this method declaration
		return 0;
	}

}
