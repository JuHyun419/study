package org.zerock.club.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.club.entity.ClubMember;
import org.zerock.club.entity.ClubMemberRole;

import java.util.stream.IntStream;

@SpringBootTest
class ClubMemberRepositoryTest {

    @Autowired
    private ClubMemberRepository clubMemberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("더미 데이터 생성")
    void insert_Dummies() {
        // 1 -  80 USER만 지정
        // 81 - 90 USER, MANAGER
        // 91 - 100 USER, MANAGER, ADMIN

        IntStream.rangeClosed(1, 100).forEach(i -> {
            ClubMember clubMember = ClubMember.builder()
                    .email("user" + i + "@zerock.org")
                    .name("사용자" + i)
                    .fromSocial(false)
                    .password(passwordEncoder.encode("1111"))
                    .build();

            clubMember.addMemberRole(ClubMemberRole.USER);
            if (i > 80) clubMember.addMemberRole(ClubMemberRole.MANAGER);
            if (i > 90) clubMember.addMemberRole(ClubMemberRole.ADMIN);

            clubMemberRepository.save(clubMember);
        });
    }


    @Test
    void read_ClubMember() {
        /* given */
        String email = "user95@zerock.org";

        /* when */
        ClubMember clubMember = clubMemberRepository.findByEmail(email, false)
                .orElseThrow(NullPointerException::new);

        /* then */
        System.out.println(clubMember);
    }

}