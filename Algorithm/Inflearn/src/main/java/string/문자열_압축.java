package string;

import java.util.Scanner;

public class 문자열_압축 {

    public static String solution(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (i = 0; i < s.length() - 1; i++) {
            char ch = s.charAt(i);
            int count = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (ch != s.charAt(j)) {
                    i = j - 1;
                    break;
                }
                count++;
            }
            sb.append(ch);
            if (count > 1) {
                sb.append(count);
            }
        }

        if (i == s.length() - 1) {
            sb.append(s.charAt(sb.length() - 1));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(문자열_압축.solution("KKTFFFFFFEEE"));
//        Main T = new Main();
//        Scanner kb = new Scanner(System.in);
//        String str = kb.next();
//        char c = kb.next().charAt(0);
//        for (int x : T.solution(str, c)) {
//            System.out.print(x + " ");
//        }
    }

}
