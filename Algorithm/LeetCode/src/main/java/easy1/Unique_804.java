package easy1;

import java.util.HashSet;
import java.util.Set;

public class Unique_804 {
    public static final String[] TABLE =
            {".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
                    "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.",
                    "--.-", ".-.", "...", "-", "..-", "...-",
                    ".--", "-..-", "-.--", "--.."};

    public static int uniqueMorseRepresentations(String[] words) {
        Set<String> set = new HashSet<>();

        for (int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < words[i].length(); j++) {
                //int index = Character.getNumericValue(words[i].charAt(j)) - 10;
                int index = words[i].charAt(j) - 'a';
                sb.append(TABLE[index]);
            }
            set.add(sb.toString());
        }
        return set.size();
    }

    public static void main(String[] args) {
        String[] words = {"gin", "zen", "gig", "msg"};
        System.out.println(uniqueMorseRepresentations(words));
    }
}
