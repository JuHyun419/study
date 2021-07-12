package recursion;

/**
 * https://www.mathsisfun.com/games/towerofhanoi.html
 * 첫 번째 기둥에서 세 번째 기둥으로 옮기기
 *  1. 한 번에 한 개의 원판만을 다른 탑으로 옮길 수 있다.
 *  2. 쌓아 놓은 원판은 항상 위의 것이 아래의 것보다 작아야 한다.
 */

/**
 * 귀납적 사고
 * 기둥 1, 기둥 2, 기둥 3
 * n-1개의 원판을 기둥 1 -> 기둥 2로 옮긴다.
 * n번째 원판을 기둥 1 -> 기둥 3으로 옮긴다.
 * n-1개의 원판을 기둥 2 -> 기둥 3으로 옮긴다.
 * 즉, n-1개의 원판을 원하는 곳으로 옮길 수만 있다면, n개의 원판을 처리할 수 있음(재귀)
 * -> 원판이 n-1개일 때 옮길 수 있으면 원판이 n개일 때도 옮길 수 있다.
 */

/**
 * 1. 함수 정의
 * void func(int a, int  b, int n)
 *   -> 원판 n개를 a번 기둥에서 b번 기둥으로 옮기는 방법을 출력하는 함수
 *
 * 2. base condition
 * n = 1 일때, 출력
 *
 * 3. 재귀 식
 * 기둥 6-a-b -> 기둥이 3개있을때, 총 합은 6이므로 a,b가 아닌 기둥을 의미
 *   1) n-1개의 원판을 기둥 a에서 기둥 6-a-b로 옮긴다.
 *   2) n번째 원판을 기둥 a에서 기둥 b로 옮긴다.
 *   3) n-1개의 원판을 기둥 6-a-b에서 기둥 b로 옮긴다.
 */
public class Hanoi {

    // https://www.acmicpc.net/problem/11729

    /**
     * 하노이 탑(원판 3개 기준)
     * @param start -> a번 기둥
     * @param to -> b번 기둥
     * @param n -> 원판 갯수
     */
    void hanoi(int start, int to, int n) {
        if (n == 1) {
            System.out.println(start + " " + to);
            return;
        }

        hanoi(start, 6 - start - to, n - 1);
        System.out.println(start + " " + to);
        hanoi(6 - start - to, to, n - 1);
    }

}
