package com.haekyulog.haekyulog;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;

//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    @DisplayName("/posts 요청시 hello world 출력")
//    void test() throws Exception {
//        //expected
//        ResultHandler print;
//        mockMvc.perform(get("/posts"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Hello world"))
//                .andDo(print());
//
//
//    }

    @Test
    @DisplayName("/posts 요청시 hello world 출력")
    void test() throws Exception {
        //글 내용
        //글 제목
        // 사용자 , id , name ,level ....
        //application/json ...  이거 였었음..=> application/x-www-from-urlencoded
        //expected

        /**
         *
         */

        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"제목입니다~~\", \"content\": \"내용입니다!@!\"}")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Hello world"))
                .andDo(print());


    }

    @Test
    @DisplayName("/posts 요청시 title 필수")
    void test2() throws Exception {


        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"\", \"content\": \"내용입니다!@!\"}")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Hello world"))
                .andDo(print());


    }
}