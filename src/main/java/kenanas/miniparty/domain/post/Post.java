package kenanas.miniparty.domain.post;

import lombok.Data;

import java.util.List;

@Data
public class Post {

    private Long id;

    private int author; // 글쓴이Id

    private String content; //내용
    private List<Integer> likes; //좋아요 누른 사람들 Id
    private Integer parentsId; // 상위 포스트 Id

    public Post() {
    }

    public Post(int author, String content, List<Integer> likes, Integer parentsId) {
        this.author = author;
        this.content = content;
        this.likes = likes;
        this.parentsId = parentsId;
    }
}
