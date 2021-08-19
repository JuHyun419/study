package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO: 다른 풀이 확인
public class Shortest_Distance_to_a_Character_821 {
    public static int[] shortestToChar(String s, char c) {
        int[] answer = new int[s.length()];
        List<Integer> indexList = new ArrayList<>();
        int index = 0;

        // 문자열에서 문자가 'e'인 index 넣기
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                indexList.add(i); // 3, 5, 6, 11
            }
        }

        for (int i = 0; i < s.length(); i++) {
            // 가장 오른쪽에 존재하는 문자인 경우
            if (index == indexList.size() - 1) {
                answer[i] = Math.abs(indexList.get(index) - i);
                continue;
            }

            // List에 존재하는 문자의 위치를 비교해서 작은값으로 이동
            int leftDistance = Math.abs(indexList.get(index) - i);
            int rightDistance = Math.abs(indexList.get(index + 1) - i);
            if (leftDistance > rightDistance) {
                index++;
            }

            // 탐색하는 문자가 주어진 문자와 동일한경우 -> 차이는 0
            if (s.charAt(i) == c) {
                answer[i] = 0;
                continue;
            }
            answer[i] = Math.abs(indexList.get(index) - i);
        }
        return answer;
    }

    public static void main(String[] args) {
        String s = "loveleetcode";

        System.out.println(Arrays.toString(shortestToChar(s, 'e')));
    }
}