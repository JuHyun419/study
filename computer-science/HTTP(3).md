# 📝 HTTP(HyperText Transfer Protocol)
[`HTTP`](https://github.com/JuHyun419/study/blob/master/computer-science/HTTP.md)

[`HTTP(2)`](https://github.com/JuHyun419/study/blob/master/computer-science/HTTP(2).md)


## [HTTP Header](https://developer.mozilla.org/ko/docs/Web/HTTP/Headers)
- HTTP 전송에 필요한 모든 부가정보
    - ex) 메시지 바디의 내용, 크기, 압축, 인증, 서버정보, 캐시 등등

```html
헤더 필드 명: 필드 값

== Request ==
GET /search?q=juhyun&hl=ko HTTP/1.1
<!-- Host: 필드 명, www.google.com: 필드 값 -->
Host: www.google.com 


== Response ==
HTTP/1.1 200 OK
Content-Type: text/html;charset=UTF-8 // HTTP 헤더
Content-Length: 3423 // HTTP 헤더
<html>
<body>...</body>
</html>
```

### 4종류의 HTTP 헤더 필드
- HTTP 헤더 필드는 용도에 따라 4종류로 분류됨

#### [일반적 헤더 필드(General Header Fields)](https://developer.mozilla.org/ko/docs/Glossary/General_header)
- 리퀘스트 메시지와 리스폰스 메시지 둘 다 사용되는 헤더

#### [리퀘스트 헤더 필드(Request Header Fields)](https://developer.mozilla.org/ko/docs/Glossary/Request_header)
- 클라이언트에서 서버로 송싱된 리퀘스트 메시지에 사용되는 헤더
- 리퀘스트의 부가적 정보, 클라이언트 정보, 리스폰스의 콘텐츠에 관한 우선 순위 등을 부가

#### [리스폰스 헤더 필드(Response Header Fields)](https://developer.mozilla.org/ko/docs/Glossary/Response_header)
- 서버에서 클라이언트로 송신한 리스폰스 메시지에 사용되는 헤더
- 리스폰스의 정보와 서버의 정보, 클라이언트의 추가 정보 요구 등을 부가함

#### [엔티티 헤더 필드(Entity Header Fields)](https://developer.mozilla.org/ko/docs/Glossary/Entity_header)
- 리퀘스트 메시지와 리스폰스 메시지에 포함된 엔티티에 사용되는 헤더
- 콘텐츠 갱신 시간 등의 엔티티에 관한 정보를 부가


<details>
  <summary>HTTP/1.1 헤더 필드 살펴보기(RFC2616)</summary>

#### 일반적 헤더 필드(General Header Fields)
|헤더 필드 명|설명|
|----|-------------|
|[Cache-Control](https://developer.mozilla.org/ko/docs/Web/HTTP/Headers/Cache-Control)|캐싱 동작 지정|
|Connection|Hop-by-hop 헤더, 커넥션 관리|
|Date|메시지 생성 날짜|
|Pragma|메시지 제어|
|Trailer|메시지의 끝에 있는 헤더의 알람|
|Transfer-Encoding|메시지 바디의 전송 코딩 형식 지정|
|Upgrade|다른 프로토콜에 업그레이드|
|Via|프록시 서버에 관한 정보|
|Warning|에러 통지|

<br>

#### 리퀘스트 헤더 필드(Request Header Fields)
|헤더 필드 명|설명|
|----|-------------|
|Accept|유저 에이전트가 처리 가능한 미디어 타입|
|Accept-Charset|문자셋 우선 순위|
|Accept-Encoding|콘텐츠 인코딩 우선 순위|
|Accept-Language|언어(자연어) 우선 순위|
|Authorization|웹 인증을 위한 정보|
|Expect|서버에 대한 특정 동작의 기대|
|From|유저의 메일 주소|
|Host|요구된 리소스의 호스트|
|If-Match|엔티티 태그의 비교(캐싱)|
|If-None-Match|엔티티 태그의 비교(If-Match 반대, 캐싱)|
|If-Modified-Since|리소스의 갱신 시간 비교(캐싱)|
|If-Unmodified-Since|리소스의 갱신 시간 비교(If-Modified-Since 반대, 캐싱)|
|If-Range|리소스가 갱신되지 않은 경우 엔티티 바이트 범위의 요구를 송신|
|Max-Forwards|최대 전송 홉 수|
|Range|엔티티 바이트 범위 요구|
|[Referer](https://developer.mozilla.org/ko/docs/Web/HTTP/Headers/Referer)|현재 요청된 페이지의 링크 이전의 웹 페이지 주소<br>ex) Referer: https://developer.mozilla.org/en-US/docs/Web/JavaScript|
|TE|전송 인코딩의 우선 순위|
|User-Agent|HTTP 클라이언트의 정보|

<br>

#### 리스폰스 헤더 필드(Response Header Fields)
|헤더 필드 명|설명|
|----|-------------|
|Accept-Ranges|바이트 단위의 요구를 수신할 수 있는지 없는지 여부|
|Age|리소스의 지정 경과 시간|
|[ETag](https://developer.mozilla.org/ko/docs/Web/HTTP/Headers/ETag)|리소스의 버전을 식별하는 고유한 문자열 검사기|
|[Location](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Location)|페이지를 리다이렉션할 URL을 나타냄|
|Proxy-Authenticate|프록시 서버의 클라이언트 인증을 위한 정보|
|Retry-After|리퀘스트 재시행의 타이밍 요구|
|Server|HTTP 서버 정보|
|Vary|프록시 서버에 대한 캐시 관리 정보|
|WWW-Authenticate|서버의 클라이언트 인증을 위한 정보|

<br>

#### 엔티티 헤더 필드(Entity Header Fields)
|헤더 필드 명|설명|
|----|-------------|
|Allow|리소스가 제공하는 HTTP 메소드|
|Content-Encoding|엔티티 바디에 적용되는 콘텐츠 인코딩|
|Content-Language|엔티티의 자연어|
|Content-Length|엔티티 바디의 사이즈(단위: 바이트)|
|Content-Location|리소스에 대응하는 대체 URI|
|Content-MD5|엔티티 바디의 메시지 다이제스트|
|Content-Range|엔티티 바디의 범위 위치|
|[Content-Type](https://developer.mozilla.org/ko/docs/Web/HTTP/Headers/Content-Type)|엔티티 바디의 미디어 타입|
|Expires|엔티티 바디의 유효기간 날짜|
|[Last-Modified](https://developer.mozilla.org/ko/docs/Web/HTTP/Headers/Last-Modified)|리소스의 최종 갱신 날짜|

http://www.yes24.com/Product/Goods/15894097
</details>

<br>

#### [콘텐츠 협상](https://developer.mozilla.org/en-US/docs/Web/HTTP/Content_negotiation)
- 클라이언트가 선호하는 표현 요청
- Accept: 클라이언트가 선호하는 미디어 타입 전달
- Accept-Charset: 클라이언트가 선호하는 문자 인코딩
- Accept-Encoding: 클라이언트가 선호하는 압축 인코딩
- Accept-Language: 클라이언트가 선호하는 자연 언어

- 협상과 우선순위 (1)
  - Quality Values(q)값 사용
  - 0 ~ 1, 클수록 높은 우선 순위
  
- 협상과 우선순위 (2)
  - 구체적인 것이 우선 순위
- Google에서 hello를 검색했을 때 Network 결과  

  <img width="630" alt="캡쳐 22" src="https://user-images.githubusercontent.com/50076031/103978159-3208b680-51be-11eb-8079-63cc65eb8ec5.PNG">

<br>

#### [쿠키(Cookie)](https://developer.mozilla.org/ko/docs/Web/HTTP/Headers/Cookie)
- [Set-Cookie](https://developer.mozilla.org/ko/docs/Web/HTTP/Headers/Set-Cookie): 서버에서 클라이언트로 쿠키 전달(응답)
- Cookie: 클라이언트가 서버로부터 받은 쿠키를 저장하고 HTTP 요청시 서버로 전달
  - 생명주기: Expires, max-age 
  
<br><br>

### References
- https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC#description
- http://www.yes24.com/Product/Goods/15894097
- https://developer.mozilla.org/ko/docs/Web/HTTP/Headers