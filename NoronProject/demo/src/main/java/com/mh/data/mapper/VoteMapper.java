package com.mh.data.mapper;

import com.mh.data.reponse.VoteResponse;
import com.mh.data.request.VoteRequest;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Vote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class VoteMapper {

    public abstract Vote toEntity(VoteRequest voteRequest);

    public abstract VoteResponse toResponse(Vote vote);
}
