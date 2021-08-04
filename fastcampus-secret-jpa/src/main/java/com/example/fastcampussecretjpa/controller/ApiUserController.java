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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
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

        final String token = JWT.create()
                .withExpiresAt(new Date())
                .withClaim("user_id", user.getId())
                .withSubject(user.getUserName())
                .withIssuer(user.getEmail())
                .sign(Algorithm.HMAC512("fastcampus".getBytes()));

        final UserLoginToken userLoginToken = UserLoginToken.builder()
                .token(token)
                .build();

        return ResponseEntity.ok().body(userLoginToken);
    }
}
