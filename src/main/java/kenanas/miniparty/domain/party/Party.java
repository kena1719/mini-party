package kenanas.miniparty.domain.party;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Party {

    private Long partyId;

    //필수
    private String partyName;        //파티 이름
    private String partyDescription; //파티 간략하게 설명
    private Integer partyAdmin;      //파티장 권한이 있는(파티만든사람) 유저 id

    //선택
    private boolean isPublic;        // 공개 or 비공개 파티인지 설정 가능
    private String partyPassword;    // 비공개 파티인 경우 참여시 비밀번호
    private String imageUrl;         // 파티 썸네일 이미지 경로

    //현재 파티에 가입해있는 유저 리스트
    private List<Integer> members = new ArrayList<>();

    public Party() {
    }

    public Party(String partyName, String partyDescription, Integer partyAdmin, boolean isPublic, String partyPassword, String imageUrl) {
        this.partyName = partyName;
        this.partyDescription = partyDescription;
        this.partyAdmin = partyAdmin;

        this.isPublic = isPublic;
        this.partyPassword = partyPassword;
        this.imageUrl = imageUrl;

        //방 만든사람은 자동으로 목록에 추가.
        this.members.add(partyAdmin);
    }
}
