package chapter10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DSL {

    private List<String> collectRowFromFile(String fileName, String words, int errorCount) throws IOException {
        List<String> errors = new ArrayList<>();
        int count = 0;
        try (BufferedReader bf = new BufferedReader(new FileReader(fileName))) {
            String line = bf.readLine();

            while (count < errorCount && line != null) {
                if (line.startsWith(words)) {
                    errors.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> errors2 = Files.lines(Paths.get(fileName))
                .filter(line -> line.startsWith(words))
                .limit(errorCount)
                .collect(Collectors.toList());
        return errors;
    }

    public static void main(String[] args) {
        List<String> numbers = Arrays.asList("one", "two", "three");
        numbers.forEach(s -> System.out.println(s));
        numbers.forEach(System.out::println);

        List<Person> persons = new ArrayList<>();
        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        Collections.sort(persons, (p1, p2) -> p1.getAge() - p2.getAge());

        // Comparator 클래스에서 제공하는 정적 유틸리티 메서드
        Collections.sort(persons, Comparator.comparing(Person::getAge));
        persons.sort(Comparator.comparing(Person::getAge));

        // 나이 역순 정렬
        persons.sort(Comparator.comparing(Person::getAge).reversed());

        // 나이 정렬 -> 같은 나이는 알파벳 순 정렬
        persons.sort(Comparator.comparing(Person::getAge)
                .thenComparing(Person::getName)
        );
    }
}

class Person {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}