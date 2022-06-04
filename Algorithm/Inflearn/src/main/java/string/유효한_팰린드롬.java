package string;

import java.util.Scanner;

public class 유효한_팰린드롬 {

    public static void main(String[] args) {
        유효한_팰린드롬 T = new 유효한_팰린드롬();
        Scanner kb = new Scanner(System.in);
        String str = kb.nextLine();
        System.out.print(T.solution(str));
    }

    private String solution(String str) {
        // remove not words
        str = str.toUpperCase().replaceAll("[^A-Z]", "");

        if (new StringBuilder(str).reverse().toString().equals(str)) {
            return "YES";
        }
        return "NO";
    }

}
