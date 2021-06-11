package kit.greedy;

public class 체육복 {

    public static int solution(final int n, final int[] lost, final int[] reserve) {
        int answer = n - lost.length;

        for (int i = 0; i < lost.length; i++) {
            for (int j = 0; j < reserve.length; j++) {
                if (lost[i] == reserve[j]) {
                    lost[i] = -1;
                    reserve[j] = -1;
                    answer++;
                    break;
                }
            }
        }

        for (int i = 0; i < lost.length; i++) {
            for (int j = 0; j < reserve.length; j++) {
                if (isRentClothes(lost[i], reserve[j])) {
                    lost[i] = -1;
                    reserve[j] = -1;
                    answer++;
                    break;
                }
            }
        }

        return answer;
    }

    private static boolean isRentClothes(final int i1, final int i2) {
        return i1 == (i2 + 1) || i1 == (i2 - 1);
    }

    public static void main(final String[] args) {
        final int n = 8;
        final int[] lost = {1, 2, 4, 6};
        final int[] reserve = {1, 2, 4, 6};

        System.out.println(solution(n, lost, reserve));
    }
}
