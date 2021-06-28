## 📎 TCP - 3-way Handshake & 4-way Handshake(작성중)

### 3-way Handshake 역할
  - TCP는 장치들 사이에 논리적인 접속을 성립(establish)하기 위해 3-way Handshake을 사용
  - 클라이언트와 서버 모두 데이터를 전송할 준비가 되었다는 것을 보장하고, 실제 데이터 통신이 이루어지기 전에 한 쪽이 다른 쪽에 준비되었다는 것을 알 수 있도록 함(신뢰성)
  - 클라이언트와 서버 모두 상대편에 대한 초기의 순차 일련번호를 얻을 수 있음

<img width="640" src="https://user-images.githubusercontent.com/50076031/123629329-203fcc00-d84f-11eb-83da-3a2ec7c5a207.png">

<br>

### 3-way Handshake 절차 (클라이언트 <-> 서버)
  - 클라이언트는 서버에 접속을 요청하는 *SYN* 패킷을 보낸다.
    - 클라이언트는 SYN을 보내고 서버로부터 SYN/ACK 응답을 기다리는 *SYN_SENT* 상태가 됨
  - 서버는 클라이언트로부터 SYN요청을 받고 클라이언트에게 요청을 수락한다는 *ACK*와 SYN flag가 설정된 패킷을 발송한 후 클라이언트가 다시 ACK응답을 보내기를 기다림
    - 서버는 SYN/ACK을 보내고 *SYN_RECEIVED* 상태가 됨
  - 클라이언트는 다시 서버에게 ACK을 보낸 후 연결이 이루어지고 데이터의 통신이 이루어짐
    - 이때 서버의 상태는 *ESTABLISHED*가 됨
  - 위와 같이 클라이언트와 서버가 통신을 하기 전 신뢰성있는 연결을 맺어 준다는 것이 TCP의 3-way Handshake

<br>

### 각 용어 설명

```html
* SYN(Synchronization): 상대에 대한 접속 요청

* ACK(Acknowledgement): 상대의 통신 응답

* SYN_SENT: 클라이언트가 직접 서버에게 시퀀스 번호을 생성하여 SYN 패킷에 담아 보냄
  - SYN(seq = m)

* SYN_RECEIVED: 클라이언트로부터 SYN패킷을 받은 서버는 자신만의 시퀀스 번호를 생성하고 SYN 패킷에 담아 클라이언트의 SYN 패킷에 있는 시퀀스 번호에 +1을 더한 후 ACK 패킷에 담아서 같이 보냄
  - SYN(seq = n), ACK(ack = m+1)

* (Client) ESTABLISHED: 서버로부터 SYN + ACK 패킷을 받은 클라이언트는 ACK 패킷의 시퀀스 번호를 보고 자신이 보낸 시퀀스 번호와 차이가 1임을 확인함
차이가 1이라면 제대로 연결되었다고 판단하고, 서버의 SYN 패킷에 있는 시퀀스 번호에 +1을 더한 후 ACK 패킷에 담아 보냄
  - SYN(ack = n+1)

* (Server) ESTABLISHED: 클라이언트의 ACK 패킷을 받고 패킷에 존재하는 시퀀스 번호가 이전에 보낸 SYN 패킷의 시퀀스 번호 + 1과 동일하다면 연결이 되었다고 판단함
  - ACK(ack = n+1)
```

<img width="640" src="https://user-images.githubusercontent.com/50076031/123632337-c7723280-d852-11eb-9db5-0a9a252e207d.png">













<br><br>

### References
  - https://mindnet.tistory.com/entry/%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC-%EC%89%BD%EA%B2%8C-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0-22%ED%8E%B8-TCP-3-WayHandshake-4-WayHandshake
  - https://velog.io/@ss-won/%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC-TCP-3-way-handshake
  - https://afteracademy.com/blog/what-is-a-tcp-3-way-handshake-process
