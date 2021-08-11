package com.example.fastcampussecretjpa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
public class UserLogin {

    @NotBlank(message = "이메일 항목은 필수입니다.")
    private String email;

    @NotBlank(message = "비밀번호 항목은 필수입니다.")
    private String password;
}
