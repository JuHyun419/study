package easy4;

// TODO: 코드 확인
public class Greatest_Common_Divisor_of_Strings_1071 {

    public String gcdOfStrings3(String a, String b) {
        if (!(a + b).equals(b + a)) return "";
        return a.substring(0, gcd(a.length(), b.length()));
    }

    private int gcd(int a, int b){
        if(a == 0)
            return b;
        return gcd(b%a, a);
    }

    public static String gcdOfStrings2(String str1, String str2) {
        if (str1.length() < str2.length()) {
            String temp = str1;
            str1 = str2;
            str2 = temp;
        }

        while (!str2.isEmpty()) {
            if (str1.equals(str2)) {
                return str1;
            }

            if (str1.startsWith(str2)) {
                String str = str1.substring(str2.length());
                str1 = str2;
                str2 = str;
            } else {
                return "";
            }
        }
        return str1;
    }

}
