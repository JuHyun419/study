package streamapi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        System.out.println("spring으로 시작하는 수업");
        springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .forEach(oc -> System.out.println(oc.getId() + " " + oc.getTitle()));


        System.out.println("===================================\n");
        System.out.println("close 되지 않은 수업");
        springClasses.stream()
                .filter(Predicate.not(OnlineClass::isClosed)) // .filter(oc -> !oc.isClosed()) 동일한 결과
                .forEach(oc -> System.out.println(oc.getId() + " " + oc.getTitle()));


        System.out.println("===================================\n");
        System.out.println("수업 이름만 모아서 스트림 만들기");
        springClasses.stream()
                .map(OnlineClass::getTitle)
                .forEach(System.out::println);


        System.out.println("===================================\n");
        System.out.println("스프링 수업 중에 제목에 spring이 들어간(filter) 것 제목만 모아서(map) List로 만들기(collect)");
        List<String> springList = springClasses.stream()
                .map(OnlineClass::getTitle)
                .filter(title -> title.contains("spring"))
                .collect(Collectors.toList());


        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        System.out.println("===================================\n");
        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        boolean test = javaClasses.stream().anyMatch(oc -> oc.getTitle().contains("Test"));
        System.out.println(test);


        System.out.println("===================================\n");
        System.out.println("자바 수업 중에 id가 8인 수업만 출력");
        javaClasses.stream()
                .filter(oc -> oc.getId().equals(8))
                .forEach(System.out::println);


        List<List<OnlineClass>> events = new ArrayList<>();
        events.add(springClasses);
        events.add(javaClasses);


        System.out.println("===================================\n");
        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        events.stream()
                .flatMap(Collection::stream)    // 연산 작업(Operation)을 할 때마다 연산 타입이 무엇인지 계산할 수 있어야함
                .forEach(oc -> System.out.println(oc.getId()));


        System.out.println("===================================\n");
        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        Stream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

    }
}
