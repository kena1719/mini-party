package kenanas.miniparty.domain.login;

import kenanas.miniparty.domain.user.User;
import kenanas.miniparty.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public User login(String userId, String password){
        return userRepository.findByLoginId(userId)
                .filter(user -> user.getPassword().equals(password))
                .orElse(null);
        //null 은 로그인 실패임.
    }
}
