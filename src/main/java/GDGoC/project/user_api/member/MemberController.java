package GDGoC.project.user_api.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor /* @RequiredArgsConstructor : 롬복이 제공하는 애너테이션으로, final이 붙은 속성을 포함하는 생성자를 자동으로 생성 */
@Controller
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/signin")
    public String signin(){
        return "sign_in";
    }

    @GetMapping("/signup")
    public String signup(){
        return "sign_up";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberForm member, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "sign_up";
        }
        this.memberService.createMember(member.getUserId(), member.getPassword(), member.getNickname(), member.getPhone());
        // TODO: 포스트로 리턴
        return "home";
    }
}
