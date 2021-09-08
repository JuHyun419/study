package com.example.validation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@ToString
@AllArgsConstructor
public class UserNotEmpty {

    @NotEmpty(message = "name must not be empty")
    private final String name;
}
