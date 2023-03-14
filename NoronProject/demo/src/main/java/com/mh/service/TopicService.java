package com.mh.service;

import com.mh.repository.ITopicRepository;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Topic;
import org.springframework.stereotype.Service;

@Service
public class TopicService implements ITopicService{

    private final ITopicRepository topicRepository;

    public TopicService(ITopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public void insertTopic(Topic topic) {
        topicRepository.insertTopic(topic);
    }
}
