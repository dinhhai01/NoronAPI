package com.mh.repository;

import com.mh.utils.DateTime;
import com.tej.JooQDemo.jooq.sample.model.Tables;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Vote;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;


@Repository
public class VoteRepository implements IVoteRepository{

    private final DSLContext dslContext;

    public VoteRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }


    @Override
    public Vote insertVote(Vote vote) {
        Vote vote1 = dslContext.insertInto(Tables.VOTE,
                Tables.VOTE.USER_ID,
                Tables.VOTE.CREATE_AT,
                Tables.VOTE.UPDATE_AT,
                Tables.VOTE.DELETE_AT)
                .values(vote.getUserId(), DateTime.time,DateTime.time,DateTime.time)
                .returning(Tables.VOTE.ID,Tables.VOTE.USER_ID)
                .fetchOne().into(Vote.class);
        return vote1;
    }
}
