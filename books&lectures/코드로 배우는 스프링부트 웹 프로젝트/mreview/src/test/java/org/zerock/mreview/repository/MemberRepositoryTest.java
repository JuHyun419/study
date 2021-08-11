package org.zerock.mreview.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mreview.entity.Member;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    @DisplayName("")
    void insertMembers() {
        /* given */
        IntStream.rangeClosed(1, 100)
                .forEach(i -> {
                    Member member = Member.builder()
                            .email("r" + i + "@aaa.com")
                            .pw("1111")
                            .nickname("reviewer" + i)
                            .build();

                    memberRepository.save(member);
                });

        /* when */


        /* then */
    }

    @Transactional
    @Commit
    @Test
    @DisplayName("")
    void testDeleteMember() {
        /* given */
        Long mid = 2L; // Member의 ID
        Member member = Member.builder()
                .mid(mid)
                .build();

        /* when */
        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(mid);

        /* then */
//        assertThatThrownBy(() -> {
//            memberRepository.findById(mid);
//        }).isInstanceOf(NullPointerException.class);
    }
}