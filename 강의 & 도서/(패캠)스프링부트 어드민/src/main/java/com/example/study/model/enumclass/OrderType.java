package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderType {
    
    All(0, "묶음", "모든 상품을 묶음 발송"),
    EACH(1, "개별", "모든 상품을 준비되는대로 발송");
    
    private final Integer id;
    private final String title;
    private final String description;
}
