package com.example.validation.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class NotEmptyValidationTest {

    private Validator validator = null;

    @BeforeEach
    public void setupValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void name이_비어있지_않는경우_제약조건은_존재하지_않는다() {
        // given
        UserNotEmpty user = new UserNotEmpty("JuHyun");

        // when
        Set<ConstraintViolation<UserNotEmpty>> violations = validator.validate(user);

        // then
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    public void name이_비어있는경우_제약조건이_존재한다() {
        // given
        UserNotEmpty user = new UserNotEmpty("");

        // when
        Set<ConstraintViolation<UserNotEmpty>> violations = validator.validate(user);

        // then
        violations.forEach(i -> System.out.println(i.getMessage()));
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void name이_null인경우_제약조건이_존재한다() {
        // given
        UserNotEmpty user = new UserNotEmpty(null);

        // when
        Set<ConstraintViolation<UserNotEmpty>> violations = validator.validate(user);

        // then
        violations.forEach(i -> System.out.println(i.getMessage()));
        assertThat(violations.size()).isEqualTo(1);
    }


}
