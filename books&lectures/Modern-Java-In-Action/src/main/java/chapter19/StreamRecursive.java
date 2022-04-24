package chapter19;

import java.util.stream.IntStream;

public class StreamRecursive {

    public static void main(String[] args) {
        primes(numbers()).forEach(System.out::println);
    }

    public static IntStream numbers() {
        return IntStream.iterate(2, n -> n + 1);
    }

    public static int head(IntStream numbers) {
        return numbers.findFirst().getAsInt();
    }

    public static IntStream tail(IntStream numbers) {
        return numbers.skip(1);
    }

    public static IntStream primes(IntStream numbers) {
        int head = head(numbers);
        return IntStream.concat(
                IntStream.of(head),
                primes(tail(numbers).filter(n -> n % head != 0).limit(5))
        );
    }

}
