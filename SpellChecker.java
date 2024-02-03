
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
	// Convert the words to lower case	
	word1 = word1.toLowerCase();
	word2 = word2.toLowerCase();
    // Base case: If either of the words is empty, return the length of the other word
	if (word1.isEmpty()) {
		return word2.length();
	}
	if (word2.isEmpty()) {
		return word1.length();
	}
	// checks the first character of the two words
	char char1 = word1.charAt(0);
	char char2 = word2.charAt(0);
	if (char1 == char2){
		return levenshtein(word1.substring(1), word2.substring(1));
	}
	// If both two chars are not equal -> calculates acorrding to the given opertions 
	int insert = levenshtein(word1, tail(word2));
	int delete = levenshtein(tail(word1), word2);
	int substitute = levenshtein(tail(word1), tail(word2));
	return (1 + myMin(myMin(insert, substitute), delete)); // calculate the min and adds 1
}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i = 0; i < 3000 ; i++){
			String word = in.readString();
			dictionary[i] = word; 
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String closestWord = word;
		int minDistance = threshold;
		for (int i = 0; i < dictionary.length; i++){
			String dicWord = dictionary[i];
			int distance = levenshtein(word, dicWord); 
			if (distance < minDistance){
				minDistance = distance;
				closestWord = dicWord;
			}
		}
			if (minDistance <= threshold) {
				return closestWord;
			} else if (minDistance == 0) {
				return word;
			} else {
				return closestWord;
		}
		}

	public static int myMin(int a, int b) { // a helper funcion, returns the minimum int
		if (a < b) {
			return a;
		} else {
			return b;
		}
	}
}
