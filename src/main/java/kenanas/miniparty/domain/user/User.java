package kenanas.miniparty.domain.user;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class User {

    private Long userId;

    //필수
    private String email;           //로그인 시 사용할 이메일
    private String password;        //로그인 시 사용할 비밀번호
    private String userName;        //활동중 사용할 닉네임

    //선택
    private String userDescription; //유저 본인 간략하게 설명
    private String imageUrl;        //썸네일 이미지 주소

    //가입한 파티 리스트
    private List<Integer> parties = new ArrayList<>();

    public User() {
    }

    public User(String email, String password, String userName, String userDescription, String imageUrl) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.userDescription = userDescription;
        this.imageUrl = imageUrl;
    }
}
