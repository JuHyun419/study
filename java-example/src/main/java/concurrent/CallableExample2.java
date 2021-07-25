package concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };
        Callable<String> java = () -> {
            Thread.sleep(3000L);
            return "Java";
        };
        Callable<String> juhyun = () -> {
            Thread.sleep(1000L);
            return "JuHyun";
        };

        // Callable 을 한 번에 가져옴
        // invokeAll() -> 각 작업은 2초, 3초, 1초이지만 모두 끝날때까지 기다린다.
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, juhyun));

        for (Future<String> future : futures) {
            System.out.println(future.get());
        }

        String any = executorService.invokeAny(Arrays.asList(hello, java, juhyun));
        System.out.println(any);

        executorService.shutdown();
    }
}
