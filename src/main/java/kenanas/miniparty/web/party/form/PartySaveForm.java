package kenanas.miniparty.web.party.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PartySaveForm {

    @NotEmpty
    private String partyName;
    @NotEmpty
    private String partyDescription;


    private boolean isPublic;        // 공개 or 비공개 파티인지 설정 가능

    private String partyPassword;    // 비공개 파티인 경우 참여시 비밀번호

    private MultipartFile imageUrl;         // 파티 썸네일 이미지 경로

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean aPublic) {
        isPublic = aPublic;
    }

}
