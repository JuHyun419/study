package easy5;

import java.util.Arrays;

public class Sorting_The_Sentence_1859 {

    public static String sortSentence(String s) {
        String[] words = s.split(" ");

        // 문자열 뒤집기(숫자가 가장 앞으로 오도록 -> 정렬하기 위해)
        for (int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder(words[i]);
            words[i] = sb.reverse().toString();
        }
        Arrays.sort(words);
        StringBuilder sb = new StringBuilder();

        // 초기의 문자열로 뒤집고 추가
        for (String word : words) {
            StringBuilder reverse = new StringBuilder(word).reverse();
            sb.append(reverse + " ");
        }
        return sb.toString().replaceAll("\\d", "").trim();
    }

    public static void main(String[] args) {
        String str = "is2 sentence4 This1 a3";
        System.out.println(sortSentence(str));
    }
}
