package com.mh.service;

import com.mh.data.reponse.PostResponse;
import com.mh.data.request.PostRequest;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Post;

import io.reactivex.Single;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

public interface IPostService {

    PostResponse insertPost(PostRequest postRequest);
    Single<List<PostResponse>> getAllPosts(Integer numComment, Pageable pageable);

    Post getViewById(int id);

    Single<Integer> updateViewById(int id);

    Single<PostResponse> updateContentById(int id, String content);

    Single<String> updateTitleById(int id, String title);

    Single<String> updateTypeById(int id, String type);

    Map<Integer,Integer> countVoteAndViewById(int id);
}
