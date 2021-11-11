package hello.springadvanced.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {

    public void save(String itemId) {
        // TODO: 2021/11/08 저장 로직

        if ("ex".equals(itemId)) {
            throw new IllegalStateException("예외 발생!");
        }

        sleep(1000);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void save2(String s) {
        if (s == "테스트") {
            System.out.println("~");
        }

        if (s.equals("test")) {
            System.out.println();

        }

        //TEST
        // test



    }

    public static void main(String[] args) {
        OrderRepositoryV0 ov = new OrderRepositoryV0();
        String test = "Aa";
         ov.save("test");

        System.out.println("CodeGuru Test");
        ov.save("ex");
    }
}
