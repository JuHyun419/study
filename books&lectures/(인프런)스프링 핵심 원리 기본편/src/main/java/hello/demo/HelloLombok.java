package hello.demo;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok("age", 10);
        helloLombok.setName("JuHyun");
        helloLombok.setAge(17);

        String name = helloLombok.getName();
        int age = helloLombok.getAge();
        System.out.println("name = " + name);
        System.out.println("age = " + age);
        System.out.println(helloLombok.toString());
    }
}
