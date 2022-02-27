package chapter5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오.
 * 2. 거래자가 근무하는 모든 도시를 중복 없이 나열하시오.
 * 3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오.
 * 4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오.
 * 5. 밀라노에 거래자가 있는가?
 * 6. 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오.
 * 7. 전체 트랜잭션 중 최댓값은 얼마인가?
 * 8. 전체 트랜잭션 중 최솟값은 얼마인가?
 */
public class Problem {
    private static final Trader raoul = new Trader("Raoul", "Cambridge");
    private static final Trader mario = new Trader("mario", "Milan");
    private static final Trader alan = new Trader("alan", "Cambridge");
    private static final Trader brian = new Trader("brian", "Cambridge");

    private static final List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 610),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    /* 1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오. */
    public static void q1() {
        final List<Integer> transactionValuesAt2011 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .map(Transaction::getValue)
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Q1) 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오. \n" + transactionValuesAt2011 + "\n");
    }


    /* 2. 거래자가 근무하는 모든 도시를 중복 없이 나열하시오. */
    public static void q2() {
        final List<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Q2) 거래자가 근무하는 모든 도시를 중복 없이 나열하시오.\n" + cities + "\n");
    }


    /* 3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오. */
    public static void q3() {
        final List<String> cambridgeTraders = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                //.sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        final List<Trader> cambridgeTraders2 = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        System.out.println("Q3) 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오.\n" + cambridgeTraders + "\n");
        //cambridgeTraders2.forEach(trader -> System.out.println(trader.getName()));
    }


    /* 4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오. */
    public static void q4() {
        final List<String> traders = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                //.sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        final String traderString = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);

        System.out.println("Q4) 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오.\n" + traders);
        System.out.println(traderString + "\n");
    }


    /* 5. 밀라노에 거래자가 있는가? */
    public static void q5() {
        final boolean existMilan = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println("Q5) 밀라노에 거래자가 있는가?\n" + existMilan + "\n");
    }


    /* 6. 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오. */
    public static void q6() {
        System.out.println("Q6) 케임브르지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오.");
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .sorted()
                .forEach(values -> System.out.print(values + " "));
        System.out.println("\n");
    }

    /* 7. 전체 트랜잭션 중 최댓값은 얼마인가? */
    public static void q7() {
//        final Optional<Transaction> maxValue = transactions.stream()
//                .max(new Comparator<Transaction>() {
//                    @Override
//                    public int compare(Transaction o1, Transaction o2) {
//                        return Integer.compare(o1.getValue(), o2.getValue());
//                    }
//                });

//        final Optional<Transaction> maxValue = transactions.stream()
//                .max((o1, o2) -> Integer.compare(o1.getValue(), o2.getValue()));

        final Optional<Transaction> maxValue = transactions.stream()
                .max(Comparator.comparingInt(Transaction::getValue));

        final Optional<Integer> maxValue2 = transactions.stream()
                        .map(Transaction::getValue)
                                .reduce(Integer::max);

        maxValue.ifPresent(
                transaction -> System.out.println("Q7) 전체 트랜잭션 중 최댓값은 얼마인가?\n" + transaction.getValue() + "\n")
        );

        //maxValue2.ifPresent(System.out::println);
    }


    /* 8. 전체 트랜잭션 중 최솟값은 얼마인가? */
    public static void q8() {
        final Optional<Integer> minValue = transactions.stream()
                .map(transaction -> transaction.getValue())
                .reduce(Integer::min);

        System.out.println("Q8) 전체 트랜잭션 중 최솟값을 얼마인가?");
        minValue.ifPresent(System.out::println);
    }

    public static void main(String[] args) {
        Problem.q1();
        Problem.q2();
        Problem.q3();
        Problem.q4();
        Problem.q5();
        Problem.q6();
        Problem.q7();
        Problem.q8();
    }
}
