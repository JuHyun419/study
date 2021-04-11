package easy2;

import java.util.ArrayList;
import java.util.List;

public class Find_1002 {

    public static List<String> commonChars(String[] arr) {
        int[] alphbet = new int[26];
        String[] alphbetChar = {"a", "b", "c", "d", "e", "f", "g",
                "h", "i", "j", "k", "l", "m", "n", "o", "p",
                "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        List<String> list = new ArrayList<>();

        for (String s : arr) {
            for (int j = 0; j < s.length(); j++) {
                alphbet[s.charAt(j) - 'a']++;
            }
        }

        for (int i = 0; i < alphbet.length; i++) {
            for (int j = 0; j < alphbet[i] / arr.length; j++) {
                list.add(alphbetChar[i]);
            }
        }

        return list;
    }

    public static void main(String[] args) {
        String[] arr = {"acabcddd","bcbdbcbd","baddbadb","cbdddcac","aacbcccd","ccccddda","cababaab","addcaccd"};

        List<String> list = commonChars(arr);

        for (String s : list) {
            System.out.println(s);
        }
    }

}
