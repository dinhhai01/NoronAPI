package com.mh.service;

import com.mh.data.reponse.VoteResponse;
import com.mh.data.request.VoteRequest;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Vote;

public interface IVoteService {
    public VoteResponse insert(VoteRequest voteRequest, int id);
}
