package com.juhyun.springaop.decorator;

// 차선 표시
public class LaneDecorator extends DisplayDecorator {
    
    // 기존 표시 클래스 설정
    public LaneDecorator(Display decoratedDisplay) {
        super(decoratedDisplay);
    }
    
    public void draw() {
        super.draw(); // 설정된 기존 표시 기능 수행
        drawLane();   // 추가적으로 차선 표시
    }

    private void drawLane() {
        System.out.println("\t차선 표시");
    }
}
