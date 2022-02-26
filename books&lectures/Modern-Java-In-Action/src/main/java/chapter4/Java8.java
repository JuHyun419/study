package chapter4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8 {

    public static void main(String[] args) {
        Java8.method4();
    }

    private static final List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
    );

    public static void method1() {
        final List<String> lowCaloricDishesName = menu.stream() // parallelStream(): 병렬 처리
                .filter(d -> d.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(dish -> dish.getName())
                .collect(Collectors.toList());
    }

    public static void method2() {
        List<String> threeHighCaloricDishNames = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());

        System.out.println(threeHighCaloricDishNames);
    }

    public static void method3() {
        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> stream = title.stream();
        stream.forEach(System.out::println);
        stream.forEach(System.out::println);
    }

    public static void method4() {
        List<String> names = menu.stream()
                .filter(dish -> {
                    System.out.println("filter: " + dish.getName());
                    return dish.getCalories() > 300;
                })
                .map(dish -> {
                    System.out.println("map: " + dish.getName());
                    return dish.getName();
                })
                .limit(3)
                .collect(Collectors.toList());

        System.out.println(names);
    }
}
