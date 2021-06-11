package easy1;

public class Decrypt_1309 {
    public static final char[] ALPHABETS = {
            '0', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z'
    };

    public static String freqAlphabets2(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chArr = s.toCharArray();
        int length = s.length();

        for (int i = 0; i < length; i++) {
            if (i + 2 < length && chArr[i + 2] == '#') { // 앞에 조건이 만족못하면 뒤 조건은 판별 안함
                int index = Integer.parseInt(s.substring(i, i + 2));
                sb.append(ALPHABETS[index]);
                i += 2;
            } else {
                int index = s.charAt(i) - '0';
                sb.append(ALPHABETS[index]);
            }
        }
        return sb.toString();
    }


    public static String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for (int i = 0; i < s.length() - 2; i++) {
            if (isShop(s.charAt(i + 2))) {
                String str = s.substring(i, i + 2);
                sb.append(ALPHABETS[Integer.parseInt(str)]);
                i += 2;
            } else {
                int index = s.charAt(i) - '0';
                sb.append(ALPHABETS[index]);
            }
        }

        // 문자열 마지막부터 '#"이 나올때까지 문자 추가
        for (int i = s.length() - 1; ; i--) {
            if (isShop(s.charAt(i))) break;

            int index = s.charAt(i) - '0';
            sb2.append(ALPHABETS[index]);
        }

        return sb.append(sb2.reverse()).toString();
    }

    private static boolean isShop(final char ch) {
        return ch == '#';
    }

    public static void main(String[] args) {
        String str = "26#11#418#5";
        System.out.println(freqAlphabets(str));
    }

}
