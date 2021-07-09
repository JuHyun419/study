package combination;

/**
 * 조합(nCr) : n! / r! * (n - r)!
 *  - 서로 다른 n개 중에서 r개를 선택하는 경우(순서 상관 X ---> 중복 허용 X)
 *  - ex) [1, 2, 3] 에서 2개를 선택하는 경우 -> [1, 2] = [2, 1]
 *      - [1, 2]
 *      - [1, 3]
 *      - [2, 3]
 */
public class Combination {

    /**
     * 백트래킹
     * @param arr - 조합 뽑아낼 배열
     * @param start - 조합에서의 현재 인덱스(기준)
     * @param n - 배열 길이
     * @param r - 뽑을 갯수
     */
    public static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            print(arr, visited, n);
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    /**
     * 재귀 호출
     * @param arr - 조합 뽑아낼 배열
     * @param visited - 조합 뽑았는지 체크
     * @param depth - 현재 인덱스
     * @param n - 배열 길이
     * @param r - 뽑을 갯수
     */
    public static void combination2(int[] arr, boolean[] visited, int depth, int n, int r) {
        if (r == 0) {
            print(arr, visited, n);
            return;
        }

        if (depth == n) {
            return;
        }

        // 현재 인덱스 뽑기
        visited[depth] = true;
        // 뽑든 뽑지않든 depth는 +1, 뽑은갯수는 -1
        combination2(arr, visited, depth + 1, n, r - 1);

        // 현재 인덱스 안뽑기
        visited[depth] = false;
        // 뽑든 뽑지않든 depth는 +1, 뽑은 갯수는 유지
        combination2(arr, visited, depth + 1, n, r);
    }

    private static void print(int[] arr, boolean[] visited, int n) {
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        final int n = 4;
        int[] arr = {1, 2, 3, 4};
        boolean[] visited = new boolean[n];

        for (int i = 1; i <= n; i++) {
            System.out.println("\n" + "nPr" + n + " 개 중에서 " + i + "개 뽑기 ");
            combination(arr, visited, 0, n, i);
        }
    }
}
