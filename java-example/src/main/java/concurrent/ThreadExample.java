package concurrent;

/**
 * 쓰레드 주요 기능
 * sleep: 현재 쓰레드 멈춰두기(재우기)
 *   - 다른 쓰레드가 처리할 수 있도록 기회를 주지만, 그렇다고 락을 놔주지는 않음
 * interrupt: 다른 쓰레드 깨우기
 *   - 다른 쓰레드를 깨워서 interruptedException 예외 발생
 *   - 그 에러가 발생했을 때 할 일은 코딩하기 나름
 *   - 종료 시키거나 계속 하던 일을 마저 하도록 수행할 수 있음
 * join: 다른 쓰레드 기다리기
 *   - 다른 쓰레드가 끝날 때 까지 기다림
 */

public class ThreadExample {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        //myThread.start();

        Thread thread = new Thread(() -> {
//            try {
//                Thread.sleep(1000L); // Thread 재우기
//            } catch (InterruptedException e) {
//                e.printStackTrace(); // Thread가 자고있을 동안 다른 작업이 들어올경우 InterruptedException
//            }
            while (true) {
                System.out.println("Thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    System.out.println("exit !");
                    return; // 누군가가 나를 깨울경우 => 종료(return)
                }
            }
        });

        thread.start();

        System.out.println("Hello: " + Thread.currentThread().getName());
        Thread.sleep(3000L);
    }

    static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("Thread: " + Thread.currentThread().getName());
        }
    }


}
