package org.juhyun.kotlinspringboot.controller

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class DeleteApiController {

    @DeleteMapping("/delete-mapping")
    fun deleteMapping(
            // @RequestParam의 value와 name은 동일하게 데이터 바인딩 역할
            @RequestParam(value = "name") name: String,
            @RequestParam(name = "age") age: Int
    ): String {
        println("$name $age")
        return "$name $age"
    }

    @DeleteMapping("/name/{name}/age/{age}")
    fun deleteMappingPath(
            @PathVariable name: String,
            @PathVariable age: Int
    ): String {
        println("$name $age")
        return "$name $age"
    }
    
}