package com.haekyulog.haekyulog.service;

import com.haekyulog.haekyulog.domain.Post;
import com.haekyulog.haekyulog.repository.PostRepository;
import com.haekyulog.haekyulog.requesst.PostCreate;
import com.haekyulog.haekyulog.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public PostResponse get(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));
                //.orElseThrow(PostNotFound::new);
                //정확한 파라미터가 넘어 오지 않았을 때 해당 클래스를 에러 리스폰스를 클래스화 하는 것이 어떨까..
                //페이징도 말도 안되는 페이징이 올 수 있고 100번 글까지 있는데 1000번 오면 공통 클래스에서 에러 메시지 던지면 어떨까..

        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();

    }

    public List<PostResponse> getList(int page) {
//        return postRepository.findAll().stream()
//                .map(post -> PostResponse.builder()
//                        .id(post.getId())
//                        .title(post.getTitle())
//                        .content(post.getContent())
//                        .build())
//                .collect(Collectors.toList());
        PageRequest pageRequest = PageRequest.of(page, 5);

        return postRepository.findAll(pageRequest).stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }
}
