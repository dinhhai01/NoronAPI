package com.mh.service;

import com.mh.data.mapper.CommentMapper;
import com.mh.data.reponse.CommentResponse;
import com.mh.data.reponse.UserResponse;
import com.mh.repository.IUserRepository;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Comments;
import com.mh.repository.ICommentRepository;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Users;
import io.reactivex.Single;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentService implements ICommentService{


    private final ICommentRepository repository;

    private final IUserRepository userRepository;

    private final CommentMapper commentMapper;
    public CommentService(ICommentRepository repository, IUserRepository userRepository, CommentMapper commentMapper){
        this.repository = repository;
        this.userRepository = userRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public void createComment(Comments comment) {
    }

    @Override
    public void updateContentById(int id, String content) {
        repository.updateContentById(id, content);
    }

    @Override
    public void updateTypeById(int id, String type) {
        repository.updateTypeById(id, type);
    }

    @Override
    public Comments getCommentByPostIdAndUserId(int postId, int userId) {
        return repository.getCommentByPost_IdAndUser_Id(postId, userId);
    }

    @Override
    public int countCommentByPostId(int postId) {
        return repository.countCommentByPost_Id(postId);
    }

    @Override
    public Single<List<CommentResponse>> getAllComment(int postId, Pageable pageable) {
        Single<List<Comments>> commentsList = repository.getAllCommentByPostId(postId,pageable);
        return commentsList
                .flatMap(comments -> {
                    List<Integer> userIds = comments.stream()
                            .map(Comments::getUserId)
                            .collect(Collectors.toList());
                    return userRepository.getAllUsersById(userIds)
                            .map(users -> {
                                return getCommentResponse(comments,users);
                            });
                });
//        List<Integer> userIds = commentsList.stream()
//                .map(Comments::getUserId)
//                .collect(Collectors.toList());
//        Single<List<Users>> users = userRepository.getAllUsersById(userIds);
//
//        List<CommentResponse> commentResponses = getCommentResponse(commentsList,users);
//        return commentResponses;
    }

    public List<CommentResponse> getCommentResponse(List<Comments> comments, List<Users> users){
        List<CommentResponse> commentResponses = new ArrayList<>();
        comments.forEach(comments1 -> {
            CommentResponse commentResponse = commentMapper.toResponse(comments1);
            int userId = comments1.getUserId();
            UserResponse userResponse = getUserComment(userId,users);
            commentResponse.setUserResponse(userResponse);
            commentResponses.add(commentResponse);
        });
        return commentResponses;
    }

    UserResponse getUserComment(int userId, List<Users> users){
        Users user = users.stream()
                .filter(users1 -> users1.getId()==userId)
                .findFirst().orElse(null);
        if(user!=null) return commentMapper.toUserResponse(user);
        return null;
    }

}
