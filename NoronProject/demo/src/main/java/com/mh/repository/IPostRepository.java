package com.mh.repository;



import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Post;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Topic;


import io.reactivex.Single;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;


public interface IPostRepository {

    Map<Post,List<Topic>> insertPost(Post post , List<Topic> topics);

    List<Post> getAllPosts(Pageable pageable);

   Post updateViewById(int id);
//
    Post updateContentById(int id,String content);

    Post updateTitleById(int id,String title);

    Single<List<Post>> getPosts(Pageable pageable);

    Post updateTypeById(int id, String type);

    int getViewById(int id);

}
