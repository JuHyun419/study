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
public class ResponseMessage {

    /*
    {
        "header": {
            result: true | false,
            resultCode: string,
            message: message,
            status: http status code
        },
        "body": body 내용
    }
     */
    private ResponseMessageHeader header;
    private Object body;

}
