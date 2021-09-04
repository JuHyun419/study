package org.juhyun.kotlinspringboot.valid

import org.juhyun.kotlinspringboot.annotation.StringFormatDateTime
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class StringFormatDateTimeValid : ConstraintValidator<StringFormatDateTime, String> {

    private var pattern: String? = null

    override fun initialize(constraintAnnotation: StringFormatDateTime?) {
        this.pattern = constraintAnnotation?.pattern
    }

    // 유효성 검사
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        try {
            LocalDateTime.parse(value, DateTimeFormatter.ofPattern(pattern))
            return true
        } catch (e: Exception) {
            return false
        }
    }

}