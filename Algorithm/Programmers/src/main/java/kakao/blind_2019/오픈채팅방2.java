package kakao.blind_2019;

import java.util.HashMap;
import java.util.Map;

public class 오픈채팅방2 {
    static Map<String, String> users = new HashMap<>();

    public String[] solution(String[] record) {
        int length = record.length;

        for (String s : record) {
            final String[] recordSplit = s.split(" ");
            final String action = recordSplit[0];
            final String userId = recordSplit[1];
            if (action.equals("Enter") || action.equals("Change")) {
                users.put(userId, recordSplit[2]);
            }
            if ("Change".equals(action)) {
                length -= 1;
            }
        }

        String[] answer = new String[length];
        int index = 0;
        for (String s : record) {
            final String[] recordSplit = s.split(" ");
            final String action = recordSplit[0];
            final String userId = recordSplit[1];
            if (!action.equals("Change")) {
                answer[index++] = getMessage(action, userId);
            }
        }
        return answer;
    }

    private String getMessage(final String action, final String userId) {
        if ("Enter".equals(action)) {
            return users.get(userId) + "님이 들어왔습니다.";
        }
        return users.get(userId) + "님이 나갔습니다.";
    }

    public static void main(String[] args) {
        오픈채팅방2 o = new 오픈채팅방2();
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        String[] result = o.solution(record);

        for (String s : result) {
            System.out.print(s + " ");
        }
    }

}
