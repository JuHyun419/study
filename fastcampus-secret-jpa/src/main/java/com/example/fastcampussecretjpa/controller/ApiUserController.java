package com.example.fastcampussecretjpa.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.fastcampussecretjpa.dto.UserLogin;
import com.example.fastcampussecretjpa.dto.UserLoginToken;
import com.example.fastcampussecretjpa.entity.User;
import com.example.fastcampussecretjpa.exception.UserNotFoundException;
import com.example.fastcampussecretjpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;

@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiUserController {

    private final UserRepository userRepository;

    /* 사용자의 이메일과 비밀번호를 통해 JWT 토큰 발행 */
    @PostMapping("/user/login")
    public ResponseEntity<UserLoginToken> createToken(@RequestBody @Valid UserLogin userLogin) {
        final User user = userRepository.findByEmail(userLogin.getEmail())
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 존재하지 않습니다."));

        // 현재 날짜 + 1달
        LocalDateTime expiredAt = LocalDateTime.now().plusMonths(1);
        Date expiredDate = java.sql.Timestamp.valueOf(expiredAt);

        final String token = JWT.create()
                .withExpiresAt(expiredDate)
                .withClaim("user_id", user.getId())
                .withSubject(user.getUserName())
                .withIssuer(user.getEmail())
                .sign(Algorithm.HMAC512("fastcampus".getBytes()));

        final UserLoginToken userLoginToken = UserLoginToken.builder()
                .token(token)
                .build();

        return ResponseEntity.ok().body(userLoginToken);
    }

    @PatchMapping("/user/login")
    private ResponseEntity<UserLoginToken> refreshToken(HttpServletRequest request) {
        // 1. Request 헤더에서 Token을 가져오기
        /**
         * Request Header에 다음과 같이 저장
         * Content-Type: application/json
         * F-TOKEN: afasdklp#asdiojxcoi@asdsakd1abasd (토큰 값)
         * 기본적으로 Bearer afasdklp#asdiojxcoi@asdsakd1abasd 처럼 들어오기 때문에 substring을 해줘야함
         */
        final String token = request.getHeader("F-TOKEN");
        final String email = JWT.require(Algorithm.HMAC512("fastcampus".getBytes())).build()
                .verify(token) // SignatureVerificationException 예외처리 해줘야함
                .getIssuer();  // email

        final User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("사용자가 존재하지 않습니다."));

        // 현재 날짜 + 1달
        final LocalDateTime expiredAt = LocalDateTime.now().plusMonths(1);
        final Date expiredDate = java.sql.Timestamp.valueOf(expiredAt);

        final String newToken = JWT.create()
                .withExpiresAt(expiredDate)
                .withClaim("user_id", user.getId())
                .withSubject(user.getUserName())
                .withIssuer(user.getEmail())
                .sign(Algorithm.HMAC512("fastcampus".getBytes()));

        final UserLoginToken userLoginToken = UserLoginToken.builder()
                .token(newToken)
                .build();

        return ResponseEntity.ok().body(userLoginToken);
    }
}
