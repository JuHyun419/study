package com.juhyun.springaop.before_decorator;

// 기본 도로 표시 + 차선 표시
public class RoadDisplayWithLane extends RoadDisplay {

    @Override
    public void draw() {
        super.draw(); // 상위 클래스(기본 도로 표시)
        drawLine();   // 추가적으로 차선 표시
    }

    private void drawLine() {
        System.out.println("차선 표시");
    }
}
