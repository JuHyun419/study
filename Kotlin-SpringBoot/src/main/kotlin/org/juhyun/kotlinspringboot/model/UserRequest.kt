package org.juhyun.kotlinspringboot.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

// Json 네이밍 전략
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
// @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class) deprecated
data class UserRequest (
        var name:String?=null,
        var age:Int?=null,
        var email:String?=null,
        var address:String?=null,

        @JsonProperty("phone_number")
        var phoneNumber:String?=null // json에서는 phone_number
)
