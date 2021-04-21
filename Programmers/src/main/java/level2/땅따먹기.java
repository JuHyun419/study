package level2;


// TODO:
public class 땅따먹기 {

    public static int solution(int[][] land) {

        // 한 행씩 내려가면서 이전 행과 더해서 최댓값 구하기(같은 열은 연속해서 밟을 수 없음)
        for (int i = 1; i < land.length; i++) {
            land[i][0] += Math.max(land[i - 1][1], Math.max(land[i - 1][2], land[i - 1][3]));
            land[i][1] += Math.max(land[i - 1][0], Math.max(land[i - 1][2], land[i - 1][3]));
            land[i][2] += Math.max(land[i - 1][0], Math.max(land[i - 1][1], land[i - 1][3]));
            land[i][3] += Math.max(land[i - 1][0], Math.max(land[i - 1][1], land[i - 1][2]));
        }

        int size = land.length - 1;

        // 마지막 행에서 최댓값
        return Math.max(land[size][0],
                 Math.max(land[size][1],
                   Math.max(land[size][2], land[size][3])));
    }

    public static void main(String[] args) {
        int[][] land = {{1, 2, 3, 5}, {5, 6, 7, 8}, {4, 3, 2, 1}};
        System.out.println(solution(land));
    }

}
