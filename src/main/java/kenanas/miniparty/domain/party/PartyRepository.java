package kenanas.miniparty.domain.party;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class PartyRepository {

    private static final Map<Long, Party> store = new HashMap<>();
    private static long sequence = 0L;

    //생성
    public Party save (Party party){
        party.setPartyId(sequence++);
        store.put(party.getPartyId(), party);
        return party;
    }

    //조회
    public Party findById(Long id){
        return store.get(id);
    }

    public List<Party> findAll(){
        return new ArrayList<>(store.values());
    }


    //업데이트
    public void update(Long partyId, Party updateParam){
        Party findParty = findById(partyId);

        //필수정보 3
        findParty.setPartyAdmin(updateParam.getPartyAdmin());
        findParty.setPartyName(updateParam.getPartyName());
        findParty.setPartyDescription(updateParam.getPartyDescription());

        //선택사항 3
        findParty.setIsPublic(updateParam.getIsPublic());
        findParty.setPartyPassword(updateParam.getPartyPassword());
        findParty.setImageUrl(updateParam.getImageUrl());
    }

    //삭제
    public void clearStore(){
        store.clear();
    }

    // ---유저 관련 ---


    public void addMember(Long partyId, Integer userId){
        Party findParty = findById(partyId);
        List<Integer> members = findParty.getMembers();
        members.add(userId);
        findParty.setMembers(members);
        log.info("addMember = {}", members);
    }

    public void removeMember(Long partyId, Integer userId){
        Party findParty = findById(partyId);
        List<Integer> members = findParty.getMembers();
        members.remove(userId);
        findParty.setMembers(members);
        log.info("removeMember = {}", members);
    }
}
