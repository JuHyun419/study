package chapter17;

import java.util.concurrent.Flow;

public class Main {

    // 뉴욕에 새 Publisher를 만들고 TempSubscriber를 구독시킴
    public static void main(String[] args) {
        getTemperatures("New York").subscribe(new TempSubscriber());
    }

    // 구독한 Subscriber에게 TempSubscription을 전송하는 Publisher를 반환
    private static Flow.Publisher<TempInfo> getTemperatures(String town) {
        return subscriber -> subscriber.onSubscribe(new TempSubscription(subscriber, town));
    }

}
