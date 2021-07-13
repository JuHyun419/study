# 📝 HTTP(2)(HyperText Transfer Protocol)
[HTTP](https://github.com/JuHyun419/study/blob/master/computer-science/HTTP.md)

<br>

## 목차
- [HTTP 메소드](#HTTP-메소드)
- [HTTP 메소드의 속성](#HTTP-메소드의-속성)
- [HTTP 상태코드](#HTTP-상태코드)

<br>

## HTTP 메소드
- HTTP는 **요청 메소드**를 정의하여 주어진 리소스에 수행하길 원하는 행동을 나타냄
- 요청 메소드를 **HTTP 동사** 라고 부르기도 함

### GET

```html
GET /search?q=heello&hl=ko HTTP/1.1
Host: www.naver.com
```

- 리소스 조회
- 서버에 전달할 데이터는 Query를 통해 전달(쿼리 스트링)
- Body를 통해 데이터를 전달할 수도 있음(권장 X)

### POST

```html
POST /members HTTP/1.1
Content-Type: application/json

{
  "user": "hello",
  "age": 10
}
```

- 특정 리소스에 엔티티를 제출할 때 사용
- 메시지 바디를 통해 서버로 요청 데이터 전달
- 서버는 클라이언트가 요청한 데이터를 처리함

### PUT
- 목적 리소스 모든 현재 표시를 요청 payload로 바꿈
  - 리소스가 있으면 대체, 없으면 생성
  - 즉 덮어버린다는 의미

### PATCH
- 리소스의 부분 수정

### DELETE
- 특정 리소스 삭제

### 기타 CONNECT, OPTIONS, TRACE 등등

<br>

## HTTP 메소드의 속성
- 안전(Safe Methods)
  - 호출해도 리소스를 변경하지 않음
- 멱등(Idempotent Methods)
  - 1번, 2번, 10번, 100번 호출하든 항상 결과는 똑같음
  - 멱등 메소드
    - GET: 몇 번을 조회하든 결과는 똑같음
    - PUT: 결과를 대체하기 때문에 같은 요청을 여러번 해도 최종 결과는 같음
    - DELETE: 결과 삭제, 같은 요청을 여러번 해도 삭제 결과는 같음
    - **POST**: 멱등 아님!! => 예를들어 결제의 경우, 여러 번 호출하면 결제가 중복해서 발생할 수 있음
    - 멱등은 외부 요인으로 인한 리소스가 변경되는 것은 고려하지 않음

- 캐시 가능(Cacheable Methods)
  - 응답 결과 리소스를 캐싱해서 사용해도 되는 메소드
  - GET, HEAD, POST, PATCH 캐시 가능
  - 하지만 실무에서는 GET이나 HEAD 정도만 캐시를 사용함

![image](https://user-images.githubusercontent.com/50076031/125431613-3170126d-e728-43b5-99f4-e9f9a0c1f417.png)

https://girawhale.tistory.com/66

<br>

## HTTP 상태코드
- 클라이언트가 HTTP 리퀘스트를 보낸 요청 결과를 서버가 정상적으로 처리되었는지 혹은 에러가 발생했는지 알려주는 것
- 1xx(Informational): 요청이 수신되어 처리중인 상태(거의 사용하지 않음)
- 2xx(Successful): 요청 정상 처리
- 3xx(Redirection): 요청을 완료하려면 추가 행동이 필요함(리다이렉션)
- 4xx(Client Error): 클라이언트 오류, 잘못된 문법 등으로 서버가 요청을 수행할 수 없음
- 5xx(Server Error): 서버 오류, 서버가 정상적으로 요청을 처리하지 못함

### 2xx 성공
- 클라이언트의 요청을 성공적으로 처리

- 200 OK - 요청 성공
- 201 Created - 요청 성공해서 새로운 리소스가 생성됨
- 202 Accepted - 요청이 접수되었으나 처리가 완료되지 않음
  - Batch 처리 등에서 사용
- 204 No Content - 서버가 요청을 성공적으로 수행했으나 응답 페이로드 본문에 보낼 데이터가 없음

### 3xx 리다이렉션

```html
리다이렉션이란 ?
웹 브라우저는 3xx 응답의 결과에 Location 헤더가 존재하면, Location 위치로 자동 이동하도록 하는 것을 의미함(리다이렉트)
```

![image](https://user-images.githubusercontent.com/50076031/125438012-2d0fdaf0-75b6-4ed4-b0ab-31b70e8a0383.png)


- 요청을 완료하기 위해 유저 에이저늩의 추가 조치가 필요함
- 301, 308 - 영구 리다이렉션
  - 특정 리소스의 URI가 영구적으로 이동
- 302, 307, 303 - 일시 리다이렉션
  - 주문 완료 후 주문 내역 화면으로 이동
  - 리소스의 URI가 일시적으로 변경
- 304 - Not Modified
  - [HTTP 캐시](https://developer.mozilla.org/ko/docs/Web/HTTP/Caching)를 목적으로 사용함

### 4xx 클라이언트 오류
- 클라이언트의 원인으로 에러가 발생한 경우
- 400 Bad Request
  - 클라이언트의 잘못된 요청으로 서버가 요청을 처리할 수 없음
  - 요청 구문, 메시지 오류 등
  - 요청 파라미터 및 API 스펙이 다를 때
- 401 Unauthorized
  - 클라이언트가 해당 리소스에 대한 인증이 필요함
  - 401 오류 발생 시 응답에 WWW-Authenticate 헤더와 함께 인증 방법을 설명해야 함
    - 인증(Authentication): 본인 확인
    - 인가(Authorization): 권한
- 403 Forbidden
  - 서버가 요청을 이해했으나 승인 거부
  - 보통 인증 자격은 있으나 접근 권한이 불충분한 경우
    - ex) USER의 역할을 가진 사용자가 ADMIN 권한에 접근했을 때
- 404 Not Found
  - 요청 리소스를 찾을 수 없음
  - 클라이언트가 요청한 리소스가 서버에 없는 경우

### 5xx 서버 오류
- 서버 문제로 인한 오류 발생
- 4xx 오류와는 다르게 재시도시 성공 할 가능성이 존재함
- 500 Internal Server Error
  - 서버에서 리퀘스트를 처리하는 도중에 에러 발생
- 503 Service Unavaliable
  - 일시적으로 서버가 과부하 상태이거나 점검중이기 때문에 현재 리퀘스트를 처리할 수 없는 경우


<br><br>

### References
- https://developer.mozilla.org/ko/docs/Web/HTTP/Methods
- https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC/dashboard
