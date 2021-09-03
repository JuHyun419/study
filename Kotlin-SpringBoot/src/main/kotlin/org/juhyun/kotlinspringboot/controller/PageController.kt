package org.juhyun.kotlinspringboot.controller

import org.juhyun.kotlinspringboot.model.UserRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class PageController {

    @GetMapping("/main")
    fun main(): String {
        println("init main")
        return "main.html"
    }

    @ResponseBody
    @GetMapping("/body")
    fun response(): UserRequest {
        return UserRequest().apply {
            this.name = "JuHyun"
            this.age = 15
        }
    }
}