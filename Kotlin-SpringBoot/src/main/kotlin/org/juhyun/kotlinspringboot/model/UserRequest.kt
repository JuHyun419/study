package org.juhyun.kotlinspringboot.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
// @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class) deprecated
data class UserRequest(
        var name: String? = null,
        var age: Int? = null,
        var email: String? = null,
        var address: String? = null,
        var phoneNumber: String? = null
)
