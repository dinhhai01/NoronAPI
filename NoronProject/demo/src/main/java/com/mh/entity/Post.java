package com.mh.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Post")
public class Post implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "type_post")
    private String type;

    @Column(name = "view_post")
    private int view;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createAt;

    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date updateAt;

    @Column(name = "delete_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date deleteAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<PostTopic> postTopics;

    @OneToMany(mappedBy = "post")
    private List<VotePost> votePosts;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
