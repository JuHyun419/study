package twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 공통원소_구하기 {

    private List<Integer> solution(int n, int m, int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        List<Integer> list = new ArrayList<>();
        int index1 = 0;
        int index2 = 0;

        while (index1 < n && index2 < m) {
            if (a[index1] == b[index2]) {
                list.add(a[index1]);
                index1++;
                index2++;
                continue;
            }

            if (a[index1] > b[index2]) index2++;
            else index1 ++;
        }
        return list;
    }

    public static void main(String[] args) {
        공통원소_구하기 T = new 공통원소_구하기();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = kb.nextInt();
        }
        int m = kb.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = kb.nextInt();
        }
        for (int x : T.solution(n, m, a, b)) System.out.print(x + " ");
    }

}
