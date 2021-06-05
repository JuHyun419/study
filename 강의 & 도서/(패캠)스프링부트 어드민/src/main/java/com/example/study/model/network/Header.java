package com.example.study.model.network;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {

    // API 통신 시간
    // camelCase -> snake_case 
    // 계속해서 설정해주기엔 번거러우므로, application.properties 파일에 같이 설정
    @JsonProperty("transaction_time")   
    private LocalDateTime transactionTime;

    // API 응답 코드
    private String resultCode;

    // API 부가 설명
    private String description;

    private T data;

    // OK
    public static <T> Header<T> OK() {
        return Header.<T>builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }

    // DATA OK
    public static <T> Header<T> OK(T data) {
        return Header.<T>builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }

    // ERROR
    // DATA OK
    public static <T> Header<T> ERROR(String description) {
        return Header.<T>builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description(description)
                .build();
    }
}
