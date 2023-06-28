package kenanas.miniparty.domain.party;

import kenanas.miniparty.domain.file.UploadFile;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Party {

    private Long id;
    private Long partyAdmin;      //파티장 권한이 있는(파티만든사람) 유저 id

    //필수
    private String partyName;        //파티 이름
    private String partyDescription; //파티 간략하게 설명

    //선택
    private boolean isPublic;        // 공개 or 비공개 파티인지 설정 가능
    private String partyPassword;    // 비공개 파티인 경우 참여시 비밀번호
    private UploadFile imageUrl;         // 파티 썸네일 이미지 경로

    //현재 파티에 가입해있는 유저 리스트
    private List<Long> members = new ArrayList<>();

    public Party() {
    }

    public Party(String partyName, String partyDescription, Long partyAdmin, boolean isPublic, String partyPassword, UploadFile imageUrl) {
        this.partyName = partyName;
        this.partyDescription = partyDescription;
        this.partyAdmin = partyAdmin;

        this.isPublic = isPublic;
        this.partyPassword = partyPassword;
        this.imageUrl = imageUrl;

        //방 만든사람은 자동으로 목록에 추가.
        this.members.add(partyAdmin);
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
