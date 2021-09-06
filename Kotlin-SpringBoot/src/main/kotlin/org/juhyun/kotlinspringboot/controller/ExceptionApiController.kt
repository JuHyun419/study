package org.juhyun.kotlinspringboot.controller

import org.juhyun.kotlinspringboot.model.UserRequest
import org.juhyun.kotlinspringboot.model.exception.Error
import org.juhyun.kotlinspringboot.model.exception.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api/exception")
@Validated
class ExceptionApiController {

    @GetMapping("/hello")
    fun hello() {
        val list = mutableListOf<String?>()
        println(list[0])
    }

    /* ErrorResponse API
{
    "result_code": "FAIL",
    "http_status": "400",
    "http_method": "GET",
    "message": "요청에 에러가 발생하였습니다.",
    "path": "/api/exception",
    "timestamp": "2021-09-06T16:03:43.556134",
    "errors":[
        {
        "field": "name",
        "message": "크기가 2에서 6 사이여야 합니다",
        "value": "LeeJuHyun"
        }
    ]
}
 */

    @GetMapping
    fun get(
            @NotBlank @Size(min = 2, max = 6) @RequestParam name: String,
            @Min(10) @RequestParam age: Int): String {
        println(name)
        println(age)
        return "$name $age"
    }

    /* Controller 내부에서만 발생하는 예외 처리 */
    @ExceptionHandler(IndexOutOfBoundsException::class)
    fun indexOutOfBoundsException(e: IndexOutOfBoundsException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error")
    }

    @PostMapping
    fun post(@Valid @RequestBody userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest
    }

}