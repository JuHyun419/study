package recursion;

/*
if the string is empty
    return 0;
else
    return 1 plus the length of the string that
    excludes the first character;
 */
public class RecursiveString {

    /* 문자열의 길이 계산 */
    public static int length(String str) {
        if (str.equals("")) { // Base case
            return 0;
        } else { // Recursive case
            return 1 + length(str.substring(1));
        }
    }


    /* 문자열의 프린트 */
    public static void printChars(String str) {
        if (str.length() == 0) { // Base case
            return;
        } else { // Recursive case
            System.out.println(str.charAt(0));
            printChars(str.substring(1));
        }
    }


    /* 문자열을 뒤집어 프린트 */
    public static void printCharsReverse(String str) {
        if (str.length() == 0) {
            return;
        } else {
            printCharsReverse(str.substring(1));
            System.out.println(str.charAt(0));
        }
    }


    /* 2진수로 변환하여 출력 */
    public static void printBinary(int n) {
        if (n < 2) {
            System.out.print(n);
        } else {
            printBinary(n / 2);
            System.out.println(n % 2);
        }
    }


    /* 배열의 합 구하기 data[0] ~ data[n-1] 까지의 합 */
    public static int sum(int n, int[] data) {
        if (n <= 0) {
            return 0;
        } else {
            return sum(n - 1, data) + data[n - 1];
        }
    }


    public static void main(String[] args) {
        int n = 15;
        printBinary(15);
    }
}
