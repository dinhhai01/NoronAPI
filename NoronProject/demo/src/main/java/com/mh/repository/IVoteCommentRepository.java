package com.mh.repository;

import com.mh.entity.VoteComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVoteCommentRepository {

    public Long insertVoteComment(int commentId,int voteId);
}
