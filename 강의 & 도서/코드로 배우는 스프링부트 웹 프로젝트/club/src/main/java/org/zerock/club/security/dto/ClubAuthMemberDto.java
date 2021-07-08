package org.zerock.club.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Log4j2
@Getter
@Setter
@ToString
public class ClubAuthMemberDto extends User implements OAuth2User {

    private String email;
    private String password;
    private String name;
    private boolean fromSocial;
    private Map<String , Object> attr;

    public ClubAuthMemberDto(String username,
                             String password,
                             boolean fromSocial,
                             Collection<? extends GrantedAuthority> authorities,
                             Map<String, Object> attr) {

        this(username, password, fromSocial, authorities);
        this.attr = attr;
    }

    public ClubAuthMemberDto(
            String username,
            String password,
            boolean fromSocial,
            Collection<? extends GrantedAuthority> authorities) {

        super(username, password, authorities);
        this.email = username;
        this.password = password;
        this.fromSocial = fromSocial;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attr;
    }
}
