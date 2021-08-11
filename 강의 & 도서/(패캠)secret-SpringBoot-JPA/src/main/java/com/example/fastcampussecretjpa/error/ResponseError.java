package com.example.fastcampussecretjpa.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.FieldError;

@Data
@Builder
@AllArgsConstructor
public class ResponseError {
    private String field;
    private String message;

    private ResponseError() {
    }

    public static ResponseError of(FieldError e) {
        return ResponseError.builder()
                .field(e.getField())
                .message(e.getDefaultMessage())
                .build();
    }
}
