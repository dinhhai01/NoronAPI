package com.mh.data.request;


import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Topic;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
public class PostRequest {

    private Integer id;
    private String title;
    private String content;
    private String typePost;
    private String viewPost;
    private List<Topic> topics;
}
