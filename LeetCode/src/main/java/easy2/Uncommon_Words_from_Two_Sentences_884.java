package easy2;

import java.util.HashMap;
import java.util.Map;

public class Uncommon_Words_from_Two_Sentences_884 {
    public String[] uncommonFromSentences(String str1, String str2) {
        Map<String, Integer> map = new HashMap<>();
        String[] split1 = str1.split(" ");
        String[] split2 = str2.split(" ");

        for (String s : split1) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        for (String s : split2) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        return map.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .toArray(String[]::new);

    }

}
