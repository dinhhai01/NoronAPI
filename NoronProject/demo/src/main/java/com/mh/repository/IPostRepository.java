package com.mh.repository;



import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Post;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Topic;


import io.reactivex.Single;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;


public interface IPostRepository {

    Single<Map<Post,List<Topic>>> insertPost(Post post , List<Topic> topics);

    Single<List<Post>> getAllPosts(Pageable pageable);

   Single<Post> updateViewById(int id);
//
    Single<Post> updateContentById(int id,String content);

    Single<Post> updateTitleById(int id,String title);

    Single<List<Post>> getPosts(Pageable pageable);

    Single<Post> updateTypeById(int id, String type);

    Single<Integer> getViewById(int id);

}
