package kit.bruteforce;

public class 카펫 {

    public int[] solution(final int brown, final int yellow) {
        final int area = brown + yellow;

        for (int i = 3; ; i++) {
            for (int j = 3; j <= 5000; j++) {
                if (i * j == area && (i - 2) * (j - 2) == yellow) {
                    return new int[]{j, i};
                }
            }
        }
    }

}
