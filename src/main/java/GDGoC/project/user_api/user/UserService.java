package GDGoC.project.user_api.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /* 회원 생성 */
    public User createUser(String userId, String rawPw, String username, String phone) {
        User u = new User();
        u.setUserId(userId);
        u.setPassword(passwordEncoder.encode(rawPw));
        u.setUsername(username);
        u.setPhone(phone);

        try {
            return userRepository.save(u);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "이미 사용 중인 아이디/전화번호입니다.");
        }
    }

    /* userId 로 조회 (로그인 세션용) */
    @Transactional(Transactional.TxType.SUPPORTS)
    public User getUser(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"));
    }

    /* id 로 조회 (공개 프로필용) */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Optional<User> getById(Integer id) {
        return userRepository.findById(id);
    }
}
