package com.mh.service;

import com.mh.data.mapper.IPostMapper;
import com.mh.data.reponse.CommentResponse;
import com.mh.data.reponse.PostResponse;
import com.mh.data.reponse.TopicResponse;
import com.mh.data.request.PostRequest;
import com.mh.dto.PostTopicDTO;
import com.mh.repository.ICommentRepository;
import com.mh.repository.IPostRepository;
import com.mh.repository.IPostTopicRepository;
import com.mh.repository.IUserRepository;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Comments;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Post;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Topic;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Users;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {


    private final IPostRepository repository;

    private final ICommentRepository commentRepository;

    private final IPostTopicRepository postTopicRepository;

    private final IUserRepository userRepository;

    private final IPostMapper mapper;

    public PostService(IPostRepository repository, ICommentRepository commentRepository,
                       IPostTopicRepository postTopicRepository,
                       IUserRepository userRepository,
                       IPostMapper mapper) {
        this.repository = repository;
        this.commentRepository = commentRepository;
        this.postTopicRepository = postTopicRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;

    }

    @Override
    public PostResponse insertPost(PostRequest postRequest) {
        Post post = mapper.toEntity(postRequest);
        List<Topic> topics = postRequest.getTopics();
        Map<Post, List<Topic>> map = repository.insertPost(post, topics);
        Map.Entry<Post, List<Topic>> entry = map.entrySet().iterator().next();
        Post postKey = entry.getKey();
        List<Topic> topicPoJo = entry.getValue();
        return mapper.toPostResponse(postKey, topicPoJo);
    }

    @Override
    public Single<List<PostResponse>> getAllPosts(Integer numComment, Pageable pageable) {

        return getAllPost(pageable)
                .flatMap(posts -> {
                    List<PostResponse> postResponses = mapper.toResponses(posts);
                    List<Integer> postIds = posts.stream().map(Post::getId).collect(Collectors.toList());
                    return Single.zip(
                            getComments(postIds, numComment),
                            getTopics(postIds),
                            (o, o1) -> getPostResponses(o, o1, postResponses));
                });
    }

    private Single<List<Post>> getAllPost(Pageable pageable) {
        return Single.create(singleEmitter -> {
            List<Post> posts = repository.getAllPosts(pageable);
            singleEmitter.onSuccess(posts);
        });
    }

    private List<PostResponse> getPostResponses(Map<Integer, List<CommentResponse>> map,
                                                Map<Integer, List<TopicResponse>> mapTopic,
                                                List<PostResponse> postResponses) {

        postResponses.forEach(p -> {
            if (!CollectionUtils.isEmpty(map) && map.containsKey(p.getId())) {
                p.setComments(map.get(p.getId()));
            }

            if (!CollectionUtils.isEmpty(mapTopic) && map.containsKey(p.getId())) {
                p.setTopics(mapTopic.get(p.getId()).stream()
                        .map(TopicResponse::getTopicId)
                        .collect(Collectors.toList()));
            }
        });

        return postResponses;
    }

    private Single<Map<Integer, List<CommentResponse>>> getComments(List<Integer> postId, int numberOfComments) {
        return Single.just("io")
                .subscribeOn(Schedulers.io())
                .flatMap(s -> getCommentByPostId(postId, numberOfComments))
                .flatMap(comments -> {
                    return getUsers(comments)
                            .map(users -> {
                                Map<Integer, List<CommentResponse>> map = new HashMap<>();
                                postId.forEach(p -> {
                                    List<CommentResponse> commentsList = mapper.toResponse(comments, users, p);
                                    map.put(p, commentsList);
                                });
                                return map;
                            });
                });
    }


    private Single<List<Comments>> getCommentByPostId(List<Integer> postIds, int numberOfComments) {
        return Single.create(singleEmitter -> {
            List<Comments> comments = commentRepository.getCommentByPostId(postIds, numberOfComments);
            singleEmitter.onSuccess(comments);
        });
    }

    private Single<Map<Integer, List<TopicResponse>>> getTopics(List<Integer> postId) {
        return Single.create(singleEmitter -> {
            List<PostTopicDTO> postTopicDTOS = postTopicRepository.getListTopicByPostId(postId);
            Map<Integer, List<TopicResponse>> map = new HashMap<>();
            postId.forEach(p -> {
                List<TopicResponse> topicList = getTopicResponses(postTopicDTOS, p);
                map.put(p, topicList);
            });
            singleEmitter.onSuccess(map);
        });
    }

    private List<TopicResponse> getTopicResponses(List<PostTopicDTO> postTopicDTOS, Integer p) {
        List<TopicResponse> topicList = new ArrayList<>();
        topicList = postTopicDTOS.stream()
                .filter(postTopicDTO -> Objects.equals(postTopicDTO.getPostId(), p))
                .map(mapper::toTopicRespon)
                .collect(Collectors.toList());
        return topicList;
    }

    private List<CommentResponse> getCommentResponses(List<Comments> comments, List<Users> userComments, Integer p) {
        return comments.stream()
                .map(comment -> getCommentResponse(userComments, p, comment))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private CommentResponse getCommentResponse(List<Users> userComments, Integer p, Comments comment) {
        if (Objects.equals(comment.getPostId(), p) && comment.getParentId() == -1) {
            CommentResponse commentResponse = mapper.toCommentResponse(comment);
            userComments.forEach(userComment -> {
                if (Objects.equals(userComment.getId(), comment.getUserId())) {
                    commentResponse.setUserResponse(mapper.toUserResponse(userComment));
                }
            });
            return commentResponse;
        }
        return null;
    }

    private Single<List<Users>> getUsers(List<Comments> comments) {
        List<Integer> userIds = comments.stream()
                .filter(comment -> comment.getParentId() == -1)
                .map(Comments::getUserId)
                .collect(Collectors.toList());
        return Single.just("io")
                .flatMap(s->getAllUsers(userIds));
//        return Single.create(singleEmitter -> {
//            singleEmitter.onSuccess(userRepository.getAllUsers(userIds));
//        });
//        List<Integer> userIds = comments.stream()
//                .filter(comment -> comment.getParentId() == -1)
//                .map(Comments::getUserId)
//                .collect(Collectors.toList());
//        return userRepository.getAllUsers(userIds);
    }

    private Single<List<Users>> getAllUsers(List<Integer> userIds) {
        return Single.create(singleEmitter -> {
            singleEmitter.onSuccess(userRepository.getAllUsers(userIds));
        });
    }

    @Override
    public Post getViewById(int id) {
        return null;
    }

    @Override
    public Single<Integer> updateViewById(int id) {
        return Single.create(singleEmitter -> {
            Post post = repository.updateViewById(id);
            if (post!= null) {
                singleEmitter.onSuccess(post.getId());
            } else {
                singleEmitter.onError(new Exception("post not found"));
            }
        });
    }

    @Override
    public Single<PostResponse> updateContentById(int id, String content) {
        return Single.create(singleEmitter -> {
            Post post = repository.updateContentById(id, content);
            if (post != null) {
                singleEmitter.onSuccess(mapper.toPostResponse(post));
            } else {
                singleEmitter.onError(new Exception("post not found"));
            }
        });
    }

    @Override
    public Single<String> updateTitleById(int id, String title) {
        return Single.create(singleEmitter -> {
            Post post = repository.updateTitleById(id, title);
            if (post!= null) {
                singleEmitter.onSuccess(post.getTitle());
            } else {
                singleEmitter.onError(new Exception("post not found"));
            }
        });
    }

    @Override
    public Single<String> updateTypeById(int id, String type) {
        return Single.create(singleEmitter -> {
            Post post = repository.updateTypeById(id, type);
            if (post!= null) {
                singleEmitter.onSuccess(post.getTypePost());
            } else {
                singleEmitter.onError(new Exception("post not found"));
            }
        });
    }

    @Override
    public Map<Integer, Integer> countVoteAndViewById(int id) {
        return null;
    }

}
