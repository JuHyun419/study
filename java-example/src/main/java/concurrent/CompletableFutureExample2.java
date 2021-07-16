package concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        // thenCompose(): 두 작업이 서로 이어서 실행하도록 조합
        CompletableFuture<String> future = hello.thenCompose(CompletableFutureExample2::getWorld);
        System.out.println(future.get()); // Hello World


        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });

        // thenCombine(): 두 작업을 독립적으로 실행하고 둘 다 종료했을 때 콜백 실행
        CompletableFuture<String> future1 = hello.thenCombine(world, (h, w) -> h + " " + w);
        System.out.println(future1.get()); // Hello World

        // anyOf(): 여러 작업 중 가장 빨리 끝난 하나의 결과에 콜백 실행
        CompletableFuture<Void> anyOfFuture = CompletableFuture.anyOf(hello, world).thenAccept(System.out::println);
        anyOfFuture.get();


        // 예외 처리
        boolean thrownError = true;

        CompletableFuture<String> errorFuture = CompletableFuture.supplyAsync(() -> {
            if (thrownError) {
                throw new IllegalArgumentException();
            }
            System.out.println("Hello ~");
            return "Hello";
        }).exceptionally(ex -> {
            System.out.println("예외 발생 시 처리할 작업");
            return "Error !!";
        });
        System.out.println(errorFuture.get()); // Error !!
    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return message + " World";
        });
    }
}
