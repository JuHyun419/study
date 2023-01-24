package level2;

import java.util.HashMap;
import java.util.Map;

public class 할인_행사 {

    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int initialSize = 10;

        Map<String, Integer> wishProduct = new HashMap<>();
        Map<String, Integer> discountProduct = new HashMap<>();

        // 1. wishProduct - Map -> want : number
        for (int i = 0; i < want.length; i++) {
            wishProduct.put(want[i], number[i]);
        }

        // 2. discountProduct - discount 최초 10개 제품 put
        for (int i = 0; i < initialSize; i++) {
            discountProduct.put(discount[i], discountProduct.getOrDefault(discount[i], 0) + 1);
        }

        // 3. Two Pointer 왼쪽 제거, 오른쪽 추가
        if (registerMember(want, wishProduct, discountProduct)) {
            answer++;
        }

        int left = 0;

        for (int i = initialSize; i < discount.length; i++) {
            // 좌측 제품 제거
            String leftProduct = discount[left++];
            String rightProduct = discount[i];
            discountProduct.put(leftProduct, discountProduct.get(leftProduct) - 1);
            if (discountProduct.get(leftProduct) == 0) {
                discountProduct.remove(leftProduct);
            }

            // 우측 제품 추가
            discountProduct.put(rightProduct, discountProduct.getOrDefault(rightProduct, 0) + 1);

            if (registerMember(want, wishProduct, discountProduct)) {
                answer++;
            }
        }

        return answer;
    }

    private boolean registerMember(String[] want, Map<String, Integer> wishProduct, Map<String, Integer> discountProduct) {
        for (String s : want) {
            if (discountProduct.get(s) == null) {
                return false;
            }
            if (!wishProduct.get(s).equals(discountProduct.get(s))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] want = {"banana", "apple", "rice", "pork", "pot"};
        int[] number = {3, 2, 2, 2, 1};
        String[] discount = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};

        할인_행사 a = new 할인_행사();
        System.out.println(a.solution(want, number, discount));
    }

}
