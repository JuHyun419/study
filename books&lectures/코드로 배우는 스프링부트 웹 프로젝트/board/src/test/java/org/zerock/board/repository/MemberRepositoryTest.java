package org.zerock.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.entity.Member;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void insertMembers() {
        IntStream.rangeClosed(1, 100)
                .forEach(i -> {
                    Member writer = Member.builder()
                            .email("user" + i + "@aaa.com")
                            .password("1111")
                            .name("USER" + i)
                            .build();

                    memberRepository.save(writer);
                });
    }
}