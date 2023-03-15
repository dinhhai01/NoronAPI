package com.mh.service;

import com.mh.data.reponse.CommentResponse;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Comments;
import io.reactivex.Single;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICommentService {

    void createComment(Comments comment);

    void updateContentById(int id,String content);

    void updateTypeById(int id,String type);

    Comments getCommentByPostIdAndUserId(int postId, int userId);

    int countCommentByPostId(int postId);

    Single<List<CommentResponse>> getAllComment(int postId, Pageable pageable);
}
