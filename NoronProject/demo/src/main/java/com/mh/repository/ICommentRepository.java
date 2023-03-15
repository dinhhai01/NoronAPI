package com.mh.repository;


import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Comments;
import io.reactivex.Single;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ICommentRepository {

    List<Comments> getAnswerOfPostId(List<Integer> postIds, int limit);

    Single<List<Comments>> getCommentByPostId(List<Integer> postIds, int limit);

    Single<List<Comments>> getAllCommentByPostId(int post, Pageable pageable);

    void updateTypeById(int id,String type);


    void updateContentById(int id,String content);

    Comments getCommentByPost_IdAndUser_Id(int postId, int userId);

    int countCommentByPost_Id(int postId);

}
