package level1;

import java.util.Stack;

public class 햄버거_만들기 {

    public int solution(int[] ingredient) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();

        for (int food : ingredient) {
            stack.push(food);

            // 햄버거 포장 여부 확인
            if (stack.size() >= 4) {
                if (isPackingHamburger(stack)) {
                    answer++;
                    // 햄버거 포장 후 음식 제외
                    stack.pop();
                    stack.pop();
                    stack.pop();
                    stack.pop();
                }
            }
        }
        
        return answer;
    }

    private boolean isPackingHamburger(Stack<Integer> stack) {
        return stack.get(stack.size() - 4) == 1
                && stack.get(stack.size() - 3) == 2
                && stack.get(stack.size() - 2) == 3
                && stack.get(stack.size() - 1) == 1;
    }


}
