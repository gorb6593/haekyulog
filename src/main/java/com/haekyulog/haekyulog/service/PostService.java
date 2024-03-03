package com.haekyulog.haekyulog.service;

import com.haekyulog.haekyulog.domain.Post;
import com.haekyulog.haekyulog.repository.PostRepository;
import com.haekyulog.haekyulog.requesst.PostCreate;
import com.haekyulog.haekyulog.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreate postCreate) {
        Post post = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();
        postRepository.save(post);
    }

//    public PostResponse  get(Long id) {
    public PostResponse get(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));
                //.orElseThrow(PostNotFound::new);

        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();

        /**
         * Controller -> WebService -> Repository
         *             -> PostService
         */

    }

//    public List<PostResponse> getList(int page) {
////        return postRepository.findAll().stream()
////                .map(post -> PostResponse.builder()
////                        .id(post.getId())
////                        .title(post.getTitle())
////                        .content(post.getContent())
////                        .build())
////                .collect(Collectors.toList());
//        PageRequest pageRequest = PageRequest.of(page, 5);
//
//        return postRepository.findAll(pageRequest).stream()
//                .map(PostResponse::new)
//                .collect(Collectors.toList());
//    }
}
