package level2;

public class 나라의_숫자_124 {

    public static String solution(int n) {
        StringBuilder sb = new StringBuilder();

        while (n != 0) {
            int rest = n % 3;

            int result = (rest == 0) ? 4 : rest;
            sb.append(result);

            n = (n-1) / 3;
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        int n1 = 3;
        int n2 = 10;
        int n3 = 40;

        System.out.println(solution(n1));
        System.out.println(solution(n2));
        System.out.println(solution(n3));
    }

}
