package org.zerock.club.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.zerock.club.entity.ClubMember;
import org.zerock.club.entity.ClubMemberRole;
import org.zerock.club.repository.ClubMemberRepository;
import org.zerock.club.security.dto.ClubAuthMemberDto;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClubOAuthUserDetailsService extends DefaultOAuth2UserService {

    private final ClubMemberRepository clubMemberRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("userRequest: " + userRequest);

        String clientName = userRequest.getClientRegistration().getClientName();
        log.info("cilentName: " + clientName);
        log.info(userRequest.getAdditionalParameters());

        log.info("=================================");
        OAuth2User oAuth2User = super.loadUser(userRequest);
        oAuth2User.getAttributes().forEach((k, v) -> log.info(k + ": " + v));
        String email = null;

        if (clientName.equals("Google")) {
            email = oAuth2User.getAttribute("email");
        }
        log.info("email: " + email);

        ClubMember member = saveSocialMember(email);
        ClubAuthMemberDto clubAuthMember = new ClubAuthMemberDto(
                member.getEmail(),
                member.getPassword(),
                true,
                getAuthority(member.getRoleSet()),
                oAuth2User.getAttributes()
        );
        clubAuthMember.setName(member.getName());
        return clubAuthMember;
    }

    private ClubMember saveSocialMember(String email) {
        // 기존에 동일한 이메일로 가입한 회원이 존재하는 경우 => 해당하는 회원 조회
        Optional<ClubMember> result = clubMemberRepository.findByEmail(email, true);
        if (result.isPresent()) {
            return result.get();
        }

        ClubMember member = ClubMember.builder()
                .email(email)
                .password(passwordEncoder.encode("1111"))
                .fromSocial(true)
                .build();

        member.addMemberRole(ClubMemberRole.USER);
        clubMemberRepository.save(member);

        return member;
    }

    private Collection<? extends GrantedAuthority> getAuthority(Set<ClubMemberRole> roleSet) {
        return roleSet.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());
    }
}
