package com.mh.controller;



import com.mh.data.reponse.PostResponse;
import com.mh.data.request.PostRequest;
import com.mh.service.IPostService;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.springframework.web.bind.annotation.*;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Post;

import javax.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/posts")
public class PostController {


    private final IPostService service;

    public PostController(IPostService service) {
        this.service = service;
    }

    @PostMapping()
    public PostResponse insertPost(@RequestBody PostRequest postRequest){
        return service.insertPost(postRequest);
    }

    @GetMapping()
    public Single<List<PostResponse>> getAllPosts(@RequestParam(value = "limit", defaultValue = "1") Integer numComment,Pageable pageable) {

        return service.getAllPosts(numComment, pageable);
    }

//    @GetMapping(value = "/content/{id}")
//    public PostDTO getContentPostById(@PathVariable(name = "id") int id) {
//        Post post = service.getContentPostById(id);
////        PostDTO dto = new PostDTO()
////                .setTitle(post.getTitle())
////                .setContent(post.getContent())
////                .setTypePost(post.getType())
////                .setViewPost(post.getView())
////                .setCreatedAt(post.getCreateAt())
////                .setUpdateAt(post.getUpdateAt())
////                .setDeleteAt(post.getDeleteAt())
////                .setUserId(post.getUser().getId())
////                .setUsername(post.getUser().getUsername());
//
//        return null;
//    }


    @GetMapping(value = "/view/{id}")
    public int getViewById(@PathVariable(name = "id") int id) {
        Post post = service.getViewById(id);
        return post.getViewPost();
    }

    @PutMapping(value = "/view/{id}")
    public void updateViewById(@PathVariable(name = "id") int id) {
        service.updateViewById(id);
    }

    @PutMapping(value = "/content/{id}")
    public Single<String> updateContentById(@PathVariable(name = "id") int id, @RequestBody Post post) {
        return service.updateContentById(id, post.getContent())
                .subscribeOn(Schedulers.io())
                .map(PostResponse::getContent);
    }

    @PutMapping(value = "/title/{id}")
    public void updateTitleById(@PathVariable(name = "id") int id, @RequestBody Post post) {
        service.updateTitleById(id, post.getTitle());
    }

    @PutMapping(value = "/type/{id}")
    public void updateTypeById(@PathVariable(name = "id") int id, @RequestBody Post post) {
        service.updateTypeById(id, post.getTypePost());
    }
}
