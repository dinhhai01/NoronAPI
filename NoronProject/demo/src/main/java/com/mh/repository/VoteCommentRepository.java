package com.mh.repository;

import com.tej.JooQDemo.jooq.sample.model.Tables;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VoteCommentRepository implements IVoteCommentRepository{

    private final DSLContext dslContext;

    public VoteCommentRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Long insertVoteComment(int commentId, int voteId) {
        List<Long> list = new ArrayList<>();
        dslContext.transaction(out->{
            DSLContext context = DSL.using(out);
            context.insertInto(Tables.VOTE_COMMENT,
                    Tables.VOTE_COMMENT.COMMENT_ID,
                    Tables.VOTE_COMMENT.VOTE_ID)
                    .values(commentId,voteId);

            Long count = context.selectCount()
                    .from(Tables.VOTE_COMMENT)
                    .where(Tables.VOTE_COMMENT.COMMENT_ID.eq(commentId))
                    .fetchOneInto(Long.class);
            list.add(count);
        });
        return list.get(0);
    }
}
