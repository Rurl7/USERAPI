package GDGoC.project.user_api.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * Q. 굳이 Form을 사용 하는 이유는 뭘까?
 * A. 폼 클래스는 입력값을 검증할 때뿐만아니라 입력 항목을 바인딩할 때도 사용
 *    바인딩: 템플릿의 항목과 form 클래스의 속성이 매핑되는 과정
 */

@Getter
@Setter
public class UserForm {
    @NotEmpty(message = "아이디는 필수항목입니다.")
    private String userId;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String password2;

    @NotEmpty(message = "닉네임은 필수항목입니다.")
    private String username;

    @NotEmpty(message = "전화번호는 필수항목입니다.")
    private String phone;
}
