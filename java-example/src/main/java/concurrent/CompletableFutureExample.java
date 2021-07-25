package concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture: 자바에서 비동기(Asynchronous) 프로그래밍을 가능하게 하는 인터페이스
 */
public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("JuHyun");
        System.out.println(future.get()); // JuHyun

        CompletableFuture<String> future2 = CompletableFuture.completedFuture("JuHyun");
        System.out.println(future2.get()); // JuHyun
        System.out.println();


        // return 타입이 없는 경우 => Void
        CompletableFuture<Void> asyncFuture = CompletableFuture.runAsync(() ->
                System.out.println("Hello " + Thread.currentThread().getName()));
        asyncFuture.get(); // Hello ForkJoinPool.commonPool-worker-3
        System.out.println();


        // return 타입이 있는 경우
        CompletableFuture<String> supplyFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });
        System.out.println(supplyFuture.get()); // Hello ForkJoinPool.commonPool-worker-3 \n Hello
        System.out.println();


        // 콜백 제공
        // thenApply(Function): 리턴값을 받아서 다른 값으로 바꾸는 콜백
        CompletableFuture<String> callbackFunctionFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName()); // ForkJoinPool
            return "Hello";
        }).thenApply((s -> {
            System.out.println(Thread.currentThread().getName()); // main
            return s.toUpperCase(); // String::toUpperCase
        }));
        System.out.println(callbackFunctionFuture.get()); // HELLO(toUpperCase)
        System.out.println();


        // 콜백 제공
        // thenAccept(Consumer): 리턴값으로 또 다른 작업을 처리하는 콜백(리턴없이)
        CompletableFuture<Void> callbackConsumerFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenAccept((s) -> {
            System.out.println(Thread.currentThread().getName()); // main
            System.out.println(s.toUpperCase()); // HELLO(toUpperCase)
        });
    }
}
