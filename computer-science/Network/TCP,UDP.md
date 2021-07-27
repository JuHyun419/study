# 📝 TCP, UDP 정리

<img src="https://user-images.githubusercontent.com/50076031/122863777-62a06f00-d35e-11eb-8416-5996d9f2926e.png" width="500" height="500">

## 전송 제어 프로토콜 TCP(Transmission Control Protocol)
  - 아래와 같은 IP 프로토콜의 한계점(비연결성, 비신뢰성 등)을 해결하기 위해 나옴
    - 비연결성 : 패킷을 받을 대상이 없거나 서비스 불능 상태여도 패킷이 전송됨
    - 비신뢰성 : 중간에 패킷이 사라지거나, 패킷이 순서대로 오지 않는 등 신뢰성을 보장하지 못함
      - 신뢰성? 전송되는 데이터 패킷들의 순서, 유실 여부 등을 검사하여 송신 측이 보낸 모든 데이터가 수신 측에 온전히 전달이 될 수 있느냐를 의미함
  - 서버와 클라이언트간에 데이터를 신뢰성있게 전달하기 위해 만들어진 프로토콜
  - 전송계층(Transport, Layer4) 에서 사용되는 프로토콜
    - 전송계층(Transport Layer): 발신지에서 목적지(End-To-End)간 제어와 에러를 관리
    - 패킷의 전송이 유효한지 확인하고 전송에 실패된 패킷을 다시 보내는 것과 같은 신뢰성있는 통신을 보장하며, 헤드에는 세그먼트가 포함된다.
    - 주소 설정, 오류 및 흐름 제어, 다중화를 수행한다.
      - PDU(Protocol Data Unit): 세그먼트(Segment)
      - 프로토콜: TCP, UDP, ARP, RTP
      - 장비: 게이트웨이, L4 스위치

### TCP 예시(전화)
<img src="https://user-images.githubusercontent.com/50076031/122863450-bbbbd300-d35d-11eb-9fdc-25581e7c9f0e.png" width="500" height="500">

https://velog.io/@hidaehyunlee/TCP-%EC%99%80-UDP-%EC%9D%98-%EC%B0%A8%EC%9D%B4


### TCP의 특징
  - 포트 번호를 사용하여 프로세스 간 통신을 제공 
  - 스트림 지향 프로토콜
  - TCP는 데이터의 송수신 프로세스에서 바이트의 흐름으로 데이터를 주고받음
  - 기본적으로 IP계층 및 하위 계층에서의 패킷 손실, 중복, 오류에 의해 발생할 수 있는 모든 데이터 전송 문제를 검출하고 복구함
  - 데이터의 전송 순서를 보장함
  - UDP보다 전송 속도가 느림

<br><br> 
    
## 사용자 데이터 프로토콜 UDP(User Datagram Protocol)
  - TCP와는 반대로 클라이언트와 서버가 비연결, 신뢰성이 없는 전송 프로토콜
  - 전송계층(Transport, Layer4) 에서 사용되는 프로토콜

### UDP 예시(카카오톡)
![image](https://user-images.githubusercontent.com/50076031/122863484-caa28580-d35d-11eb-8123-37bed19ff3e8.png)

https://velog.io/@hidaehyunlee/TCP-%EC%99%80-UDP-%EC%9D%98-%EC%B0%A8%EC%9D%B4

### UDP의 특징
  - TCP와 동일하게 포트 번호를 사용하여 프로세스 간 통신을 제공
  - UDP는 비연결 서비스(connectionless service)를 제공한다.
  - UDP에 의해 보내지는 각 사용자 데이터그램은 독립된 데이터그램이라는 것을 의미함
  - TCP와는 달리 연결 설정이나 연결 종료가 없다.
  - UDP는 매우 단순한 프로토콜로 흐름 제어(flow control)가 따로 없다.
  - UDP에는 오류 제어(error control) 메커니즘이 존재하지 않는다. 
  - 즉, 송신자는 메시지가 손실이 되었는지 또는 중복이 되었는지를 알 수 없음을 의미한다.
  - TCP보다 전송 속도가 빠름
    
<br>

### TCP, UDP 정리

<img src="https://user-images.githubusercontent.com/50076031/122865353-39351280-d361-11eb-86e9-358bb3df285b.png" width="700" height="700">

<br>

### 추가
  - TCP: HTTP/1.1, HTTP/2
  - UDP: HTTP/3 (최신 버전)
  - 현재는 주로 HTTP/1.1 을 사용
  - [`HTTP/3는 왜 UDP를 선택한 것일까?`](https://evan-moon.github.io/2019/10/08/what-is-http3/)
  - [`TCP / UDP 포트 목록`](https://ko.wikipedia.org/wiki/TCP/UDP%EC%9D%98_%ED%8F%AC%ED%8A%B8_%EB%AA%A9%EB%A1%9D)

<br><br>


## References
  - https://velog.io/@hidaehyunlee/TCP-%EC%99%80-UDP-%EC%9D%98-%EC%B0%A8%EC%9D%B4
  - https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC
  - https://evan-moon.github.io/2019/10/08/what-is-http3/
  - https://mangkyu.tistory.com/15
