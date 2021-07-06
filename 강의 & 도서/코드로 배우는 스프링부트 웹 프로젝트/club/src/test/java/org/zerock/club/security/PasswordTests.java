package org.zerock.club.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PasswordTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testEncode() {
        /* given */
        String password = "1111";
        String encodePassword = passwordEncoder.encode(password);
        System.out.println("encodePassword = " + encodePassword);

        /* when */
        boolean matchResult = passwordEncoder.matches(password, encodePassword);

        /* then */
        assertThat(matchResult).isTrue();
    }

}
