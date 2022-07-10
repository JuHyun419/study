package hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Runtime Error
public class 모든_아나그램_찾기 {

    private int solution(String s, String t) {
        int count = 0;

        for (int i = 0; i <= s.length() - t.length(); i++) {
            String compare = s.substring(i, i + t.length());
            if (isAnagram(compare, t)) {
                count++;
            }
        }
        return count;
    }

    private boolean isAnagram(String compare, String t) {
        Map<Character, Integer> anagram1 = new HashMap<>();
        Map<Character, Integer> anagram2 = new HashMap<>();

        for (char ch : compare.toCharArray()) {
            anagram1.put(ch, anagram1.getOrDefault(ch, 0) + 1);
        }

        for (char ch : t.toCharArray()) {
            anagram2.put(ch, anagram2.getOrDefault(ch, 0) + 1);
        }

        if (anagram1.size() != anagram2.size()) return false;

        for (Character character : anagram1.keySet()) {
            if (anagram1.get(character) != anagram2.get(character)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        모든_아나그램_찾기 T = new 모든_아나그램_찾기();
        Scanner kb = new Scanner(System.in);
        String a = kb.next();
        String b = kb.next();
        System.out.print(T.solution(a, b));
    }
}
