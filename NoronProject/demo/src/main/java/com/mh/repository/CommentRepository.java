package com.mh.repository;

import com.tej.JooQDemo.jooq.sample.model.Tables;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Comments;
import org.jooq.DSLContext;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CommentRepository implements ICommentRepository {


    private final DSLContext dslContext;

    public CommentRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }


    @Override
    public Comments getCommentByPost_IdAndUser_Id(int postId, int userId) {
        return null;
    }

    @Override
    public int countCommentByPost_Id(int postId) {
        return 0;
    }

    @Override
    public void updateContentById(int id, String content) {

    }

    @Override
    public List<Comments> getAnswerOfPostId(List<Integer> postIds, int limit) {

        return dslContext.select()
                .from(Tables.COMMENTS)
                .where(Tables.COMMENTS.POST_ID.in(postIds)
                        //
                )
                .limit(limit)
                .fetchInto(Comments.class);
    }

    @Override
    public List<Comments> getCommentByPostId(List<Integer> postIds, int limit) {

        return dslContext.select()
                .from(Tables.COMMENTS)
                .where(Tables.COMMENTS.POST_ID.in(postIds))
                .fetchInto(Comments.class);
    }

    @Override
    public List<Comments> getAllCommentByPostId(int postId, Pageable pageable) {

        return dslContext.select()
                .from(Tables.COMMENTS)
                .where(Tables.COMMENTS.POST_ID.eq(postId))
                .limit(pageable.getPageSize()).offset(pageable.getOffset())
                .fetchInto(Comments.class);
    }

    @Override
    public void updateTypeById(int id, String type) {

    }
}
