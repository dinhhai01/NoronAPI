package com.mh.repository;


import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Vote;
import org.springframework.stereotype.Repository;

@Repository
public interface IVoteRepository {

    public Vote insertVote(Vote vote);
}
