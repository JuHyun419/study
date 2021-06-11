package kit.hash;

import java.util.Arrays;

public class 전화번호_목록 {

    public static boolean solution(final String[] phone_book) {
        Arrays.sort(phone_book);

        for (int i = 0; i < phone_book.length - 1; i++) {
            if (isPrefix(phone_book, i)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPrefix(final String[] phone_book, final int i) {
        return phone_book[i + 1].startsWith(phone_book[i]);
    }

    public static void main(final String[] args) {
        final String[] phone_book = {"119", "97674223", "1195524421"};
        final String[] phone_book2 = {"12", "123", "1235", "567", "88"};


        System.out.println(solution(phone_book));
        System.out.println(solution(phone_book2));
    }

}
