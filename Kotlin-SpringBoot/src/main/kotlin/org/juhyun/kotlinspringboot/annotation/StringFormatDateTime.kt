package org.juhyun.kotlinspringboot.annotation

import org.juhyun.kotlinspringboot.valid.StringFormatDateTimeValid
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [StringFormatDateTimeValid::class])
@Target(
        AnnotationTarget.FIELD,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_GETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class StringFormatDateTime(
        val pattern: String = "yyyy-MM-dd HH:mm:ss",
        val message: String = "시간 형식이 유효하지 않습니다",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)