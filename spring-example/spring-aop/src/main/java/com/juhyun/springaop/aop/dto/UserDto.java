package com.juhyun.springaop.aop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {

    private String id;
    private String password;
    private String email;

}
