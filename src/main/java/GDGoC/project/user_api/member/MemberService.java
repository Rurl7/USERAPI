package GDGoC.project.user_api.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public void createMember(String userId, String password, String username, String phone) {
        Member member = new Member();
        member.setUserId(userId);
        member.setPassword(password);
        member.setUsername(username);
        member.setPhone(phone);
        this.memberRepository.save(member);
    }

    public Member getMember(String userId) {
        return this.memberRepository.findByUserId(userId);
    }
}
