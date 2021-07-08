package org.zerock.club.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.club.security.handler.ClubLoginSuccessHandler;
import org.zerock.club.security.service.ClubUserDetailsService;

@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final String PASSWORD_ENCODE = "$2a$10$c2HYg.22m3Zr2b6AQsqTEu76PZa5rBYy5khbtkuarMoNIC6g3bVja";
    private final int VALID_COOKIE_TIME = 60 * 60 * 7; // 7일

    @Autowired
    private ClubUserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Spring Security에서 UserDetailsService를 사용함으로써 아래의 인메모리는 사용 X
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 사용자 계정은 "user1", 패스워드는 "1234"에 대한 BCrypt~ 인코딩
//        auth.inMemoryAuthentication()
//                .withUser("user1")
//                .password(PASSWORD_ENCODE)
//                .roles("USER");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /* @PreAuthorize 어노테이션으로 대체 */
//        http.authorizeRequests()
//                .antMatchers("/sample/all").permitAll()
//                .antMatchers("/sample/member").hasRole("USER");


        http.formLogin(); // 인가, 인증에 문제시 로그인 화면
        http.csrf().disable();
        http.logout();

        http.oauth2Login().successHandler(successHandler());
        http.rememberMe().tokenValiditySeconds(VALID_COOKIE_TIME).userDetailsService(userDetailsService);
    }

    private ClubLoginSuccessHandler successHandler() {
        return new ClubLoginSuccessHandler(passwordEncoder());
    }
}
