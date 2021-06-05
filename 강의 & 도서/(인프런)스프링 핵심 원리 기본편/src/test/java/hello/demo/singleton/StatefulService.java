package hello.demo.singleton;

public class StatefulService {  // Ctrl+Sfhit+T => 테스트 클래스 바로 생성 단축키

    private int price;  // 상태를 유지하는 필드

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }

}
