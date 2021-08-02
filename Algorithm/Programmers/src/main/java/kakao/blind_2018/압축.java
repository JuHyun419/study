package kakao.blind_2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 압축 {

    static Map<String, Integer> dictionary = new HashMap<>();

    public static void initDictionary() {
        int index = 1;

        for (char ch = 'A'; ch <= 'Z'; ch++) {
            dictionary.put(ch + "", index++);
        }
    }

    public static int[] solution(String msg) {
        if (msg.length() == 1) {
            int number = msg.charAt(0) - 'A' + 1;
            return new int[] {number};
        }
        initDictionary();
        List<Integer> list = new ArrayList<>();

        int from = 0;
        int to = 1;
        int index = 27;

        while (from < msg.length()) {
            while (to <= msg.length()) {
                final String word = msg.substring(from, to);
                if (!dictionary.containsKey(word)) {
                    dictionary.put(word, index++);
                    break;
                }
                to += 1;
            }
            // 위 while문에서 "KA"가 사전에 존재하지 않는경우, "K"를 list에 추가해야 하므로 to - 1
            final String word = msg.substring(from, to - 1);
            list.add(dictionary.get(word));
            from = to -1;
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        String msg = "KAKAO";
        System.out.println(Arrays.toString(solution(msg)));
    }
}
