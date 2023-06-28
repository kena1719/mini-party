package kenanas.miniparty;

import jakarta.annotation.PostConstruct;
import kenanas.miniparty.domain.party.Party;
import kenanas.miniparty.domain.party.PartyRepository;
import kenanas.miniparty.domain.user.User;
import kenanas.miniparty.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final PartyRepository partyRepository;
    private final UserRepository userRepository;

    @PostConstruct
    public void init(){

        //Party
        Party party1 = new Party("파티A", "처음 만든 파티입니다", 1L,
                true, null, null);
        Party party2 = new Party("파티B", "두번째로 만든 파티입니다", 1L,
                true, null, null);

        partyRepository.save(party1);
        partyRepository.save(party2);

        //User
        User user1 = new User("test", "test", "테스터", "테스터입니다.", "**");
        User user2 = new User("test2", "test2", "테스터2", "테스터2입니다.", "**");
        User user3 = new User("1234", "1234", "테스터1234", "테스터1234입니다.", "**");


        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }


}
