package org.juhyun.kotlinspringboot.controller

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api")
@Validated
class ValidApiController {

    @DeleteMapping("/valid/{name}")
    fun valid(@PathVariable
              @Size(min = 2, max = 10, message = "name의 길이는 2 ~ 5") name: String,
              @NotNull(message = "age 값이 누락되었습니다.")
              @Min(value = 20, message = "20보다 커야 합니다.")
              @RequestParam age: Int): String {
        println(age)
        return age.toString()
    }
}