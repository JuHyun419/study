package inflearn;

public class TestClass {
    public static void main(String[] args) {
        String url = "url = https://www.inflearn.com/course/개발자-취업-통합편";

        System.out.println(url.substring(url.indexOf("h")));
        System.out.println(url.indexOf("h"));

       String test = " 총 41개 ˙ 5시간 40분의 수업";
        System.out.println(test.substring(0, test.indexOf("개")).replaceAll("\\W", ""));
    }
}
