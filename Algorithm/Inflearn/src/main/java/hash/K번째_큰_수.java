package hash;

import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class K번째_큰_수 {

    public static void main(String[] args) {
        K번째_큰_수 T = new K번째_큰_수();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int k = kb.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }
        System.out.println(T.solution(arr, k));
    }

    private int solution(int[] arr, int index) {
        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    set.add(arr[i] + arr[j] + arr[k]);
                }
            }
        }

        return indexOf(set, index);
    }

    private int indexOf(Set<Integer> set, Integer k) {
        int count = 0;

        for (int number : set) {
            count++;
            if (count == k) return number;
        }

        return -1;
    }

}
