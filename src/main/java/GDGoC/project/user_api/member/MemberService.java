package GDGoC.project.user_api.member;

import GDGoC.project.user_api.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void createMember(String userId, String password, String nickname, String phone) {
        Member member = new Member();
        member.setUserId(userId);
        member.setPassword(passwordEncoder.encode(password));
        member.setNickname(nickname);
        member.setPhone(phone);
        this.memberRepository.save(member);
    }

    public Member getUser(String username) {
        Optional<Member> member = this.memberRepository.findByUsername(username);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new DataNotFoundException("member not found");
        }
    }
}
