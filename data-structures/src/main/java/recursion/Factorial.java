package recursion;

/**
 *  Factorial
 *  수학적 정의
 *   - n = 0일 경우, n! = 1 (Base case)
 *   - n > 0일 경우, n! = n * (n - 1)! (Recursive case)
 */
public class Factorial {
    public int factorial(int n) {
        if (n == 0) return 1;           // Base case
        return n * factorial(n - 1); // Recursive case
    }
}
