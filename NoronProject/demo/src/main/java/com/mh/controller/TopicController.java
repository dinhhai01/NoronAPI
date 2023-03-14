package com.mh.controller;

import com.mh.service.ITopicService;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Topic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/topics")
public class TopicController {

    private final ITopicService topicService;

    public TopicController(ITopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping()
    public void insertTopic(@RequestBody Topic topic){
        topicService.insertTopic(topic);
    }
}
