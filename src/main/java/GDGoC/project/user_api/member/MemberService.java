package GDGoC.project.user_api.member;

import GDGoC.project.user_api.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public void createMember(String userId, String password, String nickname, String phone) {
        Member member = new Member();
        member.setUserId(userId);
        member.setPassword(password);
        member.setNickname(nickname);
        member.setPhone(phone);
        this.memberRepository.save(member);
    }

    public Member getMember(String userId) {
        Member member = memberRepository.findByUserId(userId);
        if (member == null) {
            throw new DataNotFoundException("사용자를 찾을 수 없습니다.");
        }
        return member;
    }
}

