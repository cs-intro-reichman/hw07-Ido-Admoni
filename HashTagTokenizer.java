public class HashTagTokenizer {

	public static void main(String[] args) {
		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
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


	public static boolean existInDictionary(String word, String []dictionary) {
		for (int i = 0; i < 3000; i++){
			if (areEqual(word, dictionary[i])){
				return true; 
			}
		}
		return false;
		}

	public static void breakHashTag(String hashtag, String[] dictionary) {
		hashtag = hashtag.toLowerCase(); 
		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
        int N = hashtag.length();
        for (int i = 1; i <= N; i++) {
			String prefix = hashtag.substring(0,i);
			if (existInDictionary(prefix, dictionary)){
				System.out.println(prefix);
				breakHashTag(hashtag.substring(i), dictionary);
				// stop further processing after finding a valid prefix
				return;
		}
        }
    }

	public static boolean areEqual(String s1, String s2) { // helper,checks if two givven strings are equal.
		if (s1.length() != s2.length()) {
			return false;
		}
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				return false;
			}
		}
		return true;
	}

}