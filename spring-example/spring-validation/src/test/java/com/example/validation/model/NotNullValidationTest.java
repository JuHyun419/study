package com.example.validation.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class NotNullValidationTest {

    private Validator validator = null;

    @BeforeEach
    public void setupValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void name이_notNull일경우_제약조건이_존재하지_않는다() {
        // given
        UserNotNull user = new UserNotNull("JuHyun");

        // when
        Set<ConstraintViolation<UserNotNull>> violations = validator.validate(user);

        // then
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    public void name이_Null일경우_제약조건이_존재한다() {
        // given
        UserNotNull user = new UserNotNull(null);

        // when
        Set<ConstraintViolation<UserNotNull>> violations = validator.validate(user);

        // then
        violations.forEach(i -> System.out.println(i.getMessage()));
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void 비어있는_name인경우_제약조건이_존재하지_않는다() {
        // given
        UserNotNull user = new UserNotNull("");

        // when
        Set<ConstraintViolation<UserNotNull>> violations = validator.validate(user);

        // then
        assertThat(violations.size()).isEqualTo(0);
    }

}
