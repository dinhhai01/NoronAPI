package com.mh.data.reponse;

import lombok.Data;

@Data
public class CommentResponse {

    private Integer id;
    private Integer postId;
    private String content;

    private String typeComment;

    private int parentId;

    private UserResponse userResponse;
}
