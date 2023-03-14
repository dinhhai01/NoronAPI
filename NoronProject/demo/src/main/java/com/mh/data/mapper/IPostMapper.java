package com.mh.data.mapper;


import com.mh.data.reponse.CommentResponse;
import com.mh.data.reponse.PostResponse;
import com.mh.data.reponse.TopicResponse;
import com.mh.data.reponse.UserResponse;
import com.mh.data.request.PostRequest;
import com.mh.dto.PostTopicDTO;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Comments;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Post;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Topic;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Users;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class IPostMapper {

    @Autowired
    protected TopicMapper topicMapper;

    @Autowired
    protected CommentMapper commentMapper;

    @Autowired
    private IUserMapper userMapper;

//    Post toPost(PostResponse postResponse);

    public abstract PostResponse toPostResponse(Post post);

    public abstract PostResponse toPostResponse(Post post, @Context List<Topic> topics);

    public abstract List<PostResponse> toResponses(List<Post> posts);

    public abstract Post toEntity(PostRequest postRequest);

    public TopicResponse toTopicRespon(PostTopicDTO postTopicDTO){
        return topicMapper.toResponse(postTopicDTO);
    }

    public UserResponse toUserResponse(Users users){
        return userMapper.toResponse(users);
    }

    public CommentResponse toCommentResponse(Comments comments){
        return commentMapper.toResponse(comments);
    }

//    public List<TopicResponse> toResponse(List<PostTopicDTO> postTopicDTOS, Integer id){
//        return topicMapper.topicResponses(postTopicDTOS,id);
//    }

    public List<CommentResponse> toResponse(List<Comments> comments,List<Users> users, Integer id){
        return commentMapper.toResponse(comments,users,id);
    }


    public List<Integer> getTopics(List<Topic> topics){
        return topics.stream()
                .map(topic -> topic.getId())
                .collect(Collectors.toList());
    }

    @AfterMapping
    protected void afterMapping(@MappingTarget PostResponse response, Post post, @Context List<Topic> topics) {
// ...
        response.setTopics(
                topics.stream()
                        .map(topic -> topic.getId())
                        .collect(Collectors.toList())
        );

    }
}
