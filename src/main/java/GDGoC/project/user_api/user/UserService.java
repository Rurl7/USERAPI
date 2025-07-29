package GDGoC.project.user_api.user;

import GDGoC.project.user_api.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(String userId, String password, String username, String phone) {
        User user = new User();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        user.setPhone(phone);
        this.userRepository.save(user);
        return user;
    }

    public User getUser(String userId){
        Optional<User> user = userRepository.findByUserId(userId);
        if(user.isPresent()){
            return user.get();
        } else {
            throw new DataNotFoundException("User not found");
        }
    }
}
