package GDGoC.project.user_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @Configuration : 이 파일이 스프링의 환경 설정 파일임을 의미하는 애너테이션
 * @EnableWebSecurity : 모든 요청 URL이 스프링 시큐리티의 제어를 받도록 만드는 애너테이션. 내부적으로 SecurityFilterChain 클래스가 동작하여 모든 요청 URL에 이 클래스가 필터로 적용되어 URL별로 특별한 설정을 할 수 있게 된다.
 */
@Configuration
@EnableWebSecurity // Spring Security를 활성화
public class SecurityConfig {

    @Bean /* 빈(bean)은 스프링에 의해 생성 또는 관리되는 객체를 의미 */
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 인증되지 않은 모든 페이지의 요청을 허락
                .authorizeHttpRequests(authorizeRequests -> // HTTP 요청에 대한 보안 규칙을 정의
                        authorizeRequests
                                .requestMatchers("/**").permitAll() // /** 경로에 대한 접근을 허용
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                .headers((headers) -> headers
                        .addHeaderWriter(new XFrameOptionsHeaderWriter(
                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
                .formLogin((formLogin) -> formLogin
                        .loginPage("/user/login")
                        .defaultSuccessUrl("/"))
//                .logout((logout) -> logout
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
//                        .logoutSuccessUrl("/")
//                        .invalidateHttpSession(true))
        ;

        return http.build(); // HTTP 보안 설정을 빌드하여 반환
    }

    // 비밀번호 인코더를 정의
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
