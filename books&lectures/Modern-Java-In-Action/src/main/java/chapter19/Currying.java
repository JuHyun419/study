package chapter19;

import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

public class Currying {

    public static double converter(double x, double f, double b) {
        return x * f + b;
    }

    public static DoubleUnaryOperator curriedConverter(double f, double b) {
        return (double x) -> x * f + b;
    }

    public static void main(String[] args) {
        System.out.println(Currying.converter(30, (9.0 / 5), 32));
        System.out.println(Currying.curriedConverter((9.0 / 5), 32).applyAsDouble(30));

        Function<String, Integer> strToInt = Integer::parseInt;
        System.out.println(strToInt.apply("100"));
    }

}
