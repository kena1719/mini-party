package kenanas.miniparty.domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryTest {

    UserRepository userRepository = new UserRepository();

    @AfterEach
    void afterEach(){ userRepository.clearStore(); }

    @Test
    void save(){
        //given
            User user = new User("testA@naver.com", "password1234", "HelloMyNickname",
                    null, null);

        //when
        User savedUser = userRepository.save(user);

        //then
        User findUser = userRepository.findById(savedUser.getId());
        assertThat(findUser.getId()).isEqualTo(savedUser.getId());

    }

    @Test
    void findAll(){
        User userA= new User("testA@naver.com", "password1234", "HelloMyNickname",
                null, null);
        User userB = new User("testB@google.com", "1234password", "NaNaNas",
                "안녕하세요", null);

        userRepository.save(userA);
        userRepository.save(userB);

        //when
        List<User> result = userRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(userA, userB);

    }

    @Test
    void updateUser(){
        User userA= new User("testA@naver.com", "password1234", "HelloMyNickname",
                null, null);
        User savedUser = userRepository.save(userA);
        Long userId = savedUser.getId();

        //when
        User userRenew = new User("testB@google.com", "1234password", "NaNaNas RENEW",
                "안녕하세요. 설명을 추가했어요.", null);
        userRepository.update(userId, userRenew);

        //then
        User findUser = userRepository.findById(userId);
        assertThat(findUser.getUserName()).isEqualTo(userRenew.getUserName());
        assertThat(findUser.getLoginId()).isEqualTo(userRenew.getLoginId());
        assertThat(findUser.getUserDescription()).isEqualTo(userRenew.getUserDescription());

    }


}
