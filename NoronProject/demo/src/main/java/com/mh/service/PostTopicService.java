package com.mh.service;

import com.mh.dto.PostTopicDTO;
import com.mh.repository.IPostTopicRepository;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Topic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostTopicService implements IPostTopicService{

    private final IPostTopicRepository postTopicRepository;

    public PostTopicService(IPostTopicRepository postTopicRepository) {
        this.postTopicRepository = postTopicRepository;
    }

    @Override
    public void insertPostTopic(int postId,int topicId) {
        postTopicRepository.insertPostTopic(postId, topicId);
    }

}
