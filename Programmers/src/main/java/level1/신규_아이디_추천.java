package level1;

public class 신규_아이디_추천 {

    public static String solution(String newId) {
        newId = stepOne(newId);

        newId = stepTwo(newId);

        newId = stepThree(newId);

        newId = stepFour(newId);

        newId = stepFive(newId);

        newId = stepSix(newId);

        newId = stepSeven(newId);

        return newId;
    }

    private static String stepOne(final String newId) {
        return newId.toLowerCase();
    }

    private static String stepTwo(final String newId) {
        final String regex = "[^\\w\\-.]*"; // 대문자도 제거를 안하지만, stepOne에서 대문자를 소문자로 변경했으므로 상관X
        return newId.replaceAll(regex, "");
    }

    private static String stepThree(final String newId) {
        final String regex = "\\.{2,}";
        return newId.replaceAll(regex, ".");
    }

    private static String stepFour(String newId) {
        if (isDot(newId.charAt(0))) {   // first
            newId = newId.substring(1);
        }

        if (!newId.isEmpty() && isDot(newId.charAt(newId.length() - 1))) {  // last
            newId = newId.substring(0, newId.length() - 1);
        }

        return newId;
    }

    private static boolean isDot(final char c) {
        return c == '.';
    }

    private static String stepFive(String newId) {
        if (newId.isEmpty()) {
            return "a";
        }
        return newId;
    }

    private static String stepSix(String newId) {
        if (newId.length() >= 16) {
            newId = newId.substring(0, 15);
        }

        if (isDot(newId.charAt(newId.length() - 1))) {  // last
            newId = newId.substring(0, newId.length() - 1);
        }

        return newId;
    }

    private static String stepSeven(String newId) {
        if (newId.length() <= 2) {
            while (newId.length() != 3) {
                newId += newId.charAt(newId.length() - 1);
            }
        }
        return newId;
    }


    public static void main(String[] args) {
        String test = "a!bkd3dB$$k;~##8d-_aa66.k";
        String regex = "[^\\w\\-.]*";   // \w ==> 알파벳(대소문자), 숫자
        String regex2 = "[^a-z\\d\\-_.]*";
        System.out.println(test.replaceAll(regex2, ""));

        String test2 = "aa..f.kk";
        String test3 = "aa......bkd.ff..c";

        String regex3 = "\\.{2,}";
        test3 = test3.replaceAll(regex3, ".");
        System.out.println(test3);

        String test11 = "=.=";

        System.out.println(solution(test11));




    }
}
