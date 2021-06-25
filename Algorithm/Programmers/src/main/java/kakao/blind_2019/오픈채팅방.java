package kakao.blind_2019;

import java.util.HashMap;
import java.util.Map;

public class 오픈채팅방 {
    public static String[] solution(String[] record) {
        int length = record.length;
        Map<String, String> users = new HashMap<>();     // userId : nickname
        Map<String, Integer> checkout = new HashMap<>(); // userId : Enter Count

        // 닉네임 변경한 유저 저장
        for (String s : record) {
            String[] recordSplit = s.split(" ");
            final String action = recordSplit[0];
            final String userId = recordSplit[1];

            if ("Enter".equals(action)) { // Enter -> 닉네임 저장 & 전에 입장한 경우 닉네임 수정
                users.put(userId, recordSplit[2]);
                checkout.put(userId, checkout.getOrDefault(userId, 0) + 1);
                if (checkout.get(userId) > 1) { // 전에 입장한 경우
                    users.put(userId, recordSplit[2]);
                }
            } else if ("Change".equals(action)) { // Change -> 경우 닉네임 업데이트
                length -= 1;
                users.put(userId, recordSplit[2]);
            }
        }

        String[] answer = new String[length];
        int index = 0;

        for (int i = 0; i < record.length; i++) {
            String[] recordSplit = record[i].split(" ");
            final String action = recordSplit[0];
            final String userId = recordSplit[1];

            // 닉네임 변경한 유저는 기록 X
            if ("Change".equals(action)) {
                continue;
            }

            // 변경한 유저 닉네임 수정
            if (!"Leave".equals(action) && users.get(userId) != null) {
                recordSplit[2] = users.get(userId);
            }

            answer[index++] = ("Enter".equals(action))
                    ? printEnter(recordSplit[2])
                    : printLeave(users, userId);

        }
        return answer;
    }

    private static String printEnter(String nickname) {
        return nickname + "님이 들어왔습니다.";
    }

    private static String printLeave(Map<String, String> users, String userId) {
        return users.get(userId) + "님이 나갔습니다.";
    }

    public static void main(String[] args) {
        String[] record = {"Enter uid0606 Gimoi", "Enter uid4567 Prodo", "Leave uid0606", "Enter uid1234 Prodo", "Change uid1234 OhYeah"};
        String[] result = solution(record);

        for (String s : result) {
            System.out.print(s + " ");
        }
    }
}
