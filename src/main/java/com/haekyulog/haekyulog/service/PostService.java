package com.haekyulog.haekyulog.service;

import com.haekyulog.haekyulog.domain.Post;
import com.haekyulog.haekyulog.exception.PostNotFound;
import com.haekyulog.haekyulog.repository.PostRepository;
import com.haekyulog.haekyulog.requesst.PostCreate;
import com.haekyulog.haekyulog.requesst.PostEdit;
import com.haekyulog.haekyulog.requesst.PostSearch;
import com.haekyulog.haekyulog.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                //.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));
            .orElseThrow(() -> PostNotFound.class);

        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
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
//    public List<PostResponse> getList(Pageable pageable) {
//        //web -> page 1 -> 0
//        //Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "id"));
//        return postRepository.findAll(pageable).stream()
//                .map(PostResponse::new)
//                .collect(Collectors.toList());
//    }
    public List<PostResponse> getList(PostSearch postSearch) {
        //web -> page 1 -> 0
        //Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "id"));
        return postRepository.getList(postSearch).stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostResponse edit(Long id, PostEdit postEdit) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));
        //post.change(postEdit.getTitle(), postEdit.getContent());
//        PostEditor.PostEditorBuilder editorBuilder = post.toEditor();
//
//        PostEditor postEditor = editorBuilder.title(postEdit.getTitle())
//                .content(postEdit.getContent())
//                .build();

        post.edit(postEdit.getTitle(), postEdit.getContent());

        return new PostResponse(post);
    }

    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));

        postRepository.delete(post);
    }
}
