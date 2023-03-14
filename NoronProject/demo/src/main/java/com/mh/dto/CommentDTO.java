package com.mh.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CommentDTO {

    private String content;
    private String typeComment;
    private int parentId;
    private int userId;
    private int postId;

}
