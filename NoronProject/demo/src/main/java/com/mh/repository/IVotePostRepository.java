package com.mh.repository;

import com.mh.entity.VotePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVotePostRepository  {

    public Long insertVote(int voteId, int postId);

    int countVoteByPostId(int id);
}
