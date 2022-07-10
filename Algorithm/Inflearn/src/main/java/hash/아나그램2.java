package hash;

import java.util.Arrays;
import java.util.Scanner;

// 정렬 사용
public class 아나그램2 {

    private String solution(String a, String b) {
        char[] array1 = a.toCharArray();
        char[] array2 = b.toCharArray();

        Arrays.sort(array1);
        Arrays.sort(array2);

        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return "NO";
            }
        }
        return "YES";
    }

    public static void main(String[] args) {
        아나그램2 T = new 아나그램2();
        Scanner kb = new Scanner(System.in);
        String a = kb.next();
        String b = kb.next();
        System.out.print(T.solution(a, b));
    }
}
