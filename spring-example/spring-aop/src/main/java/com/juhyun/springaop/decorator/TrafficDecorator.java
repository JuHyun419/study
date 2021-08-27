package com.juhyun.springaop.decorator;

// 교통량 표시
public class TrafficDecorator extends DisplayDecorator {

    // 기존 표시 클래스 설정
    public TrafficDecorator(Display decoratedDisplay) {
        super(decoratedDisplay);
    }

    public void draw() {
        super.draw(); // 설정된 기존 표시 기능 수행
        drawTraffic();
    }

    private void drawTraffic() {
        System.out.println("\t교통량 표시");
    }
}
