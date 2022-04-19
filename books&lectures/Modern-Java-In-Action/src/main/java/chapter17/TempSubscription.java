package chapter17;

import java.util.concurrent.Flow;

/**
 * Subscriber가 요청할 때마다 해당 도시의 온도를 전송
 */
public class TempSubscription implements Flow.Subscription {

    private final Flow.Subscriber<? super TempInfo> subscriber;
    private final String town;

    public TempSubscription(Flow.Subscriber<? super TempInfo> subscriber, String town) {
        this.subscriber = subscriber;
        this.town = town;
    }

    @Override
    public void request(long n) {
        for (long i = 0; i < n; i++) {
            try {
                subscriber.onNext(TempInfo.fetch(town));
            } catch (Exception e) {
                subscriber.onError(e);
                break;
            }
        }
    }

    @Override
    public void cancel() {
        // 구독이 취소되면 완료신호를 Subscriber로 전달
        subscriber.onComplete();
    }
}
