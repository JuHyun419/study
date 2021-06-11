package easy1;

public class Jewels_771 {

    public static int numJewelsInStones(String jewels, String stones) {
        int result = 0;

        for (int i = 0; i < jewels.length(); i++) {
            char jewel = jewels.charAt(i);
            for (int j = 0; j < stones.length(); j++) {
                char stone = stones.charAt(j);
                if (jewel == stone) {
                    result ++;
                }
            }
        }
        return result;
    }

    // String -> char[]
    public static int numJewelsInStones2(String jewels, String stones) {
        int result = 0;

        for (char jewel : jewels.toCharArray()) {
            for (char stone : stones.toCharArray()) {
                if (jewel == stone) {
                    result ++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String jewels = "aA";
        String stones = "aAAbbbb";
        System.out.println(numJewelsInStones(jewels, stones));
    }
}
