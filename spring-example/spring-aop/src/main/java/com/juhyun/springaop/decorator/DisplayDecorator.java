package com.juhyun.springaop.decorator;

// 다양한 추가 기능에 대한 공통 클래스
public abstract class DisplayDecorator extends Display {
    private final Display decoratedDisplay;

    public DisplayDecorator(Display decoratedDisplay) {
        this.decoratedDisplay = decoratedDisplay;
    }

    public void draw() {
        decoratedDisplay.draw();
    }

}
