package chapter17;

import java.util.concurrent.Flow;

/**
 * Publisher, Subscriber 사이의 중재자
 */
public class TempSubscriber implements Flow.Subscriber<TempInfo> {

    private Flow.Subscription subscription;

    // Publisher 구독
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    // 수신한 온도를 출력하고 다음 정보를 요청
    @Override
    public void onNext(TempInfo item) {
        System.out.println(item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable t) {
        System.err.println(t.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("Done!");
    }
}
