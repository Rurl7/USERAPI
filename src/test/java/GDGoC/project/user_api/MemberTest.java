package GDGoC.project.user_api;

import GDGoC.project.user_api.member.Member;
import GDGoC.project.user_api.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest /* MemberTest 클래스가 스프링 부트의 테스트 클래스임을 의미 */
public class MemberTest {
    /**
     *  MemberRepository가 필요하므로 의존성 주입
     *  보통은 순환 참조 문제와 같은 이유로 @Autowired 보다는 생성자를 통한 객체 주입 방식을 권장
     *  TestCode의 경우에는 JUnit이 생성자를 통한 객체 주입을 지원하지 않음
     *  */
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원 등록 테스트")
    void createMember() {
        Member member = new Member();
        member.setUserId("userId");
        member.setPassword(passwordEncoder.encode("1234"));
        member.setUsername("username");
        member.setPhone("010-9999-9999");
        this.memberRepository.save(member);
    }
}
