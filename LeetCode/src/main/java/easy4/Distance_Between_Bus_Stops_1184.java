package easy4;

// TODO
public class Distance_Between_Bus_Stops_1184 {
    public static int distanceBetweenBusStops(int[] distance, int start, int destination) {
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

        for (int num : distance) {
            if (num != -1) {
                sum2 += num;
            }
        }
        return Math.min(sum1, sum2);
    }

    public static void main(String[] args) {
        int[] distance = {3, 6, 7, 2, 9, 10, 7, 16, 11};
        int start = 6;
        int destination = 2;
        System.out.println(distanceBetweenBusStops(distance, start, destination));
    }
}
