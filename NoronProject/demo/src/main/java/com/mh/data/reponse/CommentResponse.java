package com.mh.data.reponse;

import lombok.Data;

@Data
public class CommentResponse {


    private String content;

    private String typeComment;

    private int parentId;

    private UserResponse userResponse;
}
