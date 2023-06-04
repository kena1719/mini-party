package kenanas.miniparty.domain.user;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    private static final Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;

    public User save(User user){
        user.setUserId(sequence++);
        store.put(user.getUserId(), user);
        return user;
    }

    public User findById(Long id){
        return store.get(id);
    }

    public List<User> findAll(){
        return new ArrayList<>(store.values());
    }

    public void update(Long id, User user){
        User findUser = store.get(id);

        //필수
        findUser.setEmail(user.getEmail());
        findUser.setUserName(user.getUserName());
        findUser.setPassword(user.getPassword());

        //선택
        findUser.setUserDescription(user.getUserDescription());
        findUser.setImageUrl(user.getImageUrl());
    }

    public void clearStore(){
        store.clear();
    }

}
