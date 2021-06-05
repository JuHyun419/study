package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

    REGISTERED(0, "등록", "사용자 등록상태"),
    UNREGISTERED(1, "해지", "사용자 해지상태");

    private final Integer id;
    private final String title;
    private final String description;

}
