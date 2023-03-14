package com.mh.controller;


import com.mh.service.IPostTopicService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/post-topic")
public class PostTopicController {


    private final IPostTopicService service;

    public PostTopicController(IPostTopicService service) {
        this.service = service;
    }

    @PostMapping(value = "/{postId}/{topicId}")
    public void insertPostTopic(@PathVariable(name = "postId") int postId,@PathVariable(name = "topicId") int topicId){
        service.insertPostTopic(postId, topicId);
    }
}
