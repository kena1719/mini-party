package kenanas.miniparty.domain.user;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {

    private static final Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;

    public User save(User user){
        user.setId(++sequence);
        store.put(user.getId(), user);
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
        findUser.setLoginId(user.getLoginId());
        findUser.setUserName(user.getUserName());
        findUser.setPassword(user.getPassword());

        //선택
        findUser.setUserDescription(user.getUserDescription());
        findUser.setImageUrl(user.getImageUrl());
    }

    public void clearStore(){
        store.clear();
    }

    public Optional<User> findByLoginId(String loginId) {
        /*
        iter을 사용하면
        List<User> all = findAll();
        for (User user : all) {
            if (user.getLoginId().equals(loginId)){
                return Optional.of(user);
            }
        }
        return Optional.empty();*/

        return findAll().stream()
                .filter(user -> user.getLoginId().equals(loginId))
                .findFirst();


    }
}
