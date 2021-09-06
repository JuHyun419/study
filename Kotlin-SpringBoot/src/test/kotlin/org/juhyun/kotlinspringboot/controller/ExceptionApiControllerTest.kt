package org.juhyun.kotlinspringboot.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.juhyun.kotlinspringboot.model.UserRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.util.LinkedMultiValueMap

@WebMvcTest // 글로벌 예외 처리는 포함 X
@AutoConfigureMockMvc
class ExceptionApiControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun helloTest() {
        val uri: String = "/api/exception/hello"
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string("Hello"))
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun getTest() {
        val uri = "/api/exception"
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "JuHyun")
        queryParams.add("age", "20")

        mockMvc.perform(MockMvcRequestBuilders.get(uri).queryParams(queryParams))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string("JuHyun 20"))
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun 이름은_2글자에서_6글자_사이여야한다() {
        val uri = "/api/exception"
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "JuHyunJuHyun")
        queryParams.add("age", "11")

        mockMvc.perform(MockMvcRequestBuilders.get(uri).queryParams(queryParams))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun 나이는_10보다_커야한다() {
        var uri = "/api/exception"
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "JuHyun")
        queryParams.add("age", "9")

        mockMvc.perform(MockMvcRequestBuilders.get(uri).queryParams(queryParams))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andExpect(MockMvcResultMatchers.jsonPath("\$.result_code").value("FAIL"))
                .andExpect(MockMvcResultMatchers.jsonPath("\$.errors[0].field").value("age"))
                .andExpect(MockMvcResultMatchers.jsonPath("\$.errors[0].value").value("9"))
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun postTest() {
        val userRequest = UserRequest().apply{
            this.name = "JuHyun"
            this.age = 20
            this.phoneNumber = "010-1111-2222"
            this.address = "서울"
            this.email = "a@a.com"
            this.createdAt = "2021-09-06 21:11:12"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)
        println(json)

        val uri = "/api/exception"

        mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("\$.name").value("JuHyun"))
                .andExpect(MockMvcResultMatchers.jsonPath("\$.age").value("20"))
                .andExpect(MockMvcResultMatchers.jsonPath("\$.address").value("서울"))
                .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun 나이는_0보다_커야한다() {
        val userRequest = UserRequest().apply{
            this.name = "JuHyun"
            this.age = -1
            this.phoneNumber = "010-1111-2222"
            this.address = "서울"
            this.email = "a@a.com"
            this.createdAt = "2021-09-06 21:11:12"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)
        println(json)

        val uri = "/api/exception"

        mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andDo(MockMvcResultHandlers.print())
    }

}