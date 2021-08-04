package com.example.fastcampussecretjpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "이메일 형식에 맞게 입력해주세요.")
    @NotBlank(message = "이메일은 필수 항목입니다.")
    private String email;

    @NotBlank(message = "이름은 필수 항목입니다.")
    private String userName;

    @Size(min = 4, message = "비밀번호는 4자 이상 입력해야합니다.")
    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    private String password;

    @Size(max = 20, message = "연락처는 최대 20자까지 입력해야합니다.")
    @NotBlank(message = "연락처는 필수 항목입니다.")
    private String phone;
}
