package kenanas.miniparty.domain.party;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class PartyRepositoryTest {

    PartyRepository partyRepository = new PartyRepository();

    @AfterEach
    void afterEach(){ partyRepository.clearStore(); }

    @Test
    void save(){
        //given
        Party party = new Party("파티A", "처음 만든 파티입니다", 0L,
                true, null, null);

        //when
        Party savedParty = partyRepository.save(party);

        //then
        Party findParty = partyRepository.findById(savedParty.getId());
        assertThat(findParty).isEqualTo(savedParty);
    }

    @Test
    void findAll(){
        //given
        Party party1 = new Party("파티A", "처음 만든 파티입니다", 0L,
                true, null, null);
        Party party2 = new Party("파티B", "두번째로 만든 파티입니다", 0L,
                false, null, null);

        partyRepository.save(party1);
        partyRepository.save(party2);

        //when
        List<Party> result = partyRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(party1, party2);
    }

    @Test
    void updateInfo(){

        //given
        Party party = new Party("파티A", "처음 만든 파티입니다", 0L,
                true, null, null);

        Party savedParty = partyRepository.save(party);
        Long partyId = savedParty.getId();

        //when
        Party updateParty = new Party("파티M", "수정한 파티입니다", 0L,
                false, null, null);

        partyRepository.update(partyId, updateParty);

        //then
        Party findParty = partyRepository.findById(partyId);
        assertThat(findParty.getPartyName()).isEqualTo(updateParty.getPartyName());
        assertThat(findParty.getPartyDescription()).isEqualTo(updateParty.getPartyDescription());
        assertThat(findParty.getIsPublic()).isEqualTo(updateParty.getIsPublic());
    }

    @Test
    void addMember(){
        //given
        Party party = new Party("파티A", "처음 만든 파티입니다", 0L,
                true, null, null);

        partyRepository.save(party);
        Long partyId = party.getId();

        //when
        partyRepository.addMember(partyId, 1L);
        partyRepository.addMember(partyId, 2L);
        Party updateParty = partyRepository.findById(partyId);

        //then
        assertThat(updateParty.getMembers()).contains(1L);
        assertThat(updateParty.getMembers()).isEqualTo(party.getMembers());
    }

    @Test
    void removeMember(){
        //given

        Party party = new Party("파티A", "처음 만든 파티입니다", 0L,
                true, null, null);


        partyRepository.save(party);
        Long partyId = party.getId();

        partyRepository.addMember(partyId, 1L);
        partyRepository.addMember(partyId, 2L);

        //when
        partyRepository.removeMember(partyId, 1);
        Party updateParty = partyRepository.findById(partyId);

        //then
        //assertThat(updateParty.getMembers()).contains(1);
        assertThat(party.getMembers()).contains(2L);

    }


}
