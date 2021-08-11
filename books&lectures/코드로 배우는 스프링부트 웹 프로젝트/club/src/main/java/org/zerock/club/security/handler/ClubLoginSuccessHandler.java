package org.zerock.club.security.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.zerock.club.security.dto.ClubAuthMemberDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class ClubLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private final PasswordEncoder passwordEncoder;

    public ClubLoginSuccessHandler(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        log.info("onAuthenticationSuccess");

        ClubAuthMemberDto authMemberDto = (ClubAuthMemberDto) authentication.getPrincipal();
        boolean fromSocial = authMemberDto.isFromSocial();
        boolean passwordResult = passwordEncoder.matches("1111", authMemberDto.getPassword());
        log.info("Need Modify Member..? " + fromSocial);

        if (fromSocial && passwordResult) {
            redirectStrategy.sendRedirect(request, response, "/member/modify?from=social");
        }

    }
}
