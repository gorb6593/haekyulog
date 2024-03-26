package com.haekyulog.haekyulog.controller;

import com.haekyulog.haekyulog.repository.PostRepository;
import com.haekyulog.haekyulog.requesst.PostCreate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class AuthControllerTest {

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
        //String json = objectMapper.writeValueAsString(request);

        //expected
//        mockMvc.perform(post("/posts")
//                        .contentType(APPLICATION_JSON)
//                        .content(json)
//                )
//                .andExpect(status().isOk())
//                .andExpect(content().string(""))
//                .andDo(print());


    }
}