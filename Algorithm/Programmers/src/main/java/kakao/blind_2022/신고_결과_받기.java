package kakao.blind_2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class 신고_결과_받기 {

    public int[] solution(String[] id_list, String[] report, int k) {
        report = removeDuplicationReport(report);

        Map<String, List<String>> reportList = new HashMap<>(); // 유저가 신고한 id 리스트
        Map<String, Integer> users = new HashMap<>(); // 유저 별 신고당한 횟수

        for (int i = 0; i < report.length; i++) {
            final String user = report[i].split(" ")[0]; // 신고한 유저
            final String reporter = report[i].split(" ")[1]; // 신고받은 유저

            users.put(reporter, users.getOrDefault(reporter, 0) + 1);

            List<String> list = reportList.getOrDefault(user, new ArrayList<>());
            list.add(reporter);
            reportList.put(user, list);
        }

        int[] answer = new int[id_list.length];

        /* 각 유저가 받은 결과 메일 수 계산 */
        for (int i = 0; i < id_list.length; i++) {
            List<String> reports = reportList.get(id_list[i]);
            if (reports == null) continue;

            int count = 0;
            for (String s : reports) {
                if (users.get(s) >= k) {
                    count++;
                }
            }
            answer[i] = count;
        }

        return answer;
    }

    /* 중복 신고 제거 */
    private String[] removeDuplicationReport(String[] report) {
        Set<String> reports = new HashSet<>(Arrays.asList(report));

        return reports.toArray(new String[0]);
    }

    public static void main(String[] args) {
        신고_결과_받기 a = new 신고_결과_받기();
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        System.out.println(Arrays.toString(a.solution(id_list, report, 2)));
    }

}
