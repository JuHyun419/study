package com.example.fastcampussecretjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseMessageHeader {

    private boolean result;
    private String resultCode;
    private String message;
    private int statusCode;

}
