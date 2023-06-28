package kenanas.miniparty.domain.post;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {

    private static final Map<Long, Post> store = new HashMap<>();
    private static long sequence = 0L;

    //저장
    public Post save(Post post){
        post.setId(++sequence);
        store.put(post.getId(), post);
        return post;
    }

    //찾기
    public Post findById(Long id){
        return store.get(id);
    }

    //전부찾기
    public List<Post> findAll(){
        return new ArrayList<>(store.values());
    }

    //저장소 삭제
    public void clearStore(){
        store.clear();
    }

    //내용 업데이트
    //내용만 업데이트 할것인지? 웹에서 처리해서 여기서 Post를 받을것인지?
    public void updateContent(Long postId, String updateParam){
        Post post = store.get(postId);
        post.setContent(updateParam);
    }

}
