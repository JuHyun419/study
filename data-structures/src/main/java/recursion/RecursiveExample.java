package recursion;

/*
if the string is empty
    return 0;
else
    return 1 plus the length of the string that
    excludes the first character;
 */
public class RecursiveExample {

    /* 1 ~ n 까지의 합을 구한다 -> n + (1부터 n-1까지의 합) */
    public static int func(int n) {
        if (n == 0) return 0;
        else {
            return n + func(n - 1);
        }
    }


    /* 피보나치 -> f(0) = 0, f(1) = 1, f(n) = f(n-1) + f(n-2) */
    public static int fibonacci(int n) {
        if (n < 2) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }


    /* 문자열의 길이 계산 -> 1 + (1 ~ 나머지 문자열의 길이의 합) */
    public static int length(String str) {
        if (str.equals("")) { // Base case
            return 0;
        }
        return 1 + length(str.substring(1)); // Recursive case
    }


    /* 문자열의 프린트 */
    public static void printChars(String str) {
        if (str.length() == 0) { // Base case
            return;
        }
        System.out.println(str.charAt(0));
        printChars(str.substring(1));
    }


    /* 문자열을 뒤집어 프린트 */
    public static void printCharsReverse(String str) {
        if (str.length() == 0) {
            return;
        }
        printCharsReverse(str.substring(1));
        System.out.println(str.charAt(0));
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
        }
        return sum(n - 1, data) + data[n - 1];
    }


    /* data[0] ~ data[n-1] 사이에서 target을 검색한다 */
    public static int search(int[] data, int n, int target) {
        for (int i = 0; i < n; i++) {
            if (data[i] == target) {
                return i;
            }
        }
        return -1;
    }


    /* data[begin] ~ data[end] 사이에서 target을 검색한다 */
    public static int search(int[] data, int begin, int end, int target) {
        if (begin > end) {
            return -1;
        } else if (data[begin] == target) {
            return begin;
        } else {
            return search(data, begin + 1, end, target);
        }
    }


    /* data[begin] ~ data[end] 사이에서 target을 검색한다 */
    public static int search2(int[] data, int begin, int end, int target) {
        if (begin > end) {
            return -1;
        } else if (data[end] == target) {
            return end;
        } else {
            return search2(data, begin, end - 1, target);
        }
    }


    /* data[begin] ~ data[end] 사이에서 최대값을 찾아 반환한다 */
    public static int findMax(int[] data, int begin, int end) {
        if (begin == end) {
            return data[begin];
        } else {
            return Math.max(data[begin], findMax(data, begin + 1, end));
        }
    }


    /* Binary Search - data[begin] ~ data[end] 사이에서 target을 검색한다 */
    public static int binarySearch(String[] data, String target, int begin, int end) {
        if (begin > end) {
            return -1;
        } else {
            int middle = (begin + end) / 2;
            int compareResult = target.compareTo(data[middle]);
            if (compareResult == 0) {
                return middle;
            } else if (compareResult < 0) {
                return binarySearch(data, target, begin, middle - 1);
            } else {
                return binarySearch(data, target, middle + 1, end);
            }
        }
    }


    public static void main(String[] args) {
        int n = 15;
        printBinary(15);
    }
}
