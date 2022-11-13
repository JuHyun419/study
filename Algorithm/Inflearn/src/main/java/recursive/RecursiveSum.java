package recursive;

public class RecursiveSum {

    public static void main(String[] args) {
        recursivePrint(3);

        System.out.println();

        recursiveBinaryNumber(11);

        System.out.println();

        System.out.println(recursiveFactorial(5));
    }


    /**
     * 재귀함수를 이용하여 1 ~ N 출력하는 프로그램
     */
    private static void recursivePrint(int number) {
        if (number == 0) {
            return;
        }

        recursivePrint(number - 1);

        System.out.print(number + " ");
    }

    /**
     * 재귀함수를 이용하여 10진수 -> 2진수 출력
     */
    private static void recursiveBinaryNumber(int number) {
        if (number == 0) return;

        recursiveBinaryNumber(number / 2);

        System.out.print(number % 2 + " ");
    }

    /**
     * 재귀함수를 이용하여 N! 구하기
     */
    private static int recursiveFactorial(int number) {
        if (number == 1) return 1;

        return number * recursiveFactorial(number - 1);
    }

}
