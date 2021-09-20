package string;

import java.util.HashMap;
import java.util.Map;

public class Word_Pattern_290 {
    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> charMap = new HashMap<>();
        Map<String, Character> strMap = new HashMap<>();
        String[] words = s.split(" ");
        char[] charArray = pattern.toCharArray();

        if (pattern.length() != words.length) {
            return false;
        }

        for (int i = 0; i < charArray.length; i++) {
            // pattern: "abba", s: "dog cat cat fish"
            if (charMap.containsKey(charArray[i]) && !charMap.get(charArray[i]).equals(words[i])) {
                return false;
            }
            // pattern: "abba", s: "dog dog dog dog"
            if (strMap.containsKey(words[i]) && !strMap.get(words[i]).equals(charArray[i])) {
                return false;
            }
            charMap.put(charArray[i], words[i]);
            strMap.put(words[i], charArray[i]);
        }
        return true;
    }
}
