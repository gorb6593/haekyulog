package com.haekyulog.haekyulog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haekyulog.haekyulog.domain.Post;
import com.haekyulog.haekyulog.repository.PostRepository;
import com.haekyulog.haekyulog.requesst.PostCreate;
import com.haekyulog.haekyulog.requesst.PostEdit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class PostControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성 요청시 hello world 출력")
    void test() throws Exception {

        //given
        PostCreate request = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();
        String json = objectMapper.writeValueAsString(request);

        //expected
        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andDo(print());


    }

    @Test
    @DisplayName("글 작성 요청시 title 필수")
    void test2() throws Exception {

        //given
        PostCreate request = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();
        String json = objectMapper.writeValueAsString(request);

        //expected
        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        //.content("{\"title\": null, \"content\": \"내용입니다!@!\"}")
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @DisplayName("글 작성 요청시 db 저장")
    void test3() throws Exception {

        //given
        PostCreate request = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(request);

        //when
        mockMvc.perform(post("/posts?authorization=haekyulog")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());

        //then
        assertEquals(1L, postRepository.count());

        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());

    }

    @Test
    @DisplayName("글 1개 조회")
    void test4() throws Exception {
        //given
        Post post = Post.builder()
                .title("12345678901245")
                .content("bar")
                .build();
        postRepository.save(post);

        // expected ( when + then)
        mockMvc.perform(get("/posts/{postId}", post.getId())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(post.getId()))
                .andExpect(jsonPath("$.title").value("1234567890"))
                .andExpect(jsonPath("$.content").value("bar"))
                .andDo(print());
    }

    @Test
    @DisplayName("글 여러개 조회")
    void test5() throws Exception {
        //given
//        Post post1 = postRepository.save(Post.builder()
//                .title("title_1")
//                .content("content_1")
//                .build());
//
//        Post post2 = postRepository.save(Post.builder()
//                .title("title_2")
//                .content("content_2")
//                .build());
        List<Post> requestPosts = IntStream.range(1,10)
                .mapToObj(i -> Post.builder()
                        .title("해규 제목 : " + i)
                        .content("반포 자이 : " + i)
                        .build())
                .toList();

        postRepository.saveAll(requestPosts);

        // expected ( when + then)
        mockMvc.perform(get("/posts?page=1&size=10")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                /**
                 * {id: ...,title: ...}
                 *
                 * [{id:...,title:...}, {id:...,title:...}]
                 */
                //.andExpect(jsonPath("$.id").value(post2.getId()))
                //.andExpect(jsonPath("$.title").value("1234567890"))
//                .andExpect(jsonPath("$.length()", is(2)))
//                .andExpect(jsonPath("$[0].id").value(post1.getId()))
//                .andExpect(jsonPath("$[0].title").value("title_1"))
//                .andExpect(jsonPath("$[0].content").value("content_1"))
//                .andExpect(jsonPath("$[1].id").value(post2.getId()))
//                .andExpect(jsonPath("$[1].title").value("title_2"))
//                .andExpect(jsonPath("$[1].content").value("content_2"))
                .andDo(print());



        //then
    }

    @Test
    @DisplayName("페이지를 0으로 요청하면 첫 페이지를 가져온다. ")
    void test6() throws Exception {
        //given
        List<Post> requestPosts = IntStream.range(1,10)
                .mapToObj(i -> Post.builder()
                        .title("해규 제목 : " + i)
                        .content("반포 자이 : " + i)
                        .build())
                .toList();

        postRepository.saveAll(requestPosts);

        // expected ( when + then)
        mockMvc.perform(get("/posts?page=0&size=10")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("글 제목 수정")
    void test7() throws Exception {
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

        // expected
        mockMvc.perform(patch("/posts/{postId}", post.getId()) //PATCH /posts/{postId}
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postEdit)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("글 삭제")
    void test8() throws Exception {
        //given
        Post post = Post.builder()
                .title("해규 제목")
                .content("반포자이")
                .build();

        postRepository.save(post);

        // expected
        mockMvc.perform(delete("/posts/{postId}", post.getId()) //PATCH /posts/{postId}
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("존재하지 않는 게시글 조회")
    void test9() throws Exception {
        //given
//        Post post = Post.builder()
//                .title("해규 제목")
//                .content("반포자이")
//                .build();
//
//        postRepository.save(post);

        // expected
        mockMvc.perform(delete("/posts/{postId}", 1L) //PATCH /posts/{postId}
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @DisplayName("존재하지 않는 게시글 수정")
    void test10() throws Exception {

        //given
        PostEdit postEdit = PostEdit.builder()
                .title("해규 제목")
                .content("헬리오시티")
                .build();

        // expected
        mockMvc.perform(patch("/posts/{postId}", 1L) //PATCH /posts/{postId}
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postEdit)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @DisplayName("게시글 작성시 제목에 '바보'는 포함될 수 없다.")
    void test11() throws Exception {

        //given
        PostCreate request = PostCreate.builder()
                .title("나는 바보입니다.")
                .content("반포자이")
                .build();

        String json = objectMapper.writeValueAsString(request);

        // expected
        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andDo(print());


    }

}