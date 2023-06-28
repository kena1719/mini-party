package kenanas.miniparty.domain.Post;

import kenanas.miniparty.domain.post.Post;
import kenanas.miniparty.domain.post.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class PostRepositoryTest {

    PostRepository postRepository = new PostRepository();

    @AfterEach
    void afterEach(){ postRepository.clearStore(); }

    @Test
    void save(){
        List<Integer> likes = new ArrayList<>();
        likes.add(1);
        likes.add(2);

        //given
        Post post = new Post(1, "글 내용 부분입니다.", likes, null);

        //when
        Post savedPost = postRepository.save(post);

        //
        Post findPost = postRepository.findById(savedPost.getId());
        assertThat(findPost).isEqualTo(savedPost);
    }

    @Test
    void findAll(){
        //given
        Post post1 = new Post(1, "글 내용 부분입니다.", null, null);
        Post post2 = new Post(1, "글이에요.", null, null);

        postRepository.save(post1);
        postRepository.save(post2);

        //when
        List<Post> list = postRepository.findAll();

        //then
        assertThat(list.size()).isEqualTo(2);
        assertThat(list).contains(post1, post2);
    }

    @Test
    void updateContent(){
        //given
        Post post = new Post(1, "글 내용 부분입니다.", null, null);

        //when
        Post saved = postRepository.save(post);
        postRepository.updateContent(saved.getId(), "수정");
        Long postId = saved.getId();

        //then
        assertThat(postRepository.findById(postId).getContent()).isEqualTo("수정");

    }



}
