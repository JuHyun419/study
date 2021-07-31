package com.juhyun.springbootjwttutorial.service;

import com.juhyun.springbootjwttutorial.dto.UserDto;
import com.juhyun.springbootjwttutorial.entity.Authority;
import com.juhyun.springbootjwttutorial.entity.User;
import com.juhyun.springbootjwttutorial.repository.UserRepository;
import com.juhyun.springbootjwttutorial.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User signUp(final UserDto userDto) {
        if (existUser(userDto.getUsername())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        final Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        final User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return userRepository.save(user);
    }

    private boolean existUser(final String username) {
        return userRepository.findOneWithAuthoritiesByUsername(username).orElse(null) != null;
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(final String username) {
        return userRepository.findOneWithAuthoritiesByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }
}
