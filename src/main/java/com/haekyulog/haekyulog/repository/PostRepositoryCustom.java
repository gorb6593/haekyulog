package com.haekyulog.haekyulog.repository;

import com.haekyulog.haekyulog.domain.Post;
import com.haekyulog.haekyulog.requesst.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
