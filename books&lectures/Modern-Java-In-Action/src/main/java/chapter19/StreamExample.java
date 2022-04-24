package chapter19;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {
    public static void main(String[] args) {
        primes(5).forEach(System.out::println);
    }

    public static Stream<Integer> primes(int n) {
        return Stream.iterate(2, i -> i + 1)
                .filter(StreamExample::isPrime)
                .limit(n);
    }

    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

}
