package com.mh.service;

import com.mh.data.mapper.VoteMapper;
import com.mh.data.reponse.VoteResponse;
import com.mh.data.request.VoteRequest;
import com.mh.repository.VoteCommentRepository;
import com.mh.repository.VotePostRepository;
import com.mh.repository.VoteRepository;
import com.mh.utils.EVote;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Vote;
import org.springframework.stereotype.Service;


@Service
public class VoteService implements IVoteService{

    private final VoteRepository voteRepository;

private final VoteCommentRepository voteCommentRepository;

    private final VotePostRepository votePostRepository;

    private final VoteMapper voteMapper;

    public VoteService(VoteRepository voteRepository,
                       VoteCommentRepository voteCommentRepository,
                       VotePostRepository votePostRepository,
                       VoteMapper voteMapper) {
        this.voteRepository = voteRepository;
        this.voteCommentRepository = voteCommentRepository;
        this.votePostRepository = votePostRepository;
        this.voteMapper = voteMapper;
    }

    @Override
    public VoteResponse insert(VoteRequest voteRequest, int id) {
        Vote vote = voteMapper.toEntity(voteRequest);
        vote = voteRepository.insertVote(vote);
        String type = voteRequest.getTypeVote();
        Long count = 0L;
        if(type.equals(EVote.POST.getType())){
            count = votePostRepository.insertVote(vote.getId(),id);
        }

        if(type.equals(EVote.COMMENT.getType())){
            count = voteCommentRepository.insertVoteComment(vote.getId(),id);
        }
        VoteResponse voteResponse = voteMapper.toResponse(vote);
        voteResponse.setNumVote(count);
        return voteResponse;
    }
}
