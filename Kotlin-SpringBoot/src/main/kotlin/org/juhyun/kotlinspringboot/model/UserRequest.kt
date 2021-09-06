package org.juhyun.kotlinspringboot.model

import org.juhyun.kotlinspringboot.annotation.StringFormatDateTime
import javax.validation.constraints.*

//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
// @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class) deprecated
data class UserRequest(

        @field: NotEmpty
        @field: Size(min = 2, max = 10)
        var name: String? = null,

        @field: PositiveOrZero // 0보다 큰 양수
        var age: Int? = null,

        @field: Email
        var email: String? = null,

        @field: NotBlank
        var address: String? = null,

        @field: Pattern(regexp = "\\d{3}-\\d{3,4}-\\d{4}\$")
        var phoneNumber: String? = null,

        @field:StringFormatDateTime(pattern = "yyyy-MM-dd HH:mm:ss", message = "패턴이 올바르지 않습니다.")
        var createdAt: String? = null // yyyy-MM-dd HH:mm:ss
)

    /* StringFormatDateTime, Valid로 대체 */
//    @AssertTrue(message = "생성일자의 패턴은 yyyy-MM-dd HH:mm:ss 여야 합니다.")
//    private fun isValidCreatedAt(): Boolean {
//        try {
//            LocalDateTime.parse(this.createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
//            return true
//        } catch (e: Exception) {
//            return false
//        }
//    }
