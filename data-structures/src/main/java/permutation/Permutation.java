package permutation;

import java.util.List;

/**
 * 순열(nPr) : n! / (n - r)!
 *  - 서로 다른 n개 중에서 r개를 선택하는 경우(순서 상관 O ---> 중복 허용 O)
 *  - ex) [1, 2, 3] 에서 2개를 선택하는 경우
 *      - [1, 2]
 *      - [1, 3]
 *      - [2, 1]
 *      - [2, 3]
 *      - [3, 1]
 *      - [3, 2]
 */
public class Permutation {

    /**
     * 순열
     * @param arr - 기준 리스트
     * @param result - 결과 리스트
     * @param n - 전체 갯수
     * @param r - 뽑을 갯수
     */
    private static void permutation(List<Integer> arr, List<Integer> result, int n, int r) {
        if (r == 0) {
            System.out.println(result.toString());
            return;
        }

        for (int i = 0; i < n; i++) {
            result.add(arr.remove(i));
            permutation(arr, result, n - 1, r - 1);
            arr.add(i, result.remove(result.size() - 1));
        }
    }
}
