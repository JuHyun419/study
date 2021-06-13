package easy4;

public class Distance_Between_Bus_Stops2_1184 {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int sum1 = 0;
        int sum2 = 0;
        if (start > destination) {
            int temp = start;
            start = destination;
            destination = temp;
        }

        for (int i = start; i < destination; i++) {
            sum1 += distance[i];
            distance[i] = -1;
        }

        for (int i = 0; i < distance.length; i++) {
            if (distance[i] != -1) {
                sum2 += distance[i];
            }
        }

        return Math.min(sum1, sum2);
    }

}
