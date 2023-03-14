package com.mh.data.mapper;

import com.mh.data.reponse.CommentResponse;
import com.mh.data.reponse.UserResponse;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Comments;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Users;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class CommentMapper {

    @Autowired
    protected IUserMapper userMapper;
    public abstract CommentResponse toResponse(Comments comments);

    public UserResponse toUserResponse(Users users){
        return userMapper.toResponse(users);
    }

//    @BeforeMapping
//    protected void beforeMapping(@MappingTarget CommentResponse commentResponses,
//                                 Comments comments, @Context List<Users> users, @Context Integer id){
//        boolean flag = (comments.getParentId() == -1 && Objects.equals(comments.getPostId(),id));
//        if(!flag) comments = null;
//    }

    @Named("toRs")
    public abstract CommentResponse toResponse(Comments comments,@Context List<Users> users,@Context Integer id);

    @IterableMapping(qualifiedByName = "toRs")
    public abstract List<CommentResponse> toResponse(List<Comments> comments,
                                                     @Context List<Users> users, @Context Integer id);

    @AfterMapping
    protected void afterMapping(@MappingTarget CommentResponse commentResponse, Comments comments,
                               @Context List<Users> users, @Context Integer id){

        Users user = users.stream()
                .filter(users1 -> Objects.equals(users1.getId(),comments.getUserId()) &&
                        Objects.equals(comments.getPostId(),id) &&
                        comments.getParentId() == -1)
                .findAny().orElse(null);

        commentResponse.setUserResponse(toUserResponse(user));
    }

}
