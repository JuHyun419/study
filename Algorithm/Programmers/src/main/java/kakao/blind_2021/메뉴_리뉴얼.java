package kakao.blind_2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 메뉴_리뉴얼 {

    static List<String> combination = new ArrayList<>();

    public String[] solution(String[] orders, int[] course) {
        for (String order : orders) {
            // 주문 당 모든 조합 구하기
            char[] orderChar = order.toCharArray();
            Arrays.sort(orderChar);

            // 정렬 후 한 글자씩 선택
            for (int j = 0; j < orderChar.length - 1; j++) {
                // course에 있는 단품메뉴들의 갯수에 대한 경우의수 구하기
                for (int k = 0; k < course.length; k++) {
                    dfs(orderChar, j, 1, course[k], String.valueOf(orderChar[j]));
                }
            }
        }

        Map<String, Integer> map = new HashMap<>();
        for (String key : combination) {
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        List<String> list = new ArrayList<>(map.keySet());


        Collections.sort(list, (o1, o2) ->
            map.get(o2).compareTo(map.get(o1)));

        List<String> answerList = new ArrayList<>();
        for (int i = 0; i < course.length; i++) {
            int max = 0;
            for (String key : list) {
                if (map.get(key) >= 2 && key.length() == course[i]) {
                    if (map.get(key) > max) {
                        answerList.add(key);
                        max = map.get(key);
                    }
                }
            }
        }
        Collections.sort(answerList);
        String[] answer = new String[answerList.size()];
        answerList.toArray(answer);
        return answer;
    }

    private static void dfs(char[] arr, int index, int count, int course, String str) {
        if (count == course) {
            combination.add(str);
            return;
        }

        for (int i = index + 1; i < arr.length; i++) {
            dfs(arr, i, count + 1, course, str + arr[i]);
        }
    }

}
