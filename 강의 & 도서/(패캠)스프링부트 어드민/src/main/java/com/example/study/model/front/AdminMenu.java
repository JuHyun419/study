package com.example.study.model.front;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AdminMenu {

    private String title;
    private String url;
    private String code;

}
