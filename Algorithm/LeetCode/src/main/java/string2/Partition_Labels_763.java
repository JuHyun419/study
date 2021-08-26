package string2;

import java.util.ArrayList;
import java.util.List;

// TODO:
public class Partition_Labels_763 {
    public List<Integer> partitionLabels(String s) {
        int[] alphabet = new int[26];

        for (int i = 0; i < s.length(); i++) {
            alphabet[s.charAt(i) - 'a'] = i;
        }
        int max = 0;
        int position = 0;
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            max = Math.max(max, alphabet[s.charAt(i) - 'a']);
            position++;
            if (i == max) {
                list.add(position);
                position = 0;
            }
        }
        return list;
    }
}
