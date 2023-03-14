package com.mh.repository;



import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Post;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Topic;


import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;


public interface IPostRepository {

    Map<Post,List<Topic>> insertPost(Post post , List<Topic> topics);

    List<Post> getAllPosts(Pageable pageable);

    void updateViewById(int id);
//
    Post updateContentById(int id,String content);

    void updateTitleById(int id,String title);

    void updateTypeById(int id, String type);

    int getViewById(int id);

}
