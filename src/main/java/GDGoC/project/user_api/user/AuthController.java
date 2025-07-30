package GDGoC.project.user_api.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    /* ---------------- 회원 가입 ---------------- */
    @PostMapping("/auth/signup")
    public ResponseEntity<UserDto> signup(@Valid @RequestBody UserSignupRequest req) {

        User user = userService.createUser(
                req.userId(), req.password(), req.username(), req.phone());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserDto.from(user));
    }

    /* ---------------- 로그인 ---------------- */
    @PostMapping("/auth/login")
    public void login() {
        /* 실제 인증은 Spring Security 필터에서 처리되므로 여기서는 굳이 코드를 작성하지 않아도 됩니다. */
    }
}
