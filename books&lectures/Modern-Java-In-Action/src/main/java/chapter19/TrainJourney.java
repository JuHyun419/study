package chapter19;

public class TrainJourney {
    public int price;
    public TrainJourney next;

    public TrainJourney(int price, TrainJourney next) {
        this.price = price;
        this.next = next;
    }

    public static TrainJourney link(TrainJourney a, TrainJourney b) {
        if (a == null) return b;
        TrainJourney t = a;
        while (t.next != null) {
            t = t.next;
        }
        t.next = b;
        return b;
    }
}
