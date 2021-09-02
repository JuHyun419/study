package org.juhyun.kotlinspringboot.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

/*
API Response Code
{
    "result": {
        "result_code": "OK",
        "result_message": "성공"
    },
    "description": "응답 설명~~",
    "user": [
        {
            "name": "JuHyun",
            "age": 20,
            "email": "a@a.com",
            "address": "~~~",
            "phone_number": "010-1111-2222"
        },
        {
            "name": "JuBal",
            "age": 25,
            "email": "b@b.com",
            "address": "~~~",
            "phone_number": "010-2222-3333"
         }
    ]
}
*/

data class UserResponse(
        var result: Result? = null,
        var description: String? = null,

        @JsonProperty("user")
        var userRequest: MutableList<UserRequest>? = mutableListOf()
)


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Result(
        var resultCode: String? = null,
        var resultMessage: String? = null
)
