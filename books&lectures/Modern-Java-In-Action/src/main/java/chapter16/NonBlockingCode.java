package chapter16;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class NonBlockingCode {

    public static void main(String[] args) {
        // 8 Core
        System.out.println("N(CPU): " + Runtime.getRuntime().availableProcessors());
        NonBlockingCode n = new NonBlockingCode();
//        n.sequencePerformance();
//        System.out.println("===================================");
//
//        n.parallelPerformance();
//        System.out.println("===================================");
//
//        n.completableFuturePerformance();
//        System.out.println("===================================");
//
//        n.completableFutureWithExecutorPerformance();
//        System.out.println("===================================");
    }

    List<Shop> shops = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("BuyItAll"),
            new Shop("BuyItAll"),
            new Shop("BuyItAll"),
            new Shop("BuyItAll"),
            new Shop("BuyItAll"),
            new Shop("BuyItAll"),
            new Shop("BuyItAll"),
            new Shop("BuyItAll"),
            new Shop("BuyItAll"),
            new Shop("BuyItAll"),
            new Shop("BuyItAll"),
            new Shop("BuyItAll"),
            new Shop("BuyItAll"),
            new Shop("BuyItAll"),
            new Shop("BuyItAll"),
            new Shop("BuyItAll")
    );

    private List<String> findPricesSequential(String product) {
        return shops.stream()
                .map(shop ->
                        String.format("%s price is %.2f", shop.getProduct(), shop.getPrice(product))
                )
                .collect(Collectors.toList());
    }

    public List<String> findPricesParallel(String product) {
        return shops.parallelStream()
                .map(shop ->
                        String.format("%s price is %.2f", shop.getProduct(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    /* 애플리케이션 특성에 맞는 Executor를 만들어 CompletableFuture를 활용하는 것이 가장 바람직함 */
    private final Executor executor =
            Executors.newFixedThreadPool(Math.min(shops.size(), 100), r -> {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            });

    public List<String> findPricesCompletableFuture(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop ->
                                CompletableFuture.supplyAsync(() ->
                                        shop.getProduct() + " price is " + shop.getPrice(product)))
                        .collect(Collectors.toList());

        return priceFutures.stream()
                .map(CompletableFuture::join) // 모든 비동기 동작이 끝나기를 기다린다.
                .collect(Collectors.toList());
    }

    public List<String> findPricesCompletableFutureWithExecutor(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop ->
                                CompletableFuture.supplyAsync(() ->
                                        shop.getProduct() + " price is " + shop.getPrice(product), executor))
                        .collect(Collectors.toList());

        return priceFutures.stream()
                .map(CompletableFuture::join) // 모든 비동기 동작이 끝나기를 기다린다.
                .collect(Collectors.toList());
    }

    // 40128ms
    private void sequencePerformance() {
        long start = System.nanoTime();
        System.out.println(findPricesSequential("myPhone275"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + "ms");
    }

    // 6044ms
    private void parallelPerformance() {
        long start = System.nanoTime();
        System.out.println(findPricesParallel("myPhone275"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + "ms");
    }

    // 6228ms
    private void completableFuturePerformance() {
        long start = System.nanoTime();
        System.out.println(findPricesCompletableFuture("myPhone275"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + "ms");
    }

    // 2011ms
    private void completableFutureWithExecutorPerformance() {
        long start = System.nanoTime();
        System.out.println(findPricesCompletableFutureWithExecutor("myPhone275"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + "ms");
    }


}
