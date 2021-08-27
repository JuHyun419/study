package com.juhyun.springaop.before_decorator;

public class Client {
    public static void main(String[] args) {
        RoadDisplay road = new RoadDisplay();
        road.draw(); // 기본 도로만 표시

        RoadDisplay roadWithLane = new RoadDisplayWithLane();
        roadWithLane.draw(); // 기본 도로 + 차선 표시
    }
}
