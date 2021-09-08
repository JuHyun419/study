package com.example.validation.model;

import org.hibernate.validator.internal.constraintvalidators.bv.NotBlankValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class NotBlankValidationTest {

    private Validator validator = null;
    NotBlankValidator notBlankValidation = null;

    @BeforeEach
    public void setupValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void name이_notBlank일경우_제약조건은_존재하지_않는다() {
        // given
        UserNotBlank user = new UserNotBlank("JuHyun");

        // when
        Set<ConstraintViolation<UserNotBlank>> violations = validator.validate(user);

        // then
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    public void name이_blank일경우_제약조건이_존재한다() {
        // given
        UserNotBlank user = new UserNotBlank(" ");

        // when
        Set<ConstraintViolation<UserNotBlank>> violations = validator.validate(user);

        // then
        violations.forEach(i -> System.out.println(i.getMessage()));
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void name이_비어있는경우_제약조건이_존재한다() {
        // given
        UserNotBlank user = new UserNotBlank("");

        // when
        Set<ConstraintViolation<UserNotBlank>> violations = validator.validate(user);

        // then
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void name이_null일경우_제약조건이_존재한다() {
        // given
        UserNotBlank user = new UserNotBlank(null);

        // when
        Set<ConstraintViolation<UserNotBlank>> violations = validator.validate(user);

        // then
        assertThat(violations.size()).isEqualTo(1);
    }

}
