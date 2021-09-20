package string2;

import java.util.ArrayList;
import java.util.List;

public class Partition_Labels_763 {
    public List<Integer> partitionLabels(String s) {
        int[] alphabet = new int[26];
        int max = 0;
        int position = 0;
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            alphabet[s.charAt(i) - 'a'] = i;
        }

        for (int i = 0; i < s.length(); i++) {
            max = Math.max(max, alphabet[s.charAt(i) - 'a']);
            position++;
            if (isLastPartitionLetters(max, i)) {
                list.add(position);
                position = 0;
            }
        }
        return list;
    }

    private boolean isLastPartitionLetters(int max, int i) {
        return i == max;
    }
}
