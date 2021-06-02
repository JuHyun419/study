package level2;

import java.util.Arrays;

public class 전화번호_목록 {

    public boolean solution(final String[] phoneBook) {
        Arrays.sort(phoneBook);

        for (int i = 0; i < phoneBook.length - 1; i++) {
            if (phoneBook[i + 1].startsWith(phoneBook[i]))
                return false;
        }
        return true;
    }

    public static void main(final String[] args) {
        final String[] str = {"119", "11964512", "11954512"};
        Arrays.sort(str);
        System.out.println(Arrays.toString(str));
    }

}
