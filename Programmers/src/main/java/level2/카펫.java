package level2;

public class 카펫 {

    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        for (int i = 3; i < 5000; i++) {     // 가로
            for (int j = 3; j < 5000; j++) { // 세로
                if ((i - 2) * (j - 2) == yellow && i * j == (brown + yellow)) {
                    return new int[]{Math.max(i, j), Math.min(i, j)};
                }
            }
        }
        return answer;
    }

}
