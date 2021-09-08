package com.example.validation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
@AllArgsConstructor
public class UserNotBlank {

    @NotBlank(message = "name must not be blank")
    private final String name;
}
