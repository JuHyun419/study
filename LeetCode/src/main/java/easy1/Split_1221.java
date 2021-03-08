package easy1;

public class Split_1221 {

    public static int balancedStringSplit(String s) {
        int count = 0;
        int LCount = 0;
        int RCount = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'L') LCount ++;
            else if (s.charAt(i) == 'R') RCount ++;

            if (LCount == RCount) {
                count ++;
                LCount = 0;
                RCount = 0;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String s = "RLLLLRRRLR";
        System.out.println(balancedStringSplit(s));
    }
}
