package GDGoC.project.user_api;

import GDGoC.project.user_api.user.User;
import GDGoC.project.user_api.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest /* UserTest 클래스가 스프링 부트의 테스트 클래스임을 의미 */
public class UserTest {
    /**
     *  UserRepository가 필요하므로 의존성 주입
     *  보통은 순환 참조 문제와 같은 이유로 @Autowired 보다는 생성자를 통한 객체 주입 방식을 권장
     *  TestCode의 경우에는 JUnit이 생성자를 통한 객체 주입을 지원하지 않음
     *  */
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원 등록 테스트")
    void createUser() {
        User user = new User();
        user.setUserId("userId");
        user.setPassword(passwordEncoder.encode("1234"));
        user.setUsername("username");
        user.setPhone("010-9999-9999");
        this.userRepository.save(user);
    }
}
