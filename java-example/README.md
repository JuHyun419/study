## Java 예제 코드

https://www.inflearn.com/course/the-java-java8/dashboard

### Stream API

### DateTime

### CompletableFuture

#### Thread
- 쓰레드 주요 기능
    - sleep: 현재 쓰레드 멈춰두기(재우기)
        - 다른 쓰레드가 처리할 수 있도록 기회를 주지만, 그렇다고 락을 놔주지는 않음
    - interrupt: 다른 쓰레드 깨우기
        - 다른 쓰레드를 깨워서 interruptedException 예외 발생
        - 그 에러가 발생했을 때 할 일은 코딩하기 나름
         - 종료 시키거나 계속 하던 일을 마저 하도록 수행할 수 있음
    - join: 다른 쓰레드 기다리기
        - 다른 쓰레드가 끝날 때 까지 기다림


#### Executors(ExecutorService)

![image](https://user-images.githubusercontent.com/50076031/125734275-c5cc6ed3-40ca-4683-9374-197e4ba200ca.png)

ScheduledExecutorService --(구현)--> ExecutorService --(구현)--> Executor
- Executors(고수준 High-Level) Concurrency 프로그래밍
    - 쓰레드를 만들고 관리하는 작업을 애플리케이션에서 분리 
    - 위의 기능을 Executors에게 위임함
- Executors가 하는 일
    - 쓰레드 생성: 애플리케이션이 사용할 쓰레드 풀을 만들어 관리
    - 쓰레드 관리: 쓰레드의 생명 주기를 관리
    - 쓰레드 작업 처리 및 실행: 쓰레드로 실행할 작업을 제공할 수 있는 API 제공

- 주요 인터페이스
    - Executor: execute(Runnable)
    - ExecutorService(주로사용): Executor을 상속 받은 인터페이스로, Callable도 실행할 수 있으며
                               Executor을 종료시키거나 여러 Callable를 동시애 실행하는 등의 기능 제공
    - ScheduledExecutorService: ExecutoreService를 상속받은 인터페이스, 특정 시간 이후 또는 주기적 작업 실행


#### Callable
- Callable: Runnable과 유사하지만 작업의 결과를 받을 수 있음(Future 타입)


#### Future
- Future: 비동기적인 작업의 현재 상태를 조회하거나 결과를 가져올 수 있음

#### CompletableFuture
- 자바에서 비동기(Asynchronous) 프로그래밍을 가능하게 하는 인터페이스
    - Future를 사용해서도 어느정도 가능했지만 하기 힘든 일들이 많았음
        - Future를 외부에서 완료시킬 수 없음
        - 취소하거나, get()에 타임아웃을 설정할 수 없음
        - 블로킹 코드(get())을 사용하지 않고서는 작업이 끝났을 때 콜백을 실행할 수 없음
        - 작업은 get() 호출 이후에 진행
        - 여러 Future를 조합할 수 없음
            - ex) Event 정보를 가져온 뒤 다음 Event에 참석하는 회원 목록 가져오기
        - 예외 처리용 API를 제공하지 않음
    
- 비동기 작업 실행(static 메소드)
    - 리턴값이 없는 경우: runAsync(Runnable)
    - 리턴값이 있는 경우: supplyAsync(Supplier)
    - 원하는 쓰레드풀(Executor)을 사용해서 실행할 수도 있음(기본은 [ForkJoinPool](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ForkJoinPool.html))
    
- 콜백 제공
    - thenApply(Function): 리턴값을 받아서 다른 값으로 바꾸는 콜백
    - thenAccept(Consumer): 리턴값으로 또 다른 작업을 처리하는 콜백(리턴없음)
    
#### CompletableFuture2
- 조합
    - thenCompose(): 두 작업이 서로 이어서 실행하도록 조합
    - thenCombine(): 두 작업을 독립적으로 실행하고 둘 다 종료했을 때 콜백 실행
    - allOf(): 여러 작업을 모두 실행하고 모든 작업 결과에 콜백 실행
    - anyOf(): 여러 작업 중에 가장 빨리 끝난 하나의 결과에 콜백 실행
    
- 예외처리
    - exceptionally(Function)
    - handle(BiFunction)