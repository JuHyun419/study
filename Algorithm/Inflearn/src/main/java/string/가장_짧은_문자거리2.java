package string;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 가장_짧은_문자거리2 {

    /**
     * 문자 t의 index를 구한 후 현재 문자와의 차이 중 최솟값을 하나씩 비교
     */
    public int[] solution(String s, char t) {
        int[] answer = new int[s.length()];
        List<Integer> tIndex = new ArrayList<>();
        int index = 0;

        // 문자 t의 index 구하기
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == t) {
                tIndex.add(i);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == t) {
                answer[i] = 0;
                continue;
            }

            // list의 사이즈 이내이면서 문자 t와 떨어진 거리의 차이가 더 커질경우 -> 문자 t를 저장한 리스트의 index + 1
            if (index < tIndex.size() - 1 &&
                    Math.abs(tIndex.get(index) - i) > Math.abs(tIndex.get(index + 1) - i)) {
                index++;
            }

            answer[i] = Math.abs(tIndex.get(index) - i);
        }
        return answer;
    }


    public static void main(String[] args) {
//        Main T = new Main();
//        Scanner kb = new Scanner(System.in);
//        String str=kb.next();
//        char c=kb.next().charAt(0);
//        for(int x : T.solution(str, c)){
//            System.out.print(x+" ");
//        }
    }

}
