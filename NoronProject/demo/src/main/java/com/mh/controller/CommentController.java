package com.mh.controller;

import com.mh.data.reponse.CommentResponse;
import com.mh.dto.CommentDTO;
import java.util.List;

import com.mh.service.ICommentService;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/comments")
public class CommentController {

//
    private final ICommentService service;

    public CommentController(ICommentService service){
        this.service = service;
    }
//
    @PostMapping()
    public void createComment(@RequestBody Comments comment) {
        service.createComment(comment);
    }

    @GetMapping(value = "/comment/{postId}")
    public List<CommentResponse> getAllCommentsByPostId(@PathVariable(name = "postId") int postId, Pageable pageable){
        return service.getAllComment(postId,pageable);
    }
//
//    @PutMapping(value = "/content/{id}")
//    public void updateContentById(@PathVariable(name = "id") int id, @RequestBody Comment comment) {
//        service.updateContentById(id, comment.getContent());
//    }
//
//    @PutMapping(value = "/type/{id}")
//    public void updateTypeById(@PathVariable(name = "id") int id, @RequestBody Comment comment) {
//        service.updateTypeById(id, comment.getTypeComment());
//    }
//
//    @GetMapping(value = "/post/{postId}/user/{userId}")
//    public CommentDTO getCommentByPostIdAndUserId(@PathVariable(name = "postId") int postId, @PathVariable(name = "userId") int userId) {
//        Comment comment = service.getCommentByPostIdAndUserId(postId, userId);
//
//        CommentDTO commentDTO = new CommentDTO()
//                .setContent(comment.getContent())
//                .setPostId(comment.getPost().getId())
//                .setUserId(comment.getUser().getId());
////        CommentDTO dto = new CommentDTO(comment.getContent(),comment.getTypeComment(),
////                             comment.getParentId(),comment.getUser().getId(),comment.getPost().getId());
////        ......
//        return commentDTO.setParentId(comment.getParentId());
//    }
//
    @GetMapping(value = "/comment/count/{postId}")
    public int countCommentByPostId(@PathVariable(name = "postId") int postId) {
        return service.countCommentByPostId(postId);
    }
//

}
