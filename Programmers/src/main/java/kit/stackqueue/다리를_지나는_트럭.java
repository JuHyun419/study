package kit.stackqueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// TODO:
public class 다리를_지나는_트럭 {

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> waitTruck = new LinkedList<>();
        List<Truck> workingTruck = new ArrayList<>();

        for (int t : truck_weights) {
            waitTruck.add(t);
        }

        // 다리위 트럭의 총 무게 - 초기값: 현재 다리위로 올라갈 트럭(peek)
        int totalWeight = waitTruck.peek();

        // 첫번째 트럭은 다리위로 바로 건너므로 넣어주기
        workingTruck.add(new Truck(waitTruck.poll(), 0));
        int time = 0;
        while (!workingTruck.isEmpty()) {
            time++;

            // ★ 현재 다리위 트럭은 모두 한칸 씩 이동시킨다.
            for (Truck truck : workingTruck) {
                truck.index++;
            }

            // 다리를 건넌 트럭은 제외
            if (workingTruck.get(0).index > bridge_length) {
                totalWeight -= workingTruck.get(0).weight;
                workingTruck.remove(0);
            }

            // 다리를 건넌 트럭을 제외하고, 다리에 트럭을 더 올릴 수 있을때
            if (!waitTruck.isEmpty() && totalWeight + waitTruck.peek() <= weight) {
                int nextTruck = waitTruck.poll();
                totalWeight += nextTruck;
                workingTruck.add(new Truck(nextTruck, 1));
            }
        }
        return time;
    }

    static class Truck {
        int weight;
        int index;

        public Truck(int weight, int index) {
            this.weight = weight;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        int bridge_length = 100;
        int weight = 100;
        int[] truck_weights = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        System.out.println(solution(bridge_length, weight, truck_weights));
    }

}
