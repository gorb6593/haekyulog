package com.haekyulog.haekyulog.service;

import com.haekyulog.haekyulog.domain.Post;
import com.haekyulog.haekyulog.repository.PostRepository;
import com.haekyulog.haekyulog.requesst.PostCreate;
import com.haekyulog.haekyulog.requesst.PostEdit;
import com.haekyulog.haekyulog.requesst.PostSearch;
import com.haekyulog.haekyulog.response.PostResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.Sort.Direction.DESC;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }


    @Test
    @DisplayName("글 작성!")
    void test1() {
        //given
        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();
        //when
        postService.write(postCreate);

        //then
        assertEquals(1L, postRepository.count());
        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }

    @Test
    @DisplayName("글 1개 조회")
    void test2() {

        //given
        Post requestPost = Post.builder()
                .title("foo")
                .content("bar")
                .build( );
        postRepository.save(requestPost);

        //when
        PostResponse postResponse = postService.get(requestPost.getId());

        //then
        assertNotNull(postResponse);
        assertEquals(1L, postRepository.count());
        assertEquals("foo", postResponse.getTitle());
        assertEquals("bar", postResponse.getContent());
    }

    @Test
    @DisplayName("글 1페이지 조회")
    void test3() {

        //given
        //for(int i=0; i<30; i++) -> 결과는 동일
        List<Post> requestPosts = IntStream.range(1,20)
                        .mapToObj(i -> Post.builder()
                                .title("해규 제목 : " + i)
                                .content("반포 자이 : " + i)
                                .build())
                        .toList();

        postRepository.saveAll(requestPosts);

        Pageable pageable = PageRequest.of(0,10, DESC,"id");

//        postRepository.saveAll(List.of(
//                Post.builder()
//                        .title("foo1")
//                        .content("bar1")
//                        .build(),
//                Post.builder()
//                        .title("foo2")
//                        .content("bar2")
//                        .build()
//        ));

        // sql -> select, limit, offset

        PostSearch postSearch = PostSearch.builder()
                .page(1)
                .build();

        //when
        List<PostResponse> posts = postService.getList(postSearch);

        //then
        assertEquals(10,posts.size());
        assertEquals("해규 제목 : 19",posts.get(0).getTitle());
        //assertEquals("해규 제목 : 26",posts.get(4).getTitle());
     }

    @Test
    @DisplayName("글 제목 수정")
    void test4() {

        //given
        Post post = Post.builder()
                .title("해규 제목")
                .content("반포자이")
                .build();

        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("해규 제목 테스트 수정")
                .content("반포자이")
                .build();
        //when
        postService.edit(post.getId(), postEdit);

        //then
        Post changePost = postRepository.findById(post.getId())
                .orElseThrow(() -> new RuntimeException("글이 없습니다. id = " + post.getId()));
        assertEquals("해규 제목 테스트 수정", changePost.getTitle());
        assertEquals("반포자이", changePost.getContent());

    }

    @Test
    @DisplayName("글 내용 수정")
    void test5() {

        //given
        Post post = Post.builder()
                .title("해규 제목")
                .content("반포자이")
                .build();

        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("해규 제목")
                .content("헬리오시티")
                .build();
        //when
        postService.edit(post.getId(), postEdit);

        //then
        Post changePost = postRepository.findById(post.getId())
                .orElseThrow(() -> new RuntimeException("글이 없습니다. id = " + post.getId()));
        assertEquals("헬리오시티", changePost.getContent());

    }

    @Test
    @DisplayName("글 삭제")
    void test6() {

        //given
        Post post = Post.builder()
                .title("해규 제목")
                .content("반포자이")
                .build();

        postRepository.save(post);

        postService.delete(post.getId());

        //then
        assertEquals(0, postRepository.count());

    }

    @Test
    @DisplayName("글 1개 조회")
    void test7() {

        //given
        Post post = Post.builder()
                .title("해규 제목")
                .content("반포자이")
                .build();

        postRepository.save(post);
        //expected
        assertThrows(IllegalArgumentException.class, () -> {
            postService.get(post.getId() + 1L);
        });



    }

}

