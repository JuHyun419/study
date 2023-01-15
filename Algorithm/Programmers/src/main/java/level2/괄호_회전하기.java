package level2;

import java.util.Map;
import java.util.Stack;

public class 괄호_회전하기 {

    // 여닫이 괄호 쌍
    Map<Character, Character> brackets = Map.of(
            '(', ')',
            '[', ']',
            '{', '}'
    );


    public int solution(String s) {
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            StringBuilder rotate = new StringBuilder();
            for (int j = i; j < s.length(); j++) {
                rotate.append(s.charAt(j));
            }

            for (int j = 0; j < i; j++) {
                rotate.append(s.charAt(j));
            }

            // 2. 올바른 괄호 문자열인지 체크
            if (isRightBracket(rotate.toString())) {
                answer++;
            }
        }

        return answer;
    }

    private boolean isRightBracket(String word) {
        int size = word.length();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < size; i++) {
            char ch = word.charAt(i);
            // 스택이 비어있고 닫는 괄호인 경우
            if (stack.isEmpty() && brackets.containsValue(ch)) {
                return false;
            }

            // '(', '[', '{' 인 경우
            if (brackets.containsKey(ch)) {
                stack.push(ch);
                continue;
            }

            // 닫는 괄호와 여는 괄호가 다른 경우
            if (ch != brackets.get(stack.peek())) { // ']', '{'
                return false;
            }
            stack.pop();
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String str = "[](){}";
        괄호_회전하기 a = new 괄호_회전하기();
        System.out.println(a.solution(str));
    }

}
