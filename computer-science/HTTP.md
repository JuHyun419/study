# 📝 HTTP(HyperText Transfer Protocol)

## 목차
- [HTTP 정의](#HTTP-정의)
- [HTTP 동작](#HTTP-동작)
- [모든것이 HTTP](#모든것이-HTTP)
- [HTTP 탄생 배경](#HTTP-탄생-배경)
- [HTTP 역사](#HTTP-역사)
- [HTTP 특징](#HTTP-특징)


## HTTP 정의
- HTML과 같은 하이퍼미디어 문서를 전송하기 위한 애플리케이션 계층 프로토콜
- 클라이언트에서 서버까지 일련의 흐름을 결정하고 있는 것이 웹에서 HTTP라고 불리는 프로토콜
    - 프로토콜: "약속", 웹은 HTTP라는 약속을 사용한 통신으로 이루어져있음
- 즉, HTTP는 **인터넷에서 데이터를 주고받을 수 있는 프로토콜**

<br>

## HTTP 동작
- 클라이언트(사용자)가 브라우저 주소 입력란에 URL을 입력하여 서버에게 **요청(Request)** 
- 서버는 클라이언트의 요청에 맞는 결과를 찾아서 사용자에게 **응답(Response)** 을 통해 웹페이지가 화면에 표시됨

![image](https://user-images.githubusercontent.com/50076031/124417544-57086b80-dd94-11eb-9e92-7e189b6ce591.png)

<br>

## 모든것이 HTTP
- HTTP 메시지에 모든 것을 전송
  - HTML, TEXT, IMAGE, 파일
  - JSON, XML(API)
- 거의 모든 형태의 데이터 전송이 가능
- 서버간에 데이터를 주고 받을때도 대부분 HTTP를 사용

<br>

## HTTP 탄생 배경
- 1989년 3월, 팀 버너스 리 박사에 의해 처음 설계 됨
- 팀 버너스 리 박사는, 멀리 떨어져있는 동료 연구자와 지식을 공용하게 할 수 있도록 시스템을 고안
- 최초 고안한 것은 여러 문서를 상호간에 관련 짓는 하이퍼텍스트(HyperText)에 의해 상호간에 참조할 수 있는 
  [WWW(World Wide Web)](https://en.wikipedia.org/wiki/World_Wide_Web) 의 기본 개념이 되는 것
- 이런 WWW를 구성하는 기술로서 다음과 같은 세 가지가 제안됨
  - HTML(HyperText Markup Language): 문서 기술 언어
  - HTTP(HyperText Transfer Protocol): 문서 전송 프로토콜
  - URL(Uniform Resource Locator): 문서의 주소를 지정하는 방법
- WWW는 지금으로 말하자면 웹 브라우저, 단순히 웹(Web)이라고 불림

<br>

## HTTP 역사

### HTTP/0.9 (1991년)
- GET 메서드만 지원
- HTTP 헤더 X
- HTTP가 정식 사양서는 아니었음

### HTTP/1.0 (1996년)
- HTTP가 정식 사양으로 공개
- 이때, HTTP/1.0 으로 [RFC1945](https://blog.edit.kr/entry/%ED%8E%8CRFC-1945-Korean-EditionHTTP10-Korean-Version-30) 가 발행
- 초기 사양이지만 현재에도 아직 많은 서버상에서 현역으로 가동되고 있는 프로토콜 사양
- 메서드, HTTP 헤더 추가

```html
RFC(Request For Comments)
국제 인터넷 표준화 기구(IETF)에서 인터넷에서 기술을 구현하는데 
필요한 상세 절차와 기본 틀을 제공하는 기술 관련 문서를 의미함
```

### **HTTP/1.1 (1997년)**
- 현재 인터넷에서 가장 많이 사용되는 버전
- RFC2068 (1997) -> RFC2616 (1999) -> RFC7230~7235 (2014) 등의 순으로 발행

### HTTP/2 (2015년)
- 성능 개선
- [what-is-http2](https://kinsta.com/learn/what-is-http2/)

### HTTP/3 (진행중)
- 성능 개선
- [TCP 대신 UDP 사용](https://evan-moon.github.io/2019/10/08/what-is-http3/)

<br>

## HTTP 특징
- 클라이언트 서버 구조
- 무상태(Stateless) 프로토콜
- 비연결성(Connectionless)
- 클라이언트와 서버 구조의 HTTP 메시지
- TCP/IP를 이용하는 응용 프로토콜

### 클라이언트 서버 구조
- Request(요청), Response(응답) 구조
- 클라이언트는 서버에 요청을 보내고, 응답을 대기
- 서버는 클라이언트의 요청에 대한 결과를 만들어서 응답

![image](https://user-images.githubusercontent.com/50076031/124419440-70abb200-dd98-11eb-98fe-a3bdcfd3c34d.png)

### 무상태(Stateless) 프로토콜
- 서버가 클라이언트의 상태를 보존하지 않음
- 장점: 서버의 확장성이 높음(Scale Out)
- 단점: 상태를 보존하지 않기 때문에 클라이언트가 추가로 데이터를 전송해야 함
- 상태유지(Stateful)와 무상태(Stateless)의 간단 예시

```html
고객(클라이언트), 점원(서버)

** 상태유지 - Stateful **
고객: 이 노트북 얼마?
점원: 100만원
고객: 2개 구매
점원: 200만원, 신용카드 & 현금 어떤거로 구매?
고객: 신용카드
점원: 200만원 결제 완료

점원이 고객의 모든 상태(정보)를 유지하고 있음 -> 노트북, 가격
따라서 고객이 모든 데이터를 전송하지 않고, 필요한 데이터를 그때그때 전송해도
상태를 유지하고 있기 때문에 점원은 알고있음


** 상태유지 - Stateful(중간에 점원이 바뀌면?) **
고객: 이 노트북 얼마?
점원A: 100만원
고객: 2개 구매
점원B: ? 무엇을 2개 구매?
고객: 신용카드
점원C: ? 무엇을 몇개 신용카드로 구매?



** 무상태 - Stateless **
고객: 이 노트북 얼마?
점원: 100만원
고객: 노트북 2개 구매
점원: 노트북 2개는 200만원, 신용카드 & 현금 어떤거로 구매?
고객: 노트북 2개 신용카드로 구매
점원: 200만원 결제 완료



** 무상태 - Stateless(점원이 중간에 바뀌면?) **
고객: 이 노트북 얼마?
점원A: 100만원
고객: 노트북 2개 구매
점원B: 노트북 2개는 200만원, 신용카드 & 현금 어떤거로 구매?
고객: 노트북 2개 신용카드로 구매
점원C: 200만원 결제 완료



** 상태유지, 무상태 차이 **
- 상태유지(Stateful): 중간에 다른 점원으로 바뀌면 이전의 정보를 모르기 때문에 안됨
- 무상태(Stateless): 중간에 다른 점원으로 바뀌어도 됨
  -> 갑자기 고객이 증가해도 점원을 대거 투입이 가능
  -> 갑자기 클라이언트의 요청(트래픽)이 증가해도 서버를 대거 투입(스케일아웃)이 가능

```

### 비연결성(Connectionless)
- HTTP는 기본적으로 연결을 유지하지 않는 모델
- 일반적으로 초 단위 이하의 빠른 속도로 응답
- 서버 자원을 효율적으로 사용할 수 있음

연결을 유지하는 모델

![image](https://user-images.githubusercontent.com/50076031/124420922-6ccd5f00-dd9b-11eb-9474-5d06041be445.png)

연결을 유지하지 않는 모델

![image](https://user-images.githubusercontent.com/50076031/124420984-866ea680-dd9b-11eb-8f03-d99ea6b2a0dd.png)

#### 비연결성의 한계와 극복
- 클라이언트의 요청에 따라 TCP/IP 연결을 새로 맺어야 함
  - [TCP/IP 3-way Handshake](https://github.com/JuHyun419/study/blob/master/computer-science/TCP-%EC%97%B0%EA%B2%B0%EC%84%A4%EC%A0%95%26%ED%95%B4%EC%A0%9C.md) 시간 추가
  - 웹 브라우저로 사이트를 요청하면 HTML, CSS, JS, 이미지 등 수많은 자원이 함께 다운로드 되어 비효율
  - 지금은 **HTTP 지속 연결(Persistent Connections)** 로 문제 해결
    - [`Keep Alive 기능`](https://goodgid.github.io/HTTP-Keep-Alive/)
  - HTTP/2, HTTP/3에서 더 많은 최적화
  
HTTP 초기 - 연결 & 종료 낭비

![image](https://user-images.githubusercontent.com/50076031/124421201-e1a09900-dd9b-11eb-9f41-089d8f09ce5e.png)

[HTTP 지속 연결(Persistent Connections)](https://brunch.co.kr/@sangjinkang/4)

![image](https://user-images.githubusercontent.com/50076031/124421205-e5ccb680-dd9b-11eb-8013-e98ba34ca537.png)

<br><br>

### References
  - https://developer.mozilla.org/en-US/docs/Web/HTTP
  - https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC/dashboard
  - http://www.yes24.com/Product/Goods/15894097Z
  - https://ahndrenaline.tistory.com/entry/RFC-%EB%AC%B8%EC%84%9C-1
