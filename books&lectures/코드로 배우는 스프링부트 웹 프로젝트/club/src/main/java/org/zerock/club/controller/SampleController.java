package org.zerock.club.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.club.security.dto.ClubAuthMemberDto;

@Controller
@Log4j2
@RequestMapping("/sample")
public class SampleController {

    // 로그인을 하지 않은 사용자도 접근 가능
    @PreAuthorize("permitAll()")
    @GetMapping("/all")
    public void all() {
        log.info("all....");
    }

    // 로그인한 사용자만 접근 가능
    @GetMapping("/member")
    public void member(@AuthenticationPrincipal ClubAuthMemberDto clubAuthMemberDto) {
        log.info("member......");
        log.info("Auth Member: " + clubAuthMemberDto);
    }

    // 관리자(admin) 권한을 가진 사용자만 접근 가능
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public void admin() {
        log.info("admin........");
    }


    // 특별 정해진 사용자만 해당 메서드를 실행하도록 설정 => "user95@zerock.org" 의 사용자만 해당 메소드 접근 가능
    @PreAuthorize("#clubAuthMemberDto != null && #clubAuthMemberDto.username eq \"user95@zerock.org\"")
    @GetMapping("/only")
    public String memberOnly(@AuthenticationPrincipal ClubAuthMemberDto clubAuthMemberDto) {
        log.info(clubAuthMemberDto);

        return "/sample/admin";
    }
}
