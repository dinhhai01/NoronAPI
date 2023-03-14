package com.mh.repository;

import com.tej.JooQDemo.jooq.sample.model.Tables;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VotePostRepository implements IVotePostRepository{

    private final DSLContext dslContext;

    public VotePostRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Long insertVote(int voteId, int postId) {
        List<Long> list = new ArrayList<>();
        dslContext.transaction(out->{
            DSLContext context = DSL.using(out);
            context.insertInto(Tables.VOTE_POST,
                    Tables.VOTE_POST.VOTE_ID,
                    Tables.VOTE_POST.POST_ID)
                    .values(voteId,postId)
                    .execute();

            Long count = context.selectCount()
                    .from(Tables.VOTE_POST)
                    .where(Tables.VOTE_POST.POST_ID.eq(postId))
                    .fetchOneInto(Long.class);
            list.add(count);
        });
        return list.get(0);
    }

    @Override
    public int countVoteByPostId(int id) {
        int count = dslContext.selectCount()
                .from(Tables.VOTE_POST)
                .where(Tables.VOTE_POST.POST_ID.eq(id))
                .fetchOneInto(Integer.class);
        return count;
    }
}
