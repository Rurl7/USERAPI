package GDGoC.project.user_api.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
    public String signup(MemberForm memberForm){
        return "sign_up";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberForm memberForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "sign_up";
        }
        if (!memberForm.getPassword1().equals(memberForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "sign_up";
        }

        try {
            memberService.createMember(memberForm.getUserId(),memberForm.getPassword1(),memberForm.getNickname(),memberForm.getPhone());
        }catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup_form";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }

        return "redirect:/";
    }
}
