package com.mh.repository;


import com.mh.dto.PostTopicDTO;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Topic;
import io.reactivex.Single;

import java.util.List;

public interface IPostTopicRepository {

    void insertPostTopic(int postId,int topicId);

    Single<List<PostTopicDTO>> getListTopicByPostId(List<Integer> postId);
}
