## [SpringBoot + JWT(Json Web Token)](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-jwt/dashboard)
- JWT는 Header, Payload, Signature 3개의 부분으로 구성
- JWT는 중앙의 인증서버, 데이터 스토어에 대한 의존성 없음, 시스템 수평 확장이 유리함
- Base64 URL Safe Encoding -> URL, Cookie, Header 어디에서든 사용이 가능함

- Payload의 정보가 많아지면 네트워크 사용량 증, 데이터 설계 고려 필요
- 토큰이 클라이언트에 저장되기 때문에 서버에서 클라이언트의 토큰을 조작할 수 없음

### Header
- Signature를 해싱하기 위한 알고리즘 정보들이 담겨있음

### Payload
- 서버와 클라이언트가 주고받는, 시스템에서 실제로 사용될 정보에 대한 내용들을 담고있음

### Signature
- 토큰의 유효성 검증을 위한 문자열




https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-jwt/dashboard