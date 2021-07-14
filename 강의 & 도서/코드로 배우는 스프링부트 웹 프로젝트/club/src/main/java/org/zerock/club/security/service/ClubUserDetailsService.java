package org.zerock.club.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.club.entity.ClubMember;
import org.zerock.club.entity.ClubMemberRole;
import org.zerock.club.repository.ClubMemberRepository;
import org.zerock.club.security.dto.ClubAuthMemberDto;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClubUserDetailsService implements UserDetailsService {

    private final ClubMemberRepository clubMemberRepository;

    // username -> 실제 로그인하는 email
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("ClubUserDetailsService username: " + username);

        ClubMember clubMember = clubMemberRepository.findByEmail(username, false)
                .orElseThrow(() -> new UsernameNotFoundException("Check Email or Social"));
        log.info("clubMember: " + clubMember);

        // UserDetails의 구현체인 User를 구현한 Dto
        // ClubAuthMemberDto ---> User ---> UserDetails
        ClubAuthMemberDto clubAuthMemberDto = new ClubAuthMemberDto(
                clubMember.getEmail(),
                clubMember.getPassword(),
                clubMember.isFromSocial(),
                getAuthority(clubMember.getRoleSet())
        );
        clubAuthMemberDto.setName(clubMember.getName());
        clubAuthMemberDto.setFromSocial(clubMember.isFromSocial());
        return clubAuthMemberDto;
    }

    private Collection<? extends GrantedAuthority> getAuthority(Set<ClubMemberRole> roleSet) {
        return roleSet.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());
    }
}
