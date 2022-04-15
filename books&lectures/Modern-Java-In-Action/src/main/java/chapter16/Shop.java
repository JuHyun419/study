package chapter16;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {

    private final String product;

    public Shop(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            double price = calculatePrice(product); // 다른 스레드에서 비동기적으로 계산 수행
            futurePrice.complete(price); // 오랜 시간이 걸리는 계산이 완료되면 Future에 값 설정
        }).start();
        return futurePrice; // 계산 결과를 기다리지 Future 반환
    }

    private double calculatePrice(String product) {
        delay();

        Random random = new Random();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /* 오래 걸리는 작업 */
    public static void delay() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
