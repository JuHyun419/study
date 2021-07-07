package org.zerock.club.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final String PASSWORD_ENCODE = "$2a$10$c2HYg.22m3Zr2b6AQsqTEu76PZa5rBYy5khbtkuarMoNIC6g3bVja";

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
        http.authorizeRequests()
                .antMatchers("/sample/all").permitAll()
                .antMatchers("/sample/member").hasRole("USER");

        http.formLogin(); // 인가, 인증에 문제시 로그인 화면
        http.csrf().disable();
        http.logout();
    }
}
