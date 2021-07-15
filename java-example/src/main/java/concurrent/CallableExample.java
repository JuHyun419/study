package concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Callable: Runnable과 유사하지만 작업의 결과를 받을 수 있음(Future 타입)
 * Future: 비동기적인 작업의 현재 상태를 조회하거나 결과를 가져올 수 있음
 */
public class CallableExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Future<String> helloFuture = executorService.submit(hello);
        System.out.println(helloFuture.isDone()); // false
        System.out.println("Started!!");

        // get을 만나기 전까지 기다리고, get을 만난 후 결과값을 가져올때까지 기다림(블로킹)
        // Waits if necessary for the computation to complete, and then retrieves its result.
        helloFuture.get();

        // cancel을 호출하면 get으로 가져올 수 없음
        helloFuture.cancel(false);
        // helloFuture.get();  --> CancellationException 예외 발생

        System.out.println(helloFuture.isDone()); // true
        System.out.println("End !!");
        executorService.shutdown();
    }
}
