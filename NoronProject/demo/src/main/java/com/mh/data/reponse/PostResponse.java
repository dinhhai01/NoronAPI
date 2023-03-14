package com.mh.data.reponse;


import lombok.Data;


import java.util.List;

@Data
public class PostResponse {
    private Integer id;
    private String title;
    private String content;
    private String typePost;
    private Integer viewPost;
    private UserResponse user;

    private List<CommentResponse> comments;

    private List<Integer> topics;
}
