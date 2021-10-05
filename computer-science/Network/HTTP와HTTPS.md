# 📝 HTTP vs HTTPS

## HTTP(Hyper Text Transfer Protocol)
- HTML과 같은 하이퍼미디어 문서를 전송하기 위한 애플리케이션 계층 프로토콜
- 클라이언트에서 서버까지 일련의 흐름을 결정하고 있는 것이 웹에서 HTTP라고 불리는 프로토콜
    - 프로토콜: "약속", 웹은 HTTP라는 약속을 사용한 통신으로 이루어져있음
- 즉, HTTP는 **인터넷에서 데이터를 주고받을 수 있는 프로토콜**
- [HTTP](https://github.com/cobak-study/computer-science/blob/master/Network/%5B%EC%A3%BC%ED%98%84%5DHTTP.md)
- [HTTP2](https://github.com/cobak-study/computer-science/blob/master/Network/%5B%EC%A3%BC%ED%98%84%5DHTTP(2).md)
- [HTTP3](https://github.com/cobak-study/computer-science/blob/master/Network/%5B%EC%A3%BC%ED%98%84%5DHTTP(3).md)

### HTTP의 약점
- 평문(암호화 하지 않은) 통신이기 때문에 도청 가능
- 통신 상대를 확인하지 않기 때문에 위장 가능
  - 위장 웹서버, 위장 클라이언트, 무효한 리퀘스트
- 완전성을 증명할 수 없기 때문에 변조 가능
  - 발신 리퀘스트 & 리스폰스와 수신 리퀘스트 & 리스폰스가 동일한지의 여부

<img width="460" alt="캡쳐1" src="https://user-images.githubusercontent.com/50076031/135942029-14c9a9a5-659e-4d1d-8be6-78b145e08adc.png">

<br>

### 용어
- 암호화: 어떤 정보를 암호화된 정보로 변환
- 복호화: 암호화된 정보를 다시 원래의 정보로 변환하는 것
- 키: 암호화, 복호화 할 때 쓰는 일종의 비밀번호
- 공통 키: 암호화할 때와 복호화할 때 같은 키를 사용하는 방식
- 공개 키(비대칭): 암호화할 때와 복호화할 때 다른 키를 사용하는 비대칭 방식
- 인증기관(CA): 클라이언트가 접속한 서버가 클라이언트가 의도한 서버가 맞는지 보장하는 인증서 역할

## HTTPS(Hyper Text Transfer Protocol Secure Socket Layer)
- SSL(보안 소켓 계층)을 조합한 HTTP로 HTTPS나 HTTP over SSL 이라고 불림
- HTTP + 암호화 + 인증 + 완전성 보호 = HTTPS

<img width="460" alt="캡쳐1" src="https://user-images.githubusercontent.com/50076031/135945743-0f93271a-8a80-475c-9ed4-134f733176b5.png">

- 그 외 HTTPS는 TLS(전송 계층 보안) 프로토콜을 통해서도 보안을 유지
- 2014년부터 구글은 HTTP를 HTTPS로 바꾸라고 권장

> TLS(Transport Layer Secure)
> - 데이터 무결성을 제공하기 때문에 데이터가 전송 중에 수정되거나 손상되는것을 방지
> - 사용자가 자신이 의도하는 웹사이트와 통신하고 있음을 입증하는 인증 기능도 제공
> - [SSL vs TLS](https://ko.gadget-info.com/difference-between-ssl)


### HTTPS는 SSL의 껍질을 덮어쓴 HTTP
- HTTPS는 새로운 애플리케이션 계층의 프로토콜이 아닌 HTTP 통신을 하는 소켓 부분을 SSL이나 TLS이라는 프로토콜로 대체하고 있음
- HTTP는 TCP와 통신을 하지만, SSL을 사용한 경우 HTTP는 SSL와 통신하고 SSL이 TCP와 통신

### 상호간에 키를 교환하는 **공개키 암호화 방식**
- SSL은 공개키 암호화 방식을 사용
- 공개키 암호에서는 비밀키(private key), 공개키(public key)라는 두 개의 키를 사용
- 비밀키: 누구에게도 알려지면 안되는 키
- 공개키: 누구에게나 알려져도 괜찮은 키

![image](https://user-images.githubusercontent.com/50076031/135947079-5d0ea9d3-59b6-40a3-a540-5b71c7da2a9f.png)

### 공개키가 진짜인지 확인하기 위한 방법? => 인증 기관(CA, Certificate Authority)
- 서버의 운영자가 인증 기관에 공개키 제출
- 인증 기관은 제출된 공개키에 디지털 서명을 한 후 서명이 끝난 공개키 생성
- 인증기관의 공개키는 사전에 브라우저에 내장되어 있음
- 서버는 인증 기관에 의해 작성된 공개키 인증서를 클라이언트에 보내고 공개키 암호로 통신
- 증명서를 받은 클라이언트는 증명 기관의 공개키를 사용해서 서버의 공개키가 신뢰할 수 있다는 것을 확인

<img width="720" alt="캡쳐1" src="https://user-images.githubusercontent.com/50076031/135949605-46c72268-1acb-42e8-a99e-dadf412fc4c0.png">

### HTTPS의 보안 외 장점
- AMP(Accelerated Mobile Pages)를 만들고 싶을때도 HTTPS 프로토콜을 사용해야 함

> AMP(Accelerated Mobile Pages)란 모바일 기기에서 훨씬 빠르게 콘텐츠를 로딩하기 위한 방법으로 구글이 만든 오픈소스  
> [나는 AMP를 좋아하지 않는다.](https://blog.outsider.ne.kr/1285?category=34)  
> [How does AMP Speed up a Website](https://getsales.africa/how-does-amp-speed-up-a-website/)  

- 검색엔진 최적화(SEO)에서의 우위를 지님
> Google 자체적으로 HTTPS 웹사이트에 가산점을 부여  
> 사용자들또한 안전하다고 생각되는 HTTPS 사이트를 더 방문하기 때문

<br>

## HTTP vs HTTPS

| | HTTP | HTTPS |
|------|---|---|
|URL|http://|https://|
|Security|Unsecure|Enhanced security|
|Port|80 포트|443 포트|
|OSI Layer|Application Layer|Transport Layer|
|TLS Certificates|X|O|
|Domain Validation|Not required|Domain validation|
|Encryption|X|O|

## 항상 HTTPS를 사용하는 게 좋을까?
- SSL은 처리 속도가 늦어진다는 큰 단점이 존재함
  - 평문 통신에 비해 암호화 통신은 CPU, 메모리 등 리소스가 많이 필요하기 때문
  - 통신마다 암호화를 하면 한 대당 처리할 수 있는 리퀘스트 수가 감소
- 따라서 민감한 정보를 포함하지 않는 통신은 HTTP를, 개인 정보 등의 민감한 정보를 다룰 때는 HTTPS에 의한 암호화 통신을 사용하는 것이 좋음
- 또한 증명서를 구입해야 하는 비용적인 측면도 존재함

### 상황에 따라 적절한 방식을 사용하는 것이 좋을 듯..

<br><br>

### References
- [HTTP VS HTTP 차이, 알면 사이트의 레벨이 보인다](http://blog.wishket.com/http-vs-https-%EC%B0%A8%EC%9D%B4-%EC%95%8C%EB%A9%B4-%EC%82%AC%EC%9D%B4%ED%8A%B8%EC%9D%98-%EB%A0%88%EB%B2%A8%EC%9D%B4-%EB%B3%B4%EC%9D%B8%EB%8B%A4/)
- [Web HTTP와 HTTPS 및 차이점](https://mangkyu.tistory.com/98)
- [그림으로 배우는 Http & Network Basic](http://www.yes24.com/Product/Viewer/Preview/15894097)
- [What Are the Differences Between HTTP and HTTPS?](https://www.venafi.com/blog/what-are-differences-between-http-https-0#:~:text=HTTPS%20is%20HTTP%20with%20encryption,uses%20HTTPS%20has%20HTTPS%3A%2F%2F.)
