package level2;

public class 다음_큰_숫자 {

    public static int solution(int n) {
        int defaultZeroCount = countOne(n);
        int compareZeroCount;
        for (int i = n + 1; ; i++) {
            compareZeroCount = countOne(i);
            if (defaultZeroCount == compareZeroCount) {
                return i;
            }
        }
    }

    private static int countOne(int n) {
        int result = 0;
        while (n != 0) {
            result += n%2 == 0 ? 0 : 1;
            n /= 2;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(16));   // 32
        System.out.println(solution(78));   // 83

        // bitCount => num 숫자를 binary 로 변환 후 1의 갯수 반환
        System.out.println(Integer.bitCount(15)); // 4
        System.out.println(Integer.bitCount(16)); // 1
    }
}
