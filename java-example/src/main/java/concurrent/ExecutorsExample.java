package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Executors(고수준 High-Level) Concurrency 프로그래밍
 *   - 쓰레드를 만들고 관리하는 작업을 애플리케이션에서 분리
 *   - 위의 기능을 Executors에게 위임함
 *
 * Executors가 하는 일
 *   - 쓰레드 생성: 애플리케이션이 사용할 쓰레드 풀을 만들어 관리
 *   - 쓰레드 관리: 쓰레드의 생명 주기를 관리
 *   - 쓰레드 작업 처리 및 실행: 쓰레드로 실행할 작업을 제공할 수 있는 API 제공
 *
 * 주요 인터페이스
 *   - Executor: execute(Runnable)
 *   - ExecutorService(주로사용): Executor을 상속 받은 인터페이스로, Callable도 실행할 수 있으며
 *                              Executor을 종료시키거나 여러 Callable를 동시애 실행하는 등의 기능 제공
 *   - ScheduledExecutorService: ExecutoreService를 상속받은 인터페이스, 특정 시간 이후 또는 주기적 작업 실행
 */

// ScheduledExecutorService --(구현)--> ExecutorService --(구현)--> Executor
public class ExecutorsExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
        });
        // ExecutorService 는 다음 작업이 들어올 때까지 무한대기 하기때문에 명시적으로 종료를 해줘야함
        executorService.shutdown();


        // 2개의 쓰레드
        ExecutorService executorService2 = Executors.newFixedThreadPool(2);
        // 5개의 작업 -> 2개의 쓰레드로 번갈아가면서 작업을 실행
        executorService2.submit(getRunnable("Hello"));
        executorService2.submit(getRunnable("JuHyun"));
        executorService2.submit(getRunnable("World"));
        executorService2.submit(getRunnable("Java"));
        executorService2.submit(getRunnable("Spring"));
        executorService2.shutdown();


        // 스케쥴 쓰레드
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 3초 뒤 실행
        scheduledExecutorService.schedule(getRunnable("Scheduled"), 3, TimeUnit.SECONDS);
        // 1초 기다린 후 2초에 한 번씩 출력
        scheduledExecutorService.scheduleAtFixedRate(getRunnable("Scheduled"), 1, 2, TimeUnit.SECONDS);
    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread().getName());
    }
}
