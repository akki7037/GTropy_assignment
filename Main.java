
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Dictionary d = new Dictionary();
        d.addPredefinedWords("D:\\Gtropy Assignment\\list.txt"); // TC-> O(num of words in dictionary * max. length of word), SC-> O(num of words in dictionary * max. length of word)
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the word to search:");
        String input = sc.next();
        sc.close();
        
        d.searchWord(input); 
    }
}

class Dictionary {
    private Map<String, Boolean> words = new HashMap<>();

    // add words from a file into the dictionary
    public void addPredefinedWords(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.put(line.trim(), true); // Trim leading/trailing whitespace and add to dictionary
            }
        }
    }

    // Search for a word in the dictionary and suggest similar words if not found
    // TC-> O(num of words in dictionary * max. length of word) , SC-> constant
    public void searchWord(String word) {
        if (words.containsKey(word)) {
            System.out.println(word + " : exists in dictionary."); // TC-> O(num of words in dictionary) , SC-> constant
        } else {
            List<String> suggested = suggestedWords(word); 
            if (!suggested.isEmpty()) {
                System.out.println("Did you mean:");
                for (String s : suggested) {
                    System.out.println(s);
                }
            } else {
                System.out.println(word + " : doesn't exist in dictionary.");
            }
        }
    }

    // Suggest similar words for a misspelled input
   // TC-> O(num of words in dictionary * max. length of word) , SC-> constant
    private List<String> suggestedWords(String word) {
        List<String> suggested = new ArrayList<>();
        for (String w : words.keySet()) { 
            if (isSimilar(w, word)) {
                suggested.add(w);
            }
        }
        return suggested;
    }

    // Check if two words are similar
    // TC-> O(num of words in dictionary * max. length of word) , SC-> constant
    private boolean isSimilar(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCount++;
                if (diffCount > 1) {
                    return false;
                }
            }
        }
        return true;
    }
}

