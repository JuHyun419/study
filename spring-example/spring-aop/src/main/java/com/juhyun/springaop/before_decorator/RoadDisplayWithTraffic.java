package com.juhyun.springaop.before_decorator;

// 기본 도로 표시 + 교통량 표시
public class RoadDisplayWithTraffic extends RoadDisplay {

    @Override
    public void draw() {
        super.draw(); // 상위 클래스(기본 도로 표시)
        drawTraffic(); // 교통량 표시
    }

    private void drawTraffic() {
        System.out.println("교통량 표시");
    }
}
