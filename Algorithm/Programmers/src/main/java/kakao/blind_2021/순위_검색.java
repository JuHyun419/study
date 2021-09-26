package kakao.blind_2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO
public class 순위_검색 {

    static final int SCORE_NUMBER = 4;
    static Map<String, List<Integer>> infos = new HashMap<>();
    static List<Integer> list;

    public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        int infoLength = info.length;
        int queryLength = query.length;

        // 1) info로 가능한 모든 경우의수 만들기
        for (int i = 0; i < infoLength; i++) {
            dfs("", 0, info[i].split(" "));
        }

        // 2) 점수 List 오름차순 정렬(이분탐색)
        listValueSortAsc();

        // 3) 이분탐색
        for (int i = 0; i < queryLength; i++) {
            // "java and backend and junior and pizza 100" => "javabackendjuniorpizza 100"
            String[] split = query[i].replaceAll(" and ", "").split(" ");
            answer[i] = binarySearch(split[0], Integer.parseInt(split[1]));
        }
        return answer;
    }

    private static void dfs(String str, int depth, String[] info) {
        if (depth == SCORE_NUMBER) {
            if (!infos.containsKey(str)) {
                list = new ArrayList<>();
                list.add(Integer.parseInt(info[4]));
                infos.put(str, list);
            } else {
                infos.get(str).add(Integer.parseInt(info[4]));
            }
            return;
        }
        dfs(str + "-", depth + 1, info);
        dfs(str + info[depth], depth + 1, info);
    }

    private static void listValueSortAsc() {
        for (String s : infos.keySet()) {
            List<Integer> scoreList = infos.get(s);
            Collections.sort(scoreList);
        }
    }

    private static int binarySearch(String str, int score) {
        if (!infos.containsKey(str)) {
            return 0;
        }
        List<Integer> scoreList = infos.get(str);
        int left = 0;
        int right = scoreList.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (scoreList.get(mid) < score) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return scoreList.size() - left;
    }

    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        solution(info, null);
    }

}
