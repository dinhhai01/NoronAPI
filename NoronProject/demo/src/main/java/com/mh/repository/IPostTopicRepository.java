package com.mh.repository;


import com.mh.dto.PostTopicDTO;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Topic;

import java.util.List;

public interface IPostTopicRepository {

    void insertPostTopic(int postId,int topicId);

    List<PostTopicDTO> getListTopicByPostId(List<Integer> postId);
}
