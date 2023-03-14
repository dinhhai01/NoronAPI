package com.mh.data.request;

import lombok.Data;

@Data
public class VoteRequest {
    private Integer userId;

    private String typeVote;
}
