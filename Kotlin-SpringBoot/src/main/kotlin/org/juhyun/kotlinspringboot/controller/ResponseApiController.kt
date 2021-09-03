package org.juhyun.kotlinspringboot.controller

import org.juhyun.kotlinspringboot.model.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/response")
class ResponseApiController {

    @GetMapping
    fun getMapping(@RequestParam age: Int?): ResponseEntity<String>? {

        return age?.let {
            // age != null
            if (age < 20) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age 값은 20보다 커야합니다.");
            }
            return ResponseEntity.status(HttpStatus.OK).body("OK")
        } ?: kotlin.run {
            // age == null
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age 값이 누락되었습니다.")
        }

        /*
        if (age == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age 값이 누락되었습니다.")
        }
        if (age < 20) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age 값은 20보다 커야합니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("OK")

         */
    }

    // 200
    @PostMapping
    fun postMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(userRequest)
    }

    // 201
    @PutMapping
    fun putMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest?>? {
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest)
    }

    // 500
    @DeleteMapping("/{id}")
    fun deleteMapping(@PathVariable id: Int): ResponseEntity<Any?>? {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
    }


}