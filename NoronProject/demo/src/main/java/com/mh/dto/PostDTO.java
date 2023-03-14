package com.mh.dto;


import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;

@Data
@Accessors(chain = true)
public class PostDTO {

    private String title;

    private String content;

    private String typePost;

    private int viewPost;

    private Date createdAt;

    private Date updateAt;

    private Date deleteAt;

    private int userId;

    private String username;


}
