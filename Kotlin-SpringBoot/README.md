# Kotlin + Spring Boot

### ?(Null Safe), ?.(Safe Call)

```kotlin
/* ?(Null Safe) */
var name: String? = null // null 허용
//var memo: String = null   null 불가능, 컴파일 에러
var memo: String = "Memo" // String 생략 가능(타입 추론) var memo = "Test"

/* ?.(Safe Call) */
println(name?.length) // null
println(memo.length)  // "Memo"
```
<br>

### val(Immutable) vs var(Mutable)
- val: 초기값 할당 후 변경 불가능(Java final)
- var: 초기값 할당 후 변경 가능

<br>

### MutableList, List
- 읽기 전용 변수(val), 수정 가능 변수(var)
- 리스트도 읽기 전용 리스트(List), 수정 가능한 리스트(MutableList)
- 읽기 전용 리스트(List)는 선언할 때 넣은 데이터를 수정, 삭제, 변경이 불가능
- MutableList는 기존 자바의 ArrayList의 함수들을 모두 사용할 수 있음

```kotlin
fun main() {

    val stringList = listOf("bezkoder.com", "programming", "tutorial")
    // [bezkoder.com, programming, tutorial]

    val anyList1 = listOf("bezkoder.com", 2019)
    // [bezkoder.com, 2019]

    val anyList2 = listOf("bezkoder.com", 2019, null)
    // [bezkoder.com, 2019, null]

    val anyList3 = listOfNotNull("bezkoder.com", 2019, null)
    // [bezkoder.com, 2019]
    
    val mutableList1: MutableList<Int> = mutableListOf(10, 20, 11, 23, 55)
    val mutableList2 = mutableListOf(10, 20, 30);
    val mutableList3 = (1..50).toMutableList()
    val mutableList4 = mutableListOf<Int>()
    val mutableList5 = MutableList<Int>(5) { i -> i } // 0..4로 초기화
}
```

<br>

### apply
- 람다 내부에서 수신 객체 자신을 다시 반환하는 경우에 사용

```kotlin
/* apply 사용 */
val peter = Person().apply {
    // apply 블록에서는 프로퍼티만 사용
    name = "Peter"
    age = 20
}

/* apply 사용하지 않는 방법 */
val peter = Person()
clark.name = "Peter"
clark.age = 20
```

<br>

- apply 사용 예제
```kotlin
/* UserResponse */
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

/* API Controller */
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
```

![image](https://user-images.githubusercontent.com/50076031/131870747-69b64f9e-821b-4509-8785-40ce1b7bc36d.png)

<br><br>

### References
- https://www.bezkoder.com/kotlin-list-mutable-list/
- https://medium.com/@limgyumin/%EC%BD%94%ED%8B%80%EB%A6%B0-%EC%9D%98-apply-with-let-also-run-%EC%9D%80-%EC%96%B8%EC%A0%9C-%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94%EA%B0%80-4a517292df29