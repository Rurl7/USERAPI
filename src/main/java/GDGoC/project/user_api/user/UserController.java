package GDGoC.project.user_api.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@RequiredArgsConstructor /* @RequiredArgsConstructor : 롬복이 제공하는 애너테이션으로, final이 붙은 속성을 포함하는 생성자를 자동으로 생성 */
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }

    @GetMapping("/signup")
    public String signup(UserForm userForm){
        return "sign_up";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserForm userForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "sign_up";
        }
        if (!userForm.getPassword1().equals(userForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "sign_up";
        }

        try {
            userService.createUser(userForm.getUserId(),userForm.getPassword1(),userForm.getUsername(),userForm.getPhone());
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
