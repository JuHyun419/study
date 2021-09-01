package org.juhyun.kotlinspringboot.controller

import org.juhyun.kotlinspringboot.model.UserRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class GetApiController {

    @GetMapping("/hello")
    fun hello(): String {
        return "hello kotlin"
    }

    @RequestMapping(path = ["/request-mapping"], method = [RequestMethod.GET])
    fun requestMapping(): String {
        return "request-mapping"
    }

    @GetMapping("/path-variable/{name}/{age}")
    fun pathVariable(@PathVariable name: String, @PathVariable age: Int): String {
        println("$name $age")
        return name
    }

    // http://localhost:8080/api/query-param/page?name=juhyun&age=20
    @GetMapping("/query-param")
    fun queryParam(@RequestParam name: String, @RequestParam age: Int): String {
        println("$name $age")
        return name + age
    }

    // 쿼리파라미터로 여러개의 정보를 받아야할때 => 객체로 처리
    @GetMapping("/query-param/object")
    fun queryParamObject(userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest
    }

    @GetMapping("/query-param/map")
    fun queryParamMap(@RequestParam map: Map<String, Any>): Map<String, Any> {
        println(map)
        return map
    }

}