package org.juhyun.kotlinspringboot.controller

import org.juhyun.kotlinspringboot.model.Result
import org.juhyun.kotlinspringboot.model.UserRequest
import org.juhyun.kotlinspringboot.model.UserResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mapping")
    fun putMapping(): String {
        return "put-mapping"
    }

    @RequestMapping(method = [RequestMethod.PUT], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping - put"
    }

    @PutMapping("/put-mapping/object")
    fun putMappingObject(@RequestBody userRequest: UserRequest): UserResponse {
        /*
        0. UserResponse
        1. result
        2. description
        3. user MutableList
        */

        // 0. Response
        return UserResponse().apply {
            // 1. result
            this.result = Result().apply {
                this.resultCode = "OK"
                this.resultMessage = "성공"
            }
        }.apply {
            // 2. description
            this.description = "응답코드 설명~~~"
        }.apply {
            // 3. user MutableList
            val userList = mutableListOf<UserRequest>()
            userList.add(userRequest)
            userList.add(UserRequest().apply {
                this.name = "a"
                this.age = 15
                this.email = "a@gmail.com"
                this.address = "Seoul"
                this.phoneNumber = "010-1234-1234"
            })
            userList.add(UserRequest().apply {
                this.name = "b"
                this.age = 18
                this.email = "b@gmail.com"
                this.address = "Seoul"
                this.phoneNumber = "010-0000-0000"
            })
            this.userRequest = userList
        }

    }

}