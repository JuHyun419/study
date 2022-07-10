package hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class 매출액의_종류 {

    public static void main(String[] args) {
        매출액의_종류 T = new 매출액의_종류();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int k = kb.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }
        for (int x : T.solution(k, arr)) System.out.print(x + " ");
    }

    private List<Integer> solution(int k, int[] arr) {
        int left = 0;
        int count = 0;
        Map<Integer, Integer> sales = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        for (int right = 0; right < arr.length; right++) {
            sales.put(arr[right], sales.getOrDefault(arr[right], 0) + 1);
            count++;

            if (count == k) { // 연속된 K일
                result.add(sales.size()); // 현재 까지 매출액의 종류
                count--;
                if (sales.get(arr[left]) > 1) { // 이미 존재하는 매출액일 경우 - 1
                    sales.put(arr[left], sales.get(arr[left]) - 1);
                } else { // 존재하지 않는 매출액의 경우 제거
                    sales.remove(arr[left]);
                }
                left++;
            }
        }
        return result;
    }

    private List<Integer> solution2( int k, int[] arr) {
        int left = 0;
        Map<Integer, Integer> sales = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        // K-1일 까지 매출액 종류 넣기
        for (int i = 0; i < k - 1; i++) {
            sales.put(arr[i], sales.getOrDefault(arr[i], 0) + 1);
        }

        // 연속된 K일부터 Two Pointers
        for (int right = k - 1; right < arr.length; right++) {
            sales.put(arr[right], sales.getOrDefault(arr[right], 0) + 1);
            result.add(sales.size()); // 현재 까지 매출액의 종류
            sales.put(arr[left], sales.get(arr[left]) - 1);

            if (sales.get(arr[left]) == 0) {
                sales.remove(arr[left]);
            }
            left++;
        }
        return result;
    }
}
