package chapter19;

import java.util.function.Function;

public class Combinator {

    // f의 기능을 적용한 후 g의 기능을 적용하는 함수를 반환
    static <A, B, C> Function<A, C> compose(Function<B, C> g, Function<A, B> f) {
        return x -> g.apply(f.apply(x));
    }

    // 함수 f에 연속적으로 n번 적용하는 루프
    static <A> Function<A, A> repeat(int n, Function<A, A> f) {
        return n == 0
                ? function -> function
                : compose(f, repeat(n - 1, f));
    }

    public static void main(String[] args) {
        System.out.println(repeat(3, (Integer x) -> 2 * x).apply(10));
    }

}
