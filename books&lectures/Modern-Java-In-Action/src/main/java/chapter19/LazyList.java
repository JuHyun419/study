package chapter19;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class LazyList<T> implements MyList<T> {

    final T head;
    final Supplier<MyList<T>> tail;

    public LazyList(T head, Supplier<MyList<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public MyList<T> tail() {
        return tail.get(); // Supplier를 통해 게으른 동작을 만들었다.
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public MyList<T> filter(Predicate<T> p) {
        return isEmpty()
                ? this
                : p.test(head())
                    ? new LazyList<>(head(), () -> tail().filter(p))
                    : tail().filter(p);
    }

    // n으로 시작하는 무한히 게으른 리스트
    public static LazyList<Integer> from(int n) {
        return new LazyList<>(n, () -> from(n + 1));
    }

    public static MyList<Integer> primes(MyList<Integer> numbers) {
        return new LazyList<>(
                numbers.head(),
                () -> primes(
                        numbers.tail().filter(
                                n -> n % numbers.head() != 0)
                )
        );
    }

    static <T> void printAll(MyList<T> list) {
        while (!list.isEmpty()) {
            System.out.println(list.head());
            list = list.tail();
        }
    }

    static <T> void printAllRecursive(MyList<T> list) {
        if (list.isEmpty()) return;
        System.out.println(list.head());
        printAllRecursive(list.tail());
    }

    public static void main(String[] args) {
        LazyList<Integer> numbers = from(2);
        int two = numbers.head();
        int three = numbers.tail().head();
        int four = numbers.tail().tail().head();
        System.out.println(two + " " + three + " " + four);

//        LazyList<Integer> numbers = from(2);
//        int one = primes(numbers).head();
//        int two = primes(numbers).tail().head();
//        int three = primes(numbers).tail().tail().head();
//        System.out.println(one + " " + two + " " + three);
//
//        //printAll(primes(from(2)));
//        printAllRecursive(primes(from(2)));
    }
}
