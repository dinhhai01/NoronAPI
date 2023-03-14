package com.mh.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors
public class PostTopicDTO {
    private Integer postId;

    private Integer topicId;

    private String nameTopic;
}
